package it.univaq.coldchain.persistence

import it.univaq.coldchain.api.CoolingStatus
import org.springframework.stereotype.Component

@Component
class Temperature {
    val temperatureList = mutableListOf(
            TemperatureReport("1", "1", 0.0, false),
            TemperatureReport("1", "2", 0.0, false),
            TemperatureReport("2", "3", 0.0, false)
    )

    fun find(boxId: String): TemperatureReport {
        return temperatureList.first() { it.boxId == boxId }
    }
}

data class TemperatureReport(val truckId: String, val boxId: String, var value: Double, var isCooling: Boolean) {
    fun incrementAndGet(): Double {
        val delta = Math.random()
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