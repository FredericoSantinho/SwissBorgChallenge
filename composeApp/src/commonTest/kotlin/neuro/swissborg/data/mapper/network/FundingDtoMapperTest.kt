package neuro.swissborg.data.mapper.network

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import neuro.swissborg.data.model.network.FundingDto
import neuro.swissborg.domain.entity.Funding
import kotlin.test.Test
import kotlin.test.assertEquals

class FundingDtoMapperTest {
	@Test
	fun test() {
		assertEquals(buildFunding(), buildFundingDto().toDomain())
		assertEquals(buildFundingList(), buildFundingDtoList().toDomain())

		assertEquals(buildFundingDto(), buildFundingJsonArray().toFundingDto())
		assertEquals(buildFundingDtoList(), buildFundingListJsonArray().toFundingDtoList())
	}

	private fun buildFundingListJsonArray(): JsonArray {
		return Json.decodeFromString(
			"""
				[
				  [
				    "fBTC",
				    0.000026424657534246574,
				    2.5e-7,
				    30,
				    72.1028594,
				    4.7397260273973e-7,
				    2,
				    830.50495206,
				    0.00000129,
				    0.0129,
				    0.00000139,
				    333.86598605,
				    0.00000148,
				    1e-8,
				    null,
				    null,
				    988.53155652
				  ],
				  [
				    "fETH",
				    0.00007446575342465753,
				    0.00007446575342465753,
				    30,
				    459.58173303,
				    0.00003999,
				    2,
				    690.28998604,
				    0.0000391,
				    0.391,
				    0.00004,
				    2794.61501383,
				    0.00007488,
				    5e-7,
				    null,
				    null,
				    1325.3384966
				  ]
				]
			"""
		)
	}

	private fun buildFundingJsonArray(): JsonArray {
		return Json.decodeFromString(
			"""
				  [
				    "fBTC",
				    0.000026424657534246574,
				    2.5e-7,
				    30,
				    72.1028594,
				    4.7397260273973e-7,
				    2,
				    830.50495206,
				    0.00000129,
				    0.0129,
				    0.00000139,
				    333.86598605,
				    0.00000148,
				    1e-8,
				    null,
				    null,
				    988.53155652
				  ]
			""".trimIndent()
		)
	}

	private fun buildFundingDtoList(): List<FundingDto> {
		return listOf(
			FundingDto("BTC"),
			FundingDto("ETH")
		)
	}

	private fun buildFundingList(): List<Funding> {
		return listOf(
			Funding("BTC"),
			Funding("ETH")
		)
	}

	private fun buildFundingDto(): FundingDto {
		return FundingDto("BTC")
	}

	private fun buildFunding(): Funding {
		return Funding("BTC")
	}
}