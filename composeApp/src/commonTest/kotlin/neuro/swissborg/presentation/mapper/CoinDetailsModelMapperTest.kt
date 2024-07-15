package neuro.swissborg.presentation.mapper

import androidx.compose.ui.graphics.Color
import neuro.swissborg.domain.entity.CoinDetails
import neuro.swissborg.presentation.model.CoinDetailsModel
import kotlin.test.Test
import kotlin.test.assertEquals

class CoinDetailsModelMapperTest {
	@Test
	fun test() {
		assertEquals(buildCoinDetailsModel(), buildCoinDetails().toPresentation())
		assertEquals(buildCoinDetailsModelList(), buildCoinDetailsList().toPresentation())
	}

	private fun buildCoinDetailsList(): List<CoinDetails> {
		return listOf(
			CoinDetails(
				"Bitcoin",
				"BTC",
				"https://static.bitfinex.com/images/icons/BTC.svg",
				100.0,
				10.0,
				18374966855136706560UL,
				true
			),
			CoinDetails(
				"Ethereum",
				"ETH",
				"https://static.bitfinex.com/images/icons/ETH.svg",
				200.0,
				-20.0,
				18446462598732840960UL,
				true
			)
		)
	}

	private fun buildCoinDetailsModelList(): List<CoinDetailsModel> {
		return listOf(
			CoinDetailsModel(
				"Bitcoin",
				"BTC",
				"https://static.bitfinex.com/images/icons/BTC.svg",
				"$ 100.0",
				"+10.0%",
				Color(18374966855136706560UL),
				true
			),
			CoinDetailsModel(
				"Ethereum",
				"ETH",
				"https://static.bitfinex.com/images/icons/ETH.svg",
				"$ 200.0",
				"-20.0%",
				Color(18446462598732840960UL),
				true
			)
		)
	}

	private fun buildCoinDetailsModel(): CoinDetailsModel {
		return CoinDetailsModel(
			"Bitcoin",
			"BTC",
			"https://static.bitfinex.com/images/icons/BTC.svg",
			"$ 100.0",
			"+10.0%",
			Color(18374966855136706560UL),
			true
		)
	}

	private fun buildCoinDetails(): CoinDetails {
		return CoinDetails(
			"Bitcoin",
			"BTC",
			"https://static.bitfinex.com/images/icons/BTC.svg",
			100.0,
			10.0,
			18374966855136706560UL,
			true
		)
	}
}