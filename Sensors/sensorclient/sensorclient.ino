
class Sensor
{
public:
    Sensor() {}
    virtual void calibrate() = 0;     // Perform one-time calibration of sensor, likely at startup time
    char name[];
    // virtual int period;        // The period of the sensor in milliseconds: i.e. the time between new sensor measurements
    // virtual double getValue() = 0;     // Get the corrected (or aggregated) value of the sensor. The value ought to be updated each period. 

    virtual double getRawValue() = 0;   // Get raw measurement directly from sensor at the current time
};

// #import "sensor.ino"

// constructor with two pins for input output, and two for heating? or one?

class SensorCO : public Sensor
{
    public:
        SensorCO(int pin_data, int pin_power, int pin_digital) 
        { 
            _pinData = pin_data; // The analog pin of the arduino where we receive result voltage (analog in), the higher voltage, the higher concentration of gas
            _pinPower = pin_power;
            _pinDigital = pin_digital;

            pinMode(_pinData, INPUT); 
            pinMode(_pinPower, OUTPUT);
            pinMode(_pinDigital, INPUT);
        }
    
        // char name[3] = "CO";
        char name[3] = {'C', 'O', '\0'};
        // int period = 90;
    
        void calibrate() { /*No need to calibrate anything in particular*/ } 

      // if time >= 90 (measurementPeriod + heatingPeriod)
      // return aggregate and reset it
      // if time >= 60 (heatingPeriod)
      // shut off heating, if not already shut off
      // add getRawValue() to aggregate
      // if time < heatingPeriod 
      // return void
      /////// REEEEEEEEEEE FIX

    double getDigitalValue()
    {
        return digitalRead(_pinDigital);
    }
    
    double getRawValue()
    {
        return analogRead(_pinData);
    }

    void cool()
    {     
        digitalWrite(_pinPower, LOW); // Set to 1.4V
    }

    void heat() 
    {
        digitalWrite(_pinPower, HIGH); // Set to 5V
    }

    private:
        int _pinData;
        int _pinPower;
        int _pinDigital;

// For god's sake,figure out if you're using camelCase or shit_case to  write your code
};


SensorCO sensor_co = SensorCO(A3, 3, 8);
// SensorThermalComfort sensor_tc = SensorThermalComfort(A7, A8);
// SensorAirQuality sensor_aq = SensorAirQuality(A9, A10);
// Sensor[3] sensors = {sensor_co, sensor_tc, sensor_aq};

void setup()
{
    Serial.begin(9600); // initialize serial connection with a data rate of 9600 bits per second
    
    // for (int i = 0; i < sensors.size(); ++i)
    // {
    //    sensors[i].calibrate();
    // }

    pinMode(13, OUTPUT);
    //pinMode(3, OUTPUT);
    // sensor_co.heat();
}

// unsigned long next_time;

// Should I maybe figure out some way to do concurrency?
// That way I could abstract the get_value part of the co sensor by having it count the seconds by itself, and then by itself deciding whether to heat up, agreggate value or send the result.
// Then I could have a cycle time of 90 seconds, where it opens a sensor_co.getValue "thread" 
// So that would mean that this loop function just switches between the different "threads"

void delaySec(int sec)
{
    if(sec == 0) return;
    delay(1000);
    Serial.print("Waiting... ");
    Serial.println(sec);
    delaySec(sec - 1);
}

void turnOnLight(int pin)
{
    digitalWrite(pin, HIGH);
}

void turnOffLight(int pin)
{
    digitalWrite(pin, LOW);
}


void loop()
{
    // --------------- CO Sensor Get Value Cycle
    
    //sensor_co.heat();
    //turnOnLight(13);
    //delaySec(10);
    //delaySec(60);
    //delaySec(5);
    //turnOffLight(13);
    //sensor_co.cool();
    //delaySec(5);
    //delaySec(27);
    //delaySec(2);
    //sensor_co.heat();
    //delaySec(5);
    sensor_co.cool();
    int co_ppm = sensor_co.getRawValue();
    Serial.println(co_ppm);
    
    /*int limit = sensor_co.getDigitalValue();
    if(limit == HIGH) {
        turnOnLight(13);
    }
    else {
        turnOffLight(13);
    }*/
    
    
//    delaySec(2);

///// FIND OUT HOW TO OMREGNE VOLT ANALOG DATA TO PPM

   
//    Serial.println(sensor_co.getRawValue());
//    Serial.println(sensor_co.getDigitalValue());

    // Manually implements cyclic executive scheduling of sensor measurements
    // waitForTimer();
    // task_a(); task_b(); task_c();
    // co-sensor is to be measured in periods of 90 seconds, the first 60 being used for heating up, the measurement taking place 28 seconds after that. 

    // waitForTimer();
    // task_a(); task_b();

    // for (int i = 0; i < sensors.size(); ++i)
    // {
    //    if (sensors[i].period > next_time)
    //    {
    //        char[] str = sensors[i].getValue();
    //        str += sensors[i].name;
    //        Serial.println(str);
    //    }
    // }
}
