package neuro.swissborg.data.repository

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.test.runTest
import neuro.swissborg.data.repository.utils.getHttpClient
import neuro.swissborg.domain.entity.Ticker
import kotlin.test.Test
import kotlin.test.assertEquals

class TickersRepositoryImplTest {
	@Test
	fun test() = runTest {
		val httpClient = getHttpClient(getTickersMockEngine())

		val tickersRepository = TickersRepositoryImpl(httpClient)

		val tickers = tickersRepository.getTickers(buildSymbolPairsList())

		assertEquals(buildExpectedTickersList(), tickers)
	}

	private fun buildSymbolPairsList(): List<String> {
		return listOf(
			"BTCUSD",
			"ETHUSD",
			"ADAUSD"
		)
	}

	private fun buildExpectedTickersList(): List<Ticker> {
		return listOf(
			Ticker("BTCUSD", 61448.0, 0.02529998),
			Ticker("ETHUSD", 3283.5, 0.02621617),
			Ticker("ADAUSD", 0.43773, -0.00738234)
		)
	}

	private fun getTickersMockEngine(): MockEngine {
		return MockEngine { request ->
			respond(
				content = ByteReadChannel(
					"""
						[
							[
								"tBTCUSD",
								61447,
								9.48793561,
								61448,
								3.92182253,
								1516,
								0.02529998,
								61437,
								1024.49357155,
								62080,
								59469
							],
							[
								"tETHUSD",
								3283.4,
								111.10942019,
								3283.5,
								240.64673153,
								83.8,
								0.02621617,
								3280.3,
								2010.20268945,
								3308,
								3172.5
							],
							[
								"tADAUSD",
								0.43723,
								234469.34998626,
								0.43773,
								201571.08375389,
								-0.00325,
								-0.00738234,
								0.43699,
								3002199.38540546,
								0.44027,
								0.42596
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