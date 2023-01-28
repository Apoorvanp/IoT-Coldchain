import random
import numpy as np
import json
import time
import paho.mqtt.client as mqtt
client = mqtt.Client()
client.connect("localhost", 1883)

#PRESETS
#PFIZER VACCINE

temp_lowerbound = 2
temp_upperbound = 10
temp_delta = 0.02
temperature = 2

humidity_lowerbound = 50
humidity_upperbound = 100
humidity_delta = 0.0125
humidity = 50

pressure_lowerbound = 1000
pressure_upperbound = 1100
pressure_delta = 0.0025
pressure = 1000

#DEFAULT
cooling_soln = False #OFF




key = "t1b1"
topics = ["temperature", "humidity", "pressure"]

while True:

    temp_var=temp_delta*(random.random()*((temp_upperbound-temp_lowerbound)+temp_lowerbound)*100)/100
    humidity_var=humidity_delta*(random.random()*((humidity_upperbound-humidity_lowerbound)+humidity_lowerbound)*100)/100
    pressure_var=pressure_delta*(random.random()*((pressure_upperbound-pressure_lowerbound)+pressure_lowerbound)*100)/100
   


    if cooling_soln == False:
        temperature = temperature+temp_var 
        humidity = humidity+humidity_var
        pressure = pressure+pressure_var
    else:
        temperature = temperature-temp_var
        humidity = humidity-humidity_var
        pressure = pressure-pressure_var

    payload = {"temperature": temperature, "humidity": humidity, "pressure": pressure}
    for topic, value in payload.items():
        ret, mid = client.publish(topic, json.dumps({"id": key, "value": round(value, 2)}))
        if ret == mqtt.MQTT_ERR_SUCCESS:
            print("Data published successfully to topic: ", topic)
        else:
            print("Error while publishing data to topic: ", topic)
    print("Published Payload:", payload)

    #Keep at 2 for testing, at 300 for real
    time.sleep(10)
# app.run(8000)

