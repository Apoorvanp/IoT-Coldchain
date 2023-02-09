package it.univaq.coldchain.persistence

import it.univaq.coldchain.api.CoolingStatus
import org.springframework.stereotype.Component

@Component
class Humidity {
    val humidityList = mutableListOf(
            HumidityReport("1", "1", 50.0, false),
            HumidityReport("1", "2", 50.0, false),
            HumidityReport("2", "3", 50.0, false)
    )

    fun find( boxId: String): HumidityReport {
        return humidityList.first { it.boxId == boxId }
    }
}

data class HumidityReport(val truckId: String, val boxId: String, var value: Double, var isCooling: Boolean) {
    fun incrementAndGet(): Double {
        val delta = Math.random() * 3
        value = if (isCooling) {
            ("%.2f".format(value - delta).toDouble())
        } else {
            ("%.2f".format(value + delta).toDouble())
        }
        return value
    }

    fun toggle(): CoolingStatus {
        isCooling = isCooling.not()
        return CoolingStatus(isCooling)
    }

}