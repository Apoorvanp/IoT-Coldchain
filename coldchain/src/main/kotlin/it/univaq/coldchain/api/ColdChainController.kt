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
    @GetMapping("/toggle/temperature/{label}/status")
    fun temperatureStatus(@PathVariable label: String): CoolingStatus {
        return CoolingStatus(temperature.find(label).isCooling)
    }

    @PostMapping("/temperature/{label}/toggle")
    fun toggleTemperature(@PathVariable label: String): CoolingStatus {
        return temperature.find(label).toggle()
    }

    @GetMapping("/toggle/humidity/{label}/status")
    fun humidityStatus(@PathVariable label: String): CoolingStatus {
        return CoolingStatus(humidity.find(label).isCooling)
    }

    @PostMapping("/humidity/{label}/toggle")
    fun toggleHumidity(@PathVariable label: String): CoolingStatus {
        return humidity.find(label).toggle()
    }

    @GetMapping("/toggle/pressure/{label}/status")
    fun pressureStatus(@PathVariable label: String): CoolingStatus {
        return CoolingStatus(pressure.find(label).isCooling)
    }

    @PostMapping("/pressure/{label}/toggle")
    fun togglePressure(@PathVariable label: String): CoolingStatus {
        return pressure.find(label).toggle()
    }
}

data class CoolingStatus(val isCooling: Boolean)
