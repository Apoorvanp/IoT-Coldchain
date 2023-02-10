package it.univaq.coldchain.persistence

import org.springframework.stereotype.Component

@Component
class Position {
    val positionList = mutableListOf(
            PositionReport("1", "Italy" ),
            PositionReport("2", "Finland"),
            PositionReport("3", "Spain")
    )
}

data class PositionReport(val truckId: String, val truckLocation: String, val value: Int = 5)