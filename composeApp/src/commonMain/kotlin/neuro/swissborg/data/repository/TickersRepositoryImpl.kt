package neuro.swissborg.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.JsonArray
import neuro.swissborg.data.mapper.toTickersDto
import neuro.swissborg.data.model.TickerDto
import neuro.swissborg.domain.repository.TickersRepository

class TickersRepositoryImpl(private val client: HttpClient) : TickersRepository {
	override suspend fun getTickers(symbols: List<String>): List<TickerDto> {
		val symbolsQuery = symbols.joinToString(separator = ",") { "t$it" }

		val response = client.get("v2/tickers?symbols=$symbolsQuery")

		val responseBody: JsonArray = response.body()

		return responseBody.toTickersDto()
	}
}