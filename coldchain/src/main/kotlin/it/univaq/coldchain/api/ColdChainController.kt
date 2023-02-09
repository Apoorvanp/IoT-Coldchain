package it.univaq.coldchain.api

import it.univaq.coldchain.persistence.Humidity
import it.univaq.coldchain.persistence.Pressure
import it.univaq.coldchain.persistence.Temperature
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class ColdChainController(val temperature: Temperature, val humidity: Humidity, val pressure: Pressure) {
    @GetMapping("/toggle/temperature/{boxId}/status")
    fun temperatureStatus(@PathVariable boxId: String): CoolingStatus {
        return CoolingStatus(temperature.find(boxId).isCooling)
    }

    @PostMapping("/temperature/{boxId}/toggle")
    fun toggleTemperature(@PathVariable boxId: String): CoolingStatus {
        return temperature.find(boxId).toggle()
    }

    @GetMapping("/toggle/humidity/{boxId}/status")
    fun humidityStatus(@PathVariable boxId: String): CoolingStatus {
        return CoolingStatus(humidity.find(boxId).isCooling)
    }

    @PostMapping("/humidity/{boxId}/toggle")
    fun toggleHumidity(@PathVariable boxId: String): CoolingStatus {
        return humidity.find(boxId).toggle()
    }

    @GetMapping("/toggle/pressure/{boxId}/status")
    fun pressureStatus(@PathVariable boxId: String): CoolingStatus {
        return CoolingStatus(pressure.find(boxId).isCooling)
    }

    @PostMapping("/pressure/{boxId}/toggle")
    fun togglePressure(@PathVariable boxId: String): CoolingStatus {
        return pressure.find(boxId).toggle()
    }
}

data class CoolingStatus(val isCooling: Boolean)
