
#include "cactus_io_AM2302.h"

//////////////////////////////////////////////////////////////////////////////////////////////////
// Classes

class Sensor {
protected: 
  unsigned long _cycleStartTime; // when we started on the period/cycle of the sensor
  virtual double getRawValue() = 0; // get raw measurement directly from sensor at the current time
public:
  String sensorName;
  unsigned long cycleTime; // time it takes to finish one period
  Sensor() {}
  virtual void calibrate() = 0; // perform one-time calibration of sensor, likely at startup time
  virtual String getValue() = 0; // get some sort of aggregated or corrected value

  // State machine functions
  bool canGetNewValue;
  virtual void manageState(unsigned long currentTime) = 0;
};

class SensorMQ : public Sensor {
protected:
  int _pinData; // the analog pin of the arduino where we receive result voltage (analog in), the higher voltage, the higher concentration of gas
  int _pinPower; // the analog pin that the sensor draws power (PWM) from
  int _pinDigital; // the digital pin, which is either 0 or 1, depending on if the gas measurement is over a certain amount (can be configured with the load resistor)
  bool _isHeating = false;
  int _coolPWM;
  int _heatPWM;

  double _analogMeasurementFresh;
  double _rRatioFresh; // r-ratio constant in fresh air as defined by datasheet
  double _r0; // a constant that is determined based on sensor results in clean air
  double _funcConstantA; // a constant that is determined by finding the regression of the curve describing sensor output in the datasheet 
  double _funcConstantB;  // another constant from the same regression
  double _rl; // load resistance in Ohm
  double _vc = 5; // control voltage in Volt

  int _numMeasurements = 0;
  double _aggregatedValue = 0;
public:
  SensorMQ(int pinData, int pinDigital, int pinPower) { 
    _pinData = pinData; 
    _pinDigital = pinDigital; 
    _pinPower = pinPower; 
    pinMode(_pinData, INPUT); 
    pinMode(_pinPower, OUTPUT);
    pinMode(_pinDigital, INPUT);

    canGetNewValue = false;
    _cycleStartTime = 0;
  }
  void calibrate() { /* no need to calibrate anything in particular */ } 
  void performAggregation(double newValue) {
    _aggregatedValue += newValue;
    _numMeasurements++;
  }
  double convertVRL(double analogValue) {
    // analogValue is returned by the reading the sensor with analogRead and is a number between 0 and 1023
    return (analogValue / 1023) * 5; 
    // voltage over the load resistor in Volt: basically, the "output" voltage of the circuit, 
    // multiplied with 5, as it is the maximum voltage that can be returned by the arduino
  }
  double convertRS(double analogValue) {
    double vrl = convertVRL(analogValue);  
    return ((_vc * _rl) / vrl) - _rl; // rs is resistance over the sensor in Ohm
  }
  double convertPPM(double analogValue) {
    double rs = convertRS(analogValue);
    double rRatio = rs / _r0;
    // Serial.println(String(analogValue, 4));
    // Serial.println(String(_r0, 4));
    // Serial.println(String(rRatio, 4));
    // Serial.println(String((pow((_funcConstantA / rRatio), (1 / -_funcConstantB))), 4));
    return pow((_funcConstantA / rRatio), (1 / -_funcConstantB)); // A power function of the form PPM(RRatio) = (a / RRatio) ^ (1 / b)
  }
  double getRawValue() {
    return analogRead(_pinData);
  }
  double getDigitalValue() {
    return digitalRead(_pinDigital);
  }
  String getValue() {
    canGetNewValue = false;
    return String((_aggregatedValue / _numMeasurements), 4); // return average
  }
  void cool() {     
    analogWrite(_pinPower, _coolPWM); 
    _isHeating = false;
  }
  void heat() {
    analogWrite(_pinPower, _heatPWM); 
    _isHeating = true;
  }
  void startNewCycle(unsigned long newCycleStartTime) {
    _cycleStartTime = newCycleStartTime;
  }
};

class SensorMQ7 : public SensorMQ {
protected:
  unsigned long _heatPeriod = 6000;
  unsigned long _coolPeriod = 9000; 
public:
  SensorMQ7(int pinData, int pinDigital, int pinPower) : SensorMQ(pinData, pinDigital, pinPower) { 
    sensorName = String("MQ7");
    cycleTime = _heatPeriod + _coolPeriod;
    _analogMeasurementFresh = 40;
    _rRatioFresh = 1;
    _funcConstantA = 0.8494;
    _funcConstantB = -0.5455;
    _rl = 10000;
    _r0 = _rRatioFresh * (convertRS(_analogMeasurementFresh));
    _coolPWM = 71; // set to 1.4V (71 of 255 duty cycle equals 1.4 V PWM on average)
    _heatPWM = 255; // set to 5V (meaning PWM always on)
  }
  void manageState(unsigned long currentTime) {
    if (_cycleStartTime == 0) { // if we have not yet started a cycle
      startNewCycle(currentTime); // sets cycle time and starts the cycle by heating 
      heat();
    }
    unsigned long cycleProgress = currentTime - _cycleStartTime;

    if (cycleProgress >= cycleTime) { // if we're done with a measurement cycle
      canGetNewValue = true;
      startNewCycle(currentTime);
      heat();
    }
    else if (cycleProgress >= _heatPeriod && _isHeating) { // switch state to cooling
      cool();
    }
    else if (cycleProgress >= _heatPeriod) { // if we're in the cooling state
      heat(); // turn on the sensor for short time, such that we can actually read values
      performAggregation(convertPPM(getRawValue()));
      cool(); // return to cooling state
    }
    // otherwise, we´re currently heating, and should continue to do so
  }
};

class SensorMQ135 : public SensorMQ {
public:
  SensorMQ135(int pinData, int pinDigital, int pinPower) : SensorMQ(pinData, pinDigital, pinPower) { 
    sensorName = String("MQ135");
    cycleTime = 10000; // we aggregate raw measurements every 10 seconds
    _analogMeasurementFresh = 40; //////////////FIX
    _rRatioFresh = 3.7;
    _funcConstantA = 5.1451;
    _funcConstantB = -0.3522;
    _rl = 10000;
    _r0 = _rRatioFresh * (convertRS(_analogMeasurementFresh));
    _coolPWM = 0; // shut off completely
    _heatPWM = 255; // set to 5V (meaning PWM always on)
  }
  void manageState(unsigned long currentTime) {
    if (_cycleStartTime == 0) { // if we have not yet started a cycle
      startNewCycle(currentTime); // sets cycle time and starts the cycle by heating 
      heat();
    }
    unsigned long cycleProgress = currentTime - _cycleStartTime;

    if (cycleProgress >= cycleTime) { // if we're done with a measurement cycle
      canGetNewValue = true;
      startNewCycle(currentTime);
    }
    else {
      performAggregation(convertPPM(getRawValue()));
    }
  }
};

class SensorAM2302 : public Sensor {
protected:
  AM2302* localAM2302;
  double getRawValue() {
    Serial.println("test1");
    localAM2302->readHumidity();
    Serial.println("test2 " + String(localAM2302->humidity));
    localAM2302->readTemperature();
    Serial.println("test3 " + String(localAM2302->temperature_C));
    return 0;    
  }
public:
  int _numMeasurements = 0;
  int _aggHumidity = 0;
  int _aggTemperature = 0;
  void calibrate() { /* no need to calibrate anything in particular */ }
  String getValue() {
    int humidity = _aggHumidity/_numMeasurements;
    int temperature = _aggTemperature/_numMeasurements;
    _aggHumidity = 0;
    _aggTemperature = 0;
    _numMeasurements = 0;
    return String("humidity: " + String(humidity) + " %, Temperature: " + String(temperature) + " *C");
  }
  SensorAM2302(int pinDigital) {
    sensorName = String("AM2302");
    AM2302 localAM2302 = AM2302(pinDigital);
    canGetNewValue = false;
  }
  void manageState(unsigned long currentTime) {
    
    getRawValue();
    
    if (isnan(localAM2302->humidity) || isnan(localAM2302->temperature_C)) {
      return;
    }
    else {
      _aggHumidity += localAM2302->humidity;
      _aggTemperature += localAM2302->temperature_C;
      _numMeasurements++;    
    }
    
    if (_numMeasurements == 10) {
      canGetNewValue = true;
    }
  }
};




class Diode {
private: 
  int _pin;
  
public:
  Diode(int pin) {
    _pin = pin;
    pinMode(_pin, OUTPUT);
  }
  void turnOn() {
    digitalWrite(_pin, HIGH);
  }
  void turnOff() {
    digitalWrite(_pin, LOW);
  }
  void toggleState() {
    digitalWrite(_pin, !digitalRead(_pin));
  }
};


//////////////////////////////////////////////////////////////////////////////////////////////////
// Methods

void delaySec(int sec)
{
  if(sec == 0) return;
  delay(1000);
  Serial.print("Waiting... ");
  Serial.println(sec);
  delaySec(sec - 1);
}

void serialPrintResult(Sensor *sensor) {
  Serial.println(String(sensor->sensorName + String('|') + sensor->getValue()));
}

//////////////////////////////////////////////////////////////////////////////////////////////////
// Program flow

Diode diode = Diode(13);
SensorMQ7 sensorMQ7 = SensorMQ7(A3, 8, A0);
SensorMQ135 sensorMQ135 = SensorMQ135(A4, 7, A1);
SensorAM2302 sensorAM2302 = SensorAM2302(12);

unsigned long startTime;
unsigned long currentTime;

void setup()
{
  Serial.begin(9600); // initialize serial connection with a data rate of 9600 bits per second
  startTime = millis(); // get number of milliseconds since the program started
  sensorMQ7.calibrate();
  sensorMQ135.calibrate();
  sensorAM2302.calibrate();
}

void loop()
{
  delay(1000);
  currentTime = millis(); 

  // Get new value
  if (sensorMQ7.canGetNewValue) {
    serialPrintResult(&sensorMQ7);
    //Serial.println(sensorMQ7.getRawValue());
  }
  if (sensorMQ135.canGetNewValue) {
    serialPrintResult(&sensorMQ135);
  }
  if (sensorAM2302.canGetNewValue) {
    serialPrintResult(&sensorAM2302);
  }
  
  // Serial.println("------------------------");

  // Manage state
  sensorMQ7.manageState(currentTime);
  sensorMQ135.manageState(currentTime);
  sensorAM2302.manageState(currentTime);
}





// Calibrate with: http://co2now.org/
