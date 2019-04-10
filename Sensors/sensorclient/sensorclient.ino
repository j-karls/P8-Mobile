
//////////////////////////////////////////////////////////////////////////////////////////////////
// Classes

class Sensor {
private: 
  virtual double getRawValue() = 0; // get raw measurement directly from sensor at the current time
public:
  Sensor() {}
  virtual void calibrate() = 0; // perform one-time calibration of sensor, likely at startup time
  char name[];
  virtual double getValue() = 0; // get some sort of aggregated or corrected value

  // State machine
  virtual bool needsControl(unsigned long currentTime) = 0;
  virtual bool canGetNewValue(unsigned long currentTime) = 0;
  virtual void manageState(unsigned long currentTime) = 0;
};

class SensorCO : public Sensor {
  private:
    int _pinData;
    int _pinPower;
    int _pinDigital;

    double getRawValue() {
      return analogRead(_pinData);
    }
  public:
    char name[3] = {'C', 'O', '\0'};
    unsigned long heatPeriod = 60;
    unsigned long coolPeriod = 90;
    void calibrate() { /* no need to calibrate anything in particular */ } 

    SensorCO(int pinData, int pinDigital, int pinPower) { 
        _pinData = pinData; // the analog pin of the arduino where we receive result voltage (analog in), the higher voltage, the higher concentration of gas
        _pinDigital = pinDigital; 
        _pinPower = pinPower; // the digital pin that the sensor draws power (PWM) from

        pinMode(_pinData, INPUT); 
        pinMode(_pinPower, OUTPUT);
        pinMode(_pinDigital, INPUT);
    }
    double getDigitalValue() {
      return digitalRead(_pinDigital);
    }
    void cool() {     
      digitalWrite(_pinPower, LOW); // set to 1.4V
    }
    void heat() {
      digitalWrite(_pinPower, HIGH); // set to 5V
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

void taskA(SensorCO sensor) {
  int co_ppm = sensor.getRawValue();
  Serial.println(co_ppm);
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
  currentTime = millis(); 

  if (currentTime - startTime >= sensorCO.heatPeriod) {
    taskA(sensorCO);
    startTime = currentTime; 
  }

  // DO SOMETHING WITH STATE MACHINE? SensorCO heat state, then check for 60 seconds? If cool state, then check for 90 seconds?


  if(sensorCO.canGetNewValue(currentTime))
    Serial.write(sensorCO.name);
    Serial.write(sensorCO.getValue());

  if(sensorCO.needsControl(currentTime))
    sensorCO.manageState();
  if(sensorThermalComfort.needsControl(currentTime))
    sensorThermalComfort.manageState();
}
