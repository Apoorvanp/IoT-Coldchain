package it.univaq.coldchain.persistence

import it.univaq.coldchain.api.CoolingStatus
import org.springframework.stereotype.Component

@Component
class Pressure {
    val pressureList = mutableListOf(
            PressureReport("1", "1", 1000.0, false),
            PressureReport("1", "2", 1000.0, false),
            PressureReport("2", "3", 1000.0, false)
    )

    fun find(boxId: String): PressureReport {
        return pressureList.first { it.boxId == boxId }
    }
}

data class PressureReport(val truckId: String, val boxId: String, var value: Double, var isCooling: Boolean) {
    fun incrementAndGet(): Double {
        val delta = Math.random()*10
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