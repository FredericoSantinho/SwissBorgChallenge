package neuro.swissborg.data.mapper

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import neuro.swissborg.data.model.network.SymbolDto
import neuro.swissborg.domain.entity.Symbol

fun JsonArray.toSymbolDto(): SymbolDto {
	val symbol = jsonArray[0].toString().replace("\"", "")
	val name = jsonArray[1].toString().replace("\"", "")

	return SymbolDto(symbol, name)
}

fun JsonArray.toSymbolsDto(): List<SymbolDto> =
	jsonArray[0].jsonArray.map { it.jsonArray.toSymbolDto() }

fun SymbolDto.toDomain() = Symbol(symbol, name)

fun List<SymbolDto>.toDomain() = map { it.toDomain() }