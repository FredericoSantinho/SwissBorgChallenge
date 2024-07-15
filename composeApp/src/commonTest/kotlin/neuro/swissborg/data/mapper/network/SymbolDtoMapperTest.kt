package neuro.swissborg.data.mapper.network

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import neuro.swissborg.data.model.network.SymbolDto
import neuro.swissborg.domain.entity.Symbol
import kotlin.test.Test
import kotlin.test.assertEquals

class SymbolDtoMapperTest {
	@Test
	fun test() {
		assertEquals(buildSymbol(), buildSymbolDto().toDomain())
		assertEquals(buildSymbolList(), buildSymbolDtoList().toDomain())

		assertEquals(buildSymbolDto(), buildSymbolJsonArray().toSymbolDto())
		assertEquals(buildSymbolDtoList(), buildSymbolListJsonArray().toSymbolsDto())
	}

	private fun buildSymbolListJsonArray(): JsonArray {
		return Json.decodeFromString(
			"""
				[
				  [
				    [
				      "BTC",
				      "Bitcoin"
				    ],
				    [
				      "ETH",
				      "Ethereum"
				    ]
				  ]
				]
			""".trimIndent()
		)
	}

	private fun buildSymbolJsonArray(): JsonArray {
		return Json.decodeFromString(
			"""
	    	[
	    	  "BTC",
	    	  "Bitcoin"
	    	]
			""".trimIndent()
		)
	}

	private fun buildSymbolDtoList(): List<SymbolDto> {
		return listOf(
			SymbolDto("BTC", "Bitcoin"),
			SymbolDto("ETH", "Ethereum")
		)
	}

	private fun buildSymbolList(): List<Symbol> {
		return listOf(
			Symbol("BTC", "Bitcoin"),
			Symbol("ETH", "Ethereum")
		)
	}

	private fun buildSymbolDto(): SymbolDto {
		return SymbolDto("BTC", "Bitcoin")
	}

	private fun buildSymbol(): Symbol {
		return Symbol("BTC", "Bitcoin")
	}
}