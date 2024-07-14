package neuro.swissborg.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.JsonArray
import neuro.swissborg.data.mapper.network.toDomain
import neuro.swissborg.data.mapper.network.toFundingDtoList
import neuro.swissborg.domain.entity.Funding
import neuro.swissborg.domain.repository.FundingRepository

class FundingRepositoryImpl(private val client: HttpClient) : FundingRepository {
	override suspend fun getFunding(symbols: List<String>): List<Funding> {
		val symbolsQuery = symbols.joinToString(separator = ",") { "f$it" }

		val response = client.get("v2/tickers?symbols=$symbolsQuery")

		val responseBody: JsonArray = response.body()

		return responseBody.toFundingDtoList().toDomain()
	}
}