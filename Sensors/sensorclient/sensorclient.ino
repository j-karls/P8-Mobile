
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

  // State machine
  bool canGetNewValue;
  virtual void manageState(unsigned long currentTime) = 0;
};

class SensorCO : public Sensor {
  protected:
    int _pinData;
    int _pinPower;
    int _pinDigital;

    bool _isHeating = false;
    unsigned long _heatPeriod = 6000;
    unsigned long _coolPeriod = 9000; //change it back REEEEEEEEEEEEEEEEEE
    int _numMeasurements = 0;
    double _aggregatedValue = 0;
    double _ro = 1.906329; // a constant that is determined based on sensor results in clean air
    double _funcConstantA = 101.9084; // a constant that is determined by finding the regression of the curve describing sensor output in the datasheet 
    double _funcConstantB = 1.5105;  // another constant from the same regression


    void startNewCycle(unsigned long newCycleStartTime) {
      _cycleStartTime = newCycleStartTime;
      heat();
    }
    void performAggregation(double newValue) {
      _aggregatedValue += newValue;
      _numMeasurements++;
    }
    double convertToPPM(double value) {
      double voltage = value / 1024 * 5;
      double rs = (5 - voltage) / voltage;
      double ratio = rs / _ro;
      // Serial.println(100 * pow(1.53196, ratio));
      return 100 * pow(1.53196, ratio);

      // PPM conversion doesn't make any kind of sense. Simplify it, fix it, or whatever... 
    }
  public:
    String name = String("CO");
    void calibrate() { /* no need to calibrate anything in particular */ } 

    SensorCO(int pinData, int pinDigital, int pinPower) { 
        _pinData = pinData; // the analog pin of the arduino where we receive result voltage (analog in), the higher voltage, the higher concentration of gas
        _pinDigital = pinDigital; 
        _pinPower = pinPower; // the digital pin that the sensor draws power (PWM) from

        pinMode(_pinData, INPUT); 
        pinMode(_pinPower, OUTPUT);
        pinMode(_pinDigital, INPUT);

        canGetNewValue = false;
        cycleTime = _heatPeriod + _coolPeriod;
        _cycleStartTime = 0;
    }
    double getRawValue() {
      return analogRead(_pinData);
    }
    double getDigitalValue() {
      return digitalRead(_pinDigital);
    }
    void cool() {     
      digitalWrite(_pinPower, LOW); // set to 1.4V
      _isHeating = false;
    }
    void heat() {
      digitalWrite(_pinPower, HIGH); // set to 5V
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
      else { // if we're in the cooling state
        performAggregation(convertToPPM(getRawValue()));
      }
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
SensorCO sensorCO = SensorCO(A3, 8, 3);
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
    Serial.println(currentTime);
    Serial.println(String(sensorCO.name + String('|') + String(sensorCO.getValue(), 4)));
    Serial.println(sensorCO.getRawValue());
  }
  
  // Manage state
  sensorCO.manageState(currentTime);
}
