package neuro.swissborg.data.mapper.network

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import neuro.swissborg.data.model.network.TickerDto
import neuro.swissborg.domain.entity.Ticker
import kotlin.test.Test
import kotlin.test.assertEquals

class TickerDtoMapperTest {
	@Test
	fun test() {
		assertEquals(buildTicker(), buildTickerDto().toDomain())
		assertEquals(buildTickerList(), buildTickerDtoList().toDomain())

		assertEquals(buildTickerDto(), buildTickerJsonArray().toTickerDto())
		assertEquals(buildTickerDtoList(), buildTickerListJsonArray().toTickersDto())
	}

	private fun buildTickerListJsonArray(): JsonArray {
		return Json.decodeFromString(
			"""
				[
				  [
				    "tBTCUSD",
				    63728,
				    5.02899793,
				    63733,
				    4.08778389,
				    3556,
				    0.05910708,
				    63718,
				    1461.07998182,
				    63786,
				    60069
				  ],
				  [
				    "tETHUSD",
				    3399.6,
				    169.91474893,
				    3400.3,
				    100.934708,
				    208.4,
				    0.06519631,
				    3404.9,
				    2324.68042855,
				    3408,
				    3192
				  ]
				]
		""".trimIndent()
		)
	}

	private fun buildTickerJsonArray(): JsonArray {
		return Json.decodeFromString(
			"""
				  [
				    "tBTCUSD",
				    63728,
				    5.02899793,
				    63733,
				    4.08778389,
				    3556,
				    0.05910708,
				    63718,
				    1461.07998182,
				    63786,
				    60069
				  ]
			""".trimIndent()
		)
	}

	private fun buildTickerDtoList(): List<TickerDto> {
		return listOf(
			TickerDto("BTCUSD", 63733.0, 0.05910708),
			TickerDto("ETHUSD", 3400.3, 0.06519631)
		)
	}

	private fun buildTickerList(): List<Ticker> {
		return listOf(
			Ticker("BTCUSD", 63733.0, 0.05910708),
			Ticker("ETHUSD", 3400.3, 0.06519631)
		)
	}

	private fun buildTickerDto(): TickerDto {
		return TickerDto("BTCUSD", 63733.0, 0.05910708)
	}

	private fun buildTicker(): Ticker {
		return Ticker("BTCUSD", 63733.0, 0.05910708)
	}
}