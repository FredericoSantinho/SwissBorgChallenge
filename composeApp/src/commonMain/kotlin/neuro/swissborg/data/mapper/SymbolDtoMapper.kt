package neuro.swissborg.data.mapper

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import neuro.swissborg.data.model.SymbolDto

fun JsonArray.toSymbolDto(): SymbolDto {
	val symbol = jsonArray[0].toString().replace("\"", "")
	val name = jsonArray[1].toString().replace("\"", "")

	return SymbolDto(symbol, name)
}

fun JsonArray.toSymbolsDto(): List<SymbolDto> =
	jsonArray[0].jsonArray.map { it.jsonArray.toSymbolDto() }