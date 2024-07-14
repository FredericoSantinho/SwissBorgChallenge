package neuro.swissborg.domain.usecase

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import neuro.swissborg.domain.entity.CoinDetails
import neuro.swissborg.domain.repository.CoinDetailsRepository
import kotlin.test.Test

class SaveCoinsDetailsUseCaseImplTest {
	@Test
	fun test() = runTest {
		val coinsDetails = buildCoinDetailsList()
		val coinDetailsRepository = mock<CoinDetailsRepository> {
			everySuspend { saveAll(coinsDetails) } returns Unit
		}

		val saveCoinsDetailsUseCase = SaveCoinsDetailsUseCaseImpl(coinDetailsRepository)

		saveCoinsDetailsUseCase.execute(coinsDetails)

		verifySuspend {
			coinDetailsRepository.saveAll(coinsDetails)
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
				18374966855136706560UL
			),
			CoinDetails(
				"Ethereum",
				"ETH",
				"https://static.bitfinex.com/images/icons/ETH.svg",
				200.0,
				-20.0,
				18446462598732840960UL
			)
		)
	}
}