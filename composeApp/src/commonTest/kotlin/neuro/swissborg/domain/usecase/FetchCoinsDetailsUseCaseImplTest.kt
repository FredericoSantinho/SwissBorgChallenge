package neuro.swissborg.domain.usecase

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import neuro.swissborg.domain.entity.CoinDetails
import kotlin.test.Test

class FetchCoinsDetailsUseCaseImplTest {
	@Test
	fun test() = runTest {
		val symbolPairs = buildSymbolPairsList()
		val coinsDetailsList = buildCoinDetailsList()

		val getCoinsDetailsUseCase = mock<GetCoinsDetailsUseCase> {
			everySuspend { execute(symbolPairs) } returns coinsDetailsList
		}
		val saveCoinsDetailsUseCase = mock<SaveCoinsDetailsUseCase> {
			everySuspend { execute(coinsDetailsList) } returns Unit
		}

		val fetchCoinsDetailsUseCase =
			FetchCoinsDetailsUseCaseImpl(getCoinsDetailsUseCase, saveCoinsDetailsUseCase)

		fetchCoinsDetailsUseCase.execute(symbolPairs)

		verifySuspend { getCoinsDetailsUseCase.execute(symbolPairs) }
		verifySuspend { saveCoinsDetailsUseCase.execute(coinsDetailsList) }
	}

	private fun buildSymbolPairsList(): List<String> {
		return listOf("BTCUSD", "ETHUSD")
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
}