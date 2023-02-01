package it.univaq.coldchain.persistence

import it.univaq.coldchain.api.CoolingStatus
import org.springframework.stereotype.Component

@Component
class Temperature {
    val temperatureList = mutableListOf(
            TemperatureReport("t1b1", 0.0, false),
            TemperatureReport("t1b2", 0.0, false),
            TemperatureReport("t2b1", 0.0, false)
    )

    fun find(label: String): TemperatureReport {
        return temperatureList.first { it.id == label }
    }
}

data class TemperatureReport(val id: String, var value: Double, var isCooling: Boolean) {
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