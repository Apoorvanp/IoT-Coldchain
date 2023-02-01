package it.univaq.coldchain.persistence

import it.univaq.coldchain.api.CoolingStatus
import org.springframework.stereotype.Component

@Component
class Humidity {
    val humidityList = mutableListOf(
            HumidityReport("t1b1", 50.0, false),
            HumidityReport("t1b2", 50.0, false),
            HumidityReport("t2b1", 50.0, false)
    )

    fun find(label: String): HumidityReport {
        return humidityList.first { it.id == label }
    }
}

data class HumidityReport(val id: String, var value: Double, var isCooling: Boolean) {
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