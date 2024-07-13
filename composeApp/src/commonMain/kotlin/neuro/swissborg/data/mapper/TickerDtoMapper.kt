package neuro.swissborg.data.mapper

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import neuro.swissborg.data.model.TickerDto
import neuro.swissborg.domain.entity.Ticker

fun JsonArray.toTickerDto(): TickerDto {
	val symbol = jsonArray[0].toString().replace("\"", "").substring(1)
	val askPrice = jsonArray[3].toString().toDouble()
	val change = jsonArray[6].toString().toDouble()

	return TickerDto(symbol, askPrice, change)
}

fun JsonArray.toTickersDto() = map { it.jsonArray.toTickerDto() }

fun TickerDto.toDomain() = Ticker(symbol, askPrice, change)

fun List<TickerDto>.toDomain() = map { it.toDomain() }