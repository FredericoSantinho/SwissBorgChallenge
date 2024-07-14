package neuro.swissborg.data.mapper.network

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import neuro.swissborg.data.model.network.FundingDto
import neuro.swissborg.domain.entity.Funding

fun JsonArray.toFundingDto(): FundingDto {
	val symbol = jsonArray[0].toString().replace("\"", "").substring(1)

	return FundingDto(symbol)
}

fun JsonArray.toFundingDtoList() = map { it.jsonArray.toFundingDto() }

fun FundingDto.toDomain() = Funding(symbol)

fun List<FundingDto>.toDomain() = map { it.toDomain() }