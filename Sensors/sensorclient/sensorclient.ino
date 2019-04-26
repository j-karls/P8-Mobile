
//////////////////////////////////////////////////////////////////////////////////////////////////
// Classes

class Sensor {
protected: 
  unsigned long _cycleStartTime; // when we started on the period/cycle of the sensor
  virtual double getRawValue() = 0; // get raw measurement directly from sensor at the current time
public:
  char name[];
  unsigned long cycleTime; // time it takes to finish one period
  Sensor() {}
  virtual void calibrate() = 0; // perform one-time calibration of sensor, likely at startup time
  virtual double getValue() = 0; // get some sort of aggregated or corrected value

  // State machine functions
  bool canGetNewValue;
  virtual void manageState(unsigned long currentTime) = 0;
};

class SensorCO : public Sensor {
  protected:
    int _pinData;
    int _pinPower;
    int _pinDigital;

    bool _isHeating = false;
    unsigned long _heatPeriod = 60000;
    unsigned long _coolPeriod = 90000; 
    int _numMeasurements = 0;
    double _aggregatedValue = 0;

    double _analogMeasurementFresh = 40;
    double _rRatioFresh = 1; // r-ratio constant in fresh air as defined by datasheet
    double _r0 = -1; // a constant that is determined based on sensor results in clean air
    double _funcConstantA = 0.8494; // a constant that is determined by finding the regression of the curve describing sensor output in the datasheet 
    double _funcConstantB = -0.5455;  // another constant from the same regression
    double _rl = 10000; // load resistance in Ohm
    double _vc = 5; // control voltage in Volt

    void startNewCycle(unsigned long newCycleStartTime) {
      _cycleStartTime = newCycleStartTime;
      heat();
    }
    void performAggregation(double newValue) {
      _aggregatedValue += newValue;
      _numMeasurements++;
    }
    double convertVRL(double analogValue) {
      // analogValue is returned by the reading the sensor with analogRead and is a number between 0 and 1023
      return (analogValue / 1023) * 5; // voltage over the load resistor in Volt: basically, the "output" voltage of the circuit, multiplied with 5, as it is the maximum voltage that can be returned by the arduino
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
      Serial.println(String((pow((_funcConstantA / rRatio), (1 / -_funcConstantB))), 4));
      
      return pow((_funcConstantA / rRatio), (1 / -_funcConstantB)); // A power function of the form PPM(RRatio) = (a / RRatio) ^ (1 / b)
    }
  public:
    String name = String("CO");
    void calibrate() { /* no need to calibrate anything in particular */ } 

    SensorCO(int pinData, int pinDigital, int pinPower) { 
        _pinData = pinData; // the analog pin of the arduino where we receive result voltage (analog in), the higher voltage, the higher concentration of gas
        _pinDigital = pinDigital; 
        _pinPower = pinPower; // the analog pin that the sensor draws power (PWM) from

        pinMode(_pinData, INPUT); 
        pinMode(_pinPower, OUTPUT);
        pinMode(_pinDigital, INPUT);

        canGetNewValue = false;
        cycleTime = _heatPeriod + _coolPeriod;
        _cycleStartTime = 0;
        _r0 = _rRatioFresh * (convertRS(_analogMeasurementFresh));
    }
    double getRawValue() {
      return analogRead(_pinData);
    }
    double getDigitalValue() {
      return digitalRead(_pinDigital);
    }
    void cool() {     
      analogWrite(_pinPower, 71); // set to 1.4V (71 of 255 duty cycle equals 1.4 V PWM on average)
      _isHeating = false;
    }
    void heat() {
      analogWrite(_pinPower, 255); // set to 5V (meaning PWM always on)
      _isHeating = true;
    }
    void manageState(unsigned long currentTime) {
      if (_cycleStartTime == 0) { // if we have not yet started a cycle
        startNewCycle(currentTime); // sets cycle time and starts the cycle by heating 
      }
      unsigned long cycleProgress = currentTime - _cycleStartTime;

      if (cycleProgress >= cycleTime) { // if we're done with a measurement cycle
        canGetNewValue = true;
        startNewCycle(currentTime);
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
    double getValue() {
      canGetNewValue = false;
      return _aggregatedValue / _numMeasurements; // return average
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


//////////////////////////////////////////////////////////////////////////////////////////////////
// Program flow

Diode diode = Diode(13);
SensorCO sensorCO = SensorCO(A3, 8, A0);
// INSERT OTHER SENSORS

unsigned long startTime;
unsigned long currentTime;

void setup()
{
  Serial.begin(9600); // initialize serial connection with a data rate of 9600 bits per second
  startTime = millis(); // get number of milliseconds since the program started
  sensorCO.calibrate();
}

void loop()
{
  delay(1000);
  
  currentTime = millis(); 

  // Get new value
  if (sensorCO.canGetNewValue) {
    //Serial.println(currentTime);
    Serial.println(String(sensorCO.name + String('|') + String(sensorCO.getValue(), 4)));
    //Serial.println(sensorCO.getRawValue());
    Serial.println("------------------------");
  }
  
  // Manage state
  sensorCO.manageState(currentTime);
}
