package neuro.swissborg.data.mapper

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import neuro.swissborg.data.model.TickerDto

fun JsonArray.toTickerDto(): TickerDto {
	val symbol = jsonArray[0].toString().replace("\"", "").substring(1)
	val askPrice = jsonArray[3].toString().toDouble()
	val change = jsonArray[6].toString().toDouble()

	return TickerDto(symbol, askPrice, change)
}

fun JsonArray.toTickersDto() = map { it.jsonArray.toTickerDto() }