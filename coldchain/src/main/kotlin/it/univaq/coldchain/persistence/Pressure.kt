package it.univaq.coldchain.persistence

import it.univaq.coldchain.api.CoolingStatus
import org.springframework.stereotype.Component

@Component
class Pressure {
    val pressureList = mutableListOf(
            PressureReport("t1b1", 1000.0, false),
            PressureReport("t1b2", 1000.0, false),
            PressureReport("t2b1", 1000.0, false)
    )

    fun find(label: String): PressureReport {
        return pressureList.first { it.id == label }
    }
}

data class PressureReport(val id: String, var value: Double, var isCooling: Boolean) {
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