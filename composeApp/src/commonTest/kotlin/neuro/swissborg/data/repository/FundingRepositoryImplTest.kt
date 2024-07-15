package neuro.swissborg.data.repository

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.test.runTest
import neuro.swissborg.data.repository.utils.getHttpClient
import neuro.swissborg.domain.entity.Funding
import kotlin.test.Test
import kotlin.test.assertEquals

class FundingRepositoryImplTest {
	@Test
	fun test() = runTest {
		val httpClient = getHttpClient(getFundingMockEngine())

		val fundingRepository = FundingRepositoryImpl(httpClient)

		val fundingList = fundingRepository.getFunding(buildSymbolPairsList())

		assertEquals(buildExpectedFundingList(), fundingList)
	}

	private fun buildExpectedFundingList(): List<Funding> {
		return listOf(
			Funding("BTC"),
			Funding("ETH")
		)
	}

	private fun buildSymbolPairsList(): List<String> {
		return listOf(
			"BTC",
			"ETH",
			"ADA"
		)
	}

	private fun getFundingMockEngine(): MockEngine {
		return MockEngine { request ->
			respond(
				content = ByteReadChannel(
					"""
						[
						  [
						    "fBTC",
						    0.000026427397260273972,
						    4e-7,
						    120,
						    71.51950479,
						    1.2e-7,
						    2,
						    1302.31638344,
						    0.00000139,
						    0.0139,
						    0.00000148,
						    635.01986367,
						    0.0000019,
						    1e-8,
						    null,
						    null,
						    991.39156874
						  ],
						  [
						    "fETH",
						    0.00007362191780821917,
						    0.00007362191780821917,
						    30,
						    584.20006283,
						    0.000010969,
						    2,
						    69.70764224,
						    0.000005,
						    0.05,
						    0.00005,
						    2210.63599079,
						    0.00007416,
						    5e-7,
						    null,
						    null,
						    1208.70115097
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