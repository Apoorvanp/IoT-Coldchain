import random
import json
import time
import paho.mqtt.client as mqtt
client = mqtt.Client()
client.connect("localhost", 1883)

key = "t2b1"
topics = ["temperature", "humidity", "pressure"]

while True:
    temperature = round(random.uniform(2, 10), 3) # Degree Celsius
    humidity = round(random.uniform(50, 100), 3) # Percent Relative Humidity
    pressure = round(random.uniform(1000, 1100), 3) # Millibars
    payload = {"temperature": temperature, "humidity": humidity, "pressure": pressure}
    for topic, value in payload.items():
        client.publish(topic, json.dumps({key: value}))
        ret, mid = client.publish(topic, json.dumps({key: value}))
        if ret == mqtt.MQTT_ERR_SUCCESS:
            print("Data published successfully to topic: ", topic)
        else:
            print("Error while publishing data to topic: ", topic)
    print("Published Payload:", payload)
    time.sleep(2)