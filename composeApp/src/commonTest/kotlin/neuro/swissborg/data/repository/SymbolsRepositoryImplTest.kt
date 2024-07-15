package neuro.swissborg.data.repository

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.test.runTest
import neuro.swissborg.data.repository.utils.getHttpClient
import neuro.swissborg.domain.entity.Symbol
import kotlin.test.Test
import kotlin.test.assertEquals

class SymbolsRepositoryImplTest {
	@Test
	fun testGetSymbols() = runTest {
		val httpClient = getHttpClient(getSymbolsMockEngine())

		val symbolsRepository = SymbolsRepositoryImpl(httpClient)

		val symbols = symbolsRepository.getSymbols()

		assertEquals(buildExpectedSymbolsList(), symbols)
	}

	private fun buildExpectedSymbolsList(): List<Symbol> {
		return listOf(
			Symbol("1INCH", "1INCH"),
			Symbol("AAVE", "AAVE"),
			Symbol("ADA", "Cardano ADA")
		)
	}

	private fun getSymbolsMockEngine(): MockEngine {
		return MockEngine { request ->
			respond(
				content = ByteReadChannel(
					"""
						[
							[
								[
									"1INCH",
									"1INCH"
								],
								[
									"AAVE",
									"AAVE"
								],
								[
									"ADA",
									"Cardano ADA"
								]
							]
						]
					""".trimIndent()
				),
				status = HttpStatusCode.OK,
				headers = headersOf(HttpHeaders.ContentType, "application/json")
			)
		}
	}
}