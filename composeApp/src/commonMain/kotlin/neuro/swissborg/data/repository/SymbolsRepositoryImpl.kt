package neuro.swissborg.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.JsonArray
import neuro.swissborg.data.mapper.toSymbolsDto
import neuro.swissborg.data.model.SymbolDto
import neuro.swissborg.domain.repository.SymbolsRepository

class SymbolsRepositoryImpl(private val client: HttpClient) : SymbolsRepository {
	override suspend fun getSymbols(): List<SymbolDto> {
		val response = client.get("v2/conf/pub:map:currency:label")

		val responseBody: JsonArray = response.body()

		return responseBody.toSymbolsDto()
	}
}