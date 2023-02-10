package it.univaq.coldchain.jobs

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import it.univaq.coldchain.persistence.*
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

@Component
class ColdchainReporter(val temperature: Temperature, val pressure: Pressure, val humidity: Humidity, val position: Position) {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val mapper = ObjectMapper().registerModule(KotlinModule.Builder().build())
    private val mqttDatasource = MqttClient("tcp://mqtt:1883", UUID.randomUUID().toString())

    init {
        mqttDatasource.connect()
    }


    @Scheduled(fixedRate = 5000L)
    fun report() {
        temperature.temperatureList.forEach {
            val payload = mapper.writeValueAsString(ColdchainReport(it.truckId, it.boxId, it.incrementAndGet()))
            mqttDatasource.publish("temperature", MqttMessage(payload.toByteArray()))
        }
        pressure.pressureList.forEach {
            val payload = mapper.writeValueAsString(ColdchainReport(it.truckId, it.boxId, it.incrementAndGet()))
            mqttDatasource.publish("pressure", MqttMessage(payload.toByteArray()))
        }
        humidity.humidityList.forEach {
            val payload = mapper.writeValueAsString(ColdchainReport(it.truckId, it.boxId, it.incrementAndGet()))
            mqttDatasource.publish("humidity", MqttMessage(payload.toByteArray()))
        }

    }

    @Scheduled(fixedRate = 80000L)
    fun reportPosition() {
        position.positionList.forEach {
            val payload = mapper.writeValueAsString(PositionReport(it.truckId, it.truckLocation))
            mqttDatasource.publish("position", MqttMessage(payload.toByteArray()))
        }
    }


}

data class ColdchainReport(val truckId: String, val tag: String, val value: Double)