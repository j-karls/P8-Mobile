
aqibp = (0, 50, 100, 200, 300, 400)

def aqicalc1(conc, conc_low, conc_high, index_low, index_high):
    return (((index_high-index_low)/(conc_high-conc_low))*(conc-conc_low))+index_low

def aqicalc2(conc, conc_low, conc_high, index_low, index_high):
    return (((index_low-index_high)/(conc_high-conc_low))*(conc-conc_high))+index_low

def co2aqi(input):
    co2bp = (0, 1000, 5000, 40000, 141000, 200000)
    i = 0
    if(input>200000):
        return aqicalc1(input, co2bp[4], co2bp[5], aqibp[4], aqibp[5])
    while(i<5):
        if(input >= co2bp[i] and input <= co2bp[i+1]):
            return aqicalc1(input, co2bp[i], co2bp[i+1], aqibp[i], aqibp[i+1])
        i += 1

def coaqi(input):
    cobp = (0, 11.65, 25, 75, 150, 200)
    i = 0
    if(input>200):
        return aqicalc1(input, cobp[4], cobp[5], aqibp[4], aqibp[5])
    while(i<5):
        if(input >= cobp[i] and input <= cobp[i+1]):
            return aqicalc1(input, cobp[i], cobp[i+1], aqibp[i], aqibp[i+1])
        i += 1

def tempaqi(input):
    templowbp = (20, 18, 7, -14, -31, -40)
    temphighbp = (20, 23, 29, 33, 37, 42)
    i = 0
    if(input<-40):
        return (aqicalc2(input, templowbp[5], templowbp[4], aqibp[4], aqibp[5]), "low")
    elif(input>42):
        return (aqicalc1(input, temphighbp[4], temphighbp[5], aqibp[4], aqibp[5]), "high")
    elif(input<=20):
        while(i<5):
            if(input <= templowbp[i] and input >= templowbp[i+1]):
                return (aqicalc2(input, templowbp[i+1], templowbp[i], aqibp[i], aqibp[i+1]), "low")
            i += 1
    elif(input>20):
        while(i<5):
            if(input >= temphighbp[i] and input <= temphighbp[i+1]):
                return (aqicalc1(input, temphighbp[i], temphighbp[i+1], aqibp[i], aqibp[i+1]), "high")
            i += 1

def humiaqi(input):
    humilowbp = (50, 40, 30 ,0)
    humihighbp = (50, 60, 70, 100)
    i = 0
    if(input<=50):
        while(i<3):
            if(input <= humilowbp[i] and input >= humilowbp[i+1]):
                return (aqicalc2(input, humilowbp[i+1], humilowbp[i], aqibp[i], aqibp[i+1]), "low")
            i += 1
    elif(input>50):
        while(i<3):
            if(input >= humihighbp[i] and input <= humihighbp[i+1]):
                return (aqicalc1(input, humihighbp[i], humihighbp[i+1], aqibp[i], aqibp[i+1]), "high")
            i += 1

def aqimessageconc(maxfactor, temp_level, humi_level):
    switcher = {
        'co2': {'problem':"CO2_CONCERNING", 'solution':("OPEN_WINDOW", "TURN_ON_AC")},
        'co': {'problem':"CO_CONCERNING", 'solution':("OPEN_WINDOW", "TURN_ON_AC", "TURN_OFF_FIRE")},
        'temperature': {'problem':"TEMPERATURE_LOW", 'solution':("OPEN_WINDOW","TURN_ON_HEATER")}
        if temp_level == 'low' 
        else{'problem':"TEMPERATURE_HIGH", 'solution':("OPEN_WINDOW", "TURN_ON_AC")} ,
        'humidity': {'problem':"HUMIDITY_CONCERNING", 'solution':("OPEN_WINDOW", "TURN_ON_HUMIDIFIER")}
        if humi_level == 'low'
        else {'problem':"HUMIDITY_CONCERNING", 'solution':("OPEN_WINDOW", "TURN_ON_DEHUMIDIFIER")},
    }
    return switcher.get(maxfactor, "Invalid input")

def aqimessageleth(maxfactor, temp_level):
    switcher = {
        'co2': {'problem':"CO2_LETHAL", 'solution':("OPEN_WINDOW", "TURN_ON_AC", "LEAVE_ROOM")},
        'co': {'problem':"CO_LETHAl", 'solution':("OPEN_WINDOW", "TURN_ON_AC", "TURN_OFF_FIRE", "LEAVE_ROOM")},
        'temperature': {'problem':"TEMPERATURE_LETHAL_LOW", 'solution':("OPEN_WINDOW","TURN_ON_HEATER", "LEAVE_ROOM")}
        if temp_level == 'low' 
        else{'problem':"TEMPERATURE_LETHAL_HIGH", 'solution':("OPEN_WINDOW", "TURN_ON_AC", "LEAVE_ROOM")} ,
    }
    return switcher.get(maxfactor, "Invalid input")

def aqicompare(co2val, coval, tempval, humival):
    temperature = tempaqi(tempval)
    humidity = humiaqi(humival)
    aqivalues = {'co2':co2aqi(co2val), 'co':coaqi(coval), 'temperature':temperature[0], 'humidity':humidity[0]}
    maxaqi = max(zip( aqivalues.values(), aqivalues.keys()))
    aqivalues.update({'max': maxaqi})
    if(maxaqi[0]<=50):
        aqivalues.update({'problem': "ALL_FACTORS_OK", 'solution': "NO_ACTION_NEEDED"})
    elif(maxaqi[0]>50 and maxaqi[0]<=300):
        aqivalues.update(aqimessageconc(maxaqi[1], temperature[1], humidity[1]))
    else:
        aqivalues.update(aqimessageleth(maxaqi[1], temperature[1]))
    return aqivalues

