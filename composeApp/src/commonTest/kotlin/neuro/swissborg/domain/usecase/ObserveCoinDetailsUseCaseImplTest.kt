package neuro.swissborg.domain.usecase

import app.cash.turbine.test
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import neuro.swissborg.domain.entity.CoinDetails
import neuro.swissborg.domain.repository.CoinDetailsRepository
import kotlin.test.Test
import kotlin.test.assertEquals

class ObserveCoinDetailsUseCaseImplTest {
	@Test
	fun test() = runTest {
		val coinDetailsList = buildCoinDetailsList()

		val coinDetailsRepository = mock<CoinDetailsRepository> {
			everySuspend { observeAll() } returns flowOf(coinDetailsList)
		}

		val observeCoinDetailsUseCase = ObserveCoinDetailsUseCaseImpl(coinDetailsRepository)

		observeCoinDetailsUseCase.execute().test {
			assertEquals(coinDetailsList, awaitItem())
			awaitComplete()
		}

		verifySuspend {
			coinDetailsRepository.observeAll()
		}
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