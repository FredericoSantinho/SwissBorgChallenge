package neuro.swissborg.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TickerDto(val symbol: String, val askPrice: Double, val change: Double)