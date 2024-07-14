package neuro.swissborg.domain.usecase

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import neuro.swissborg.domain.entity.CoinDetails
import neuro.swissborg.domain.entity.Funding
import neuro.swissborg.domain.entity.Symbol
import neuro.swissborg.domain.entity.Ticker
import neuro.swissborg.domain.repository.FundingRepository
import neuro.swissborg.domain.repository.SymbolsRepository
import neuro.swissborg.domain.repository.TickersRepository
import kotlin.test.Test
import kotlin.test.assertEquals

class GetCoinsDetailsUseCaseImplTest {
	@Test
	fun test() = runTest {
		val tickersRepository = mock<TickersRepository> {
			everySuspend { getTickers(buildSymbolPairsList()) } returns buildTickersList()
		}
		val symbolsRepository = mock<SymbolsRepository> {
			everySuspend { getSymbols() } returns buildSymbolsList()
		}
		val fundingRepository = mock<FundingRepository> {
			everySuspend { getFunding(buildSymbolsList().map { it.symbol }) } returns buildFundingList()
		}

		val getCoinsDetailsUseCase =
			GetCoinsDetailsUseCaseImpl(tickersRepository, symbolsRepository, fundingRepository)

		val coinDetailsList = getCoinsDetailsUseCase.execute(buildSymbolPairsList())

		verifySuspend {
			tickersRepository.getTickers(buildSymbolPairsList())
			symbolsRepository.getSymbols()
		}

		assertEquals(expectedCoinDetailsList(), coinDetailsList)
	}

	private fun expectedCoinDetailsList(): List<CoinDetails> {
		return listOf(
			CoinDetails(
				"Bitcoin",
				"BTC",
				"https://static.bitfinex.com/images/icons/BTC.svg",
				100.0,
				10.0,
				18378557361896816640UL,
				true
			),
			CoinDetails(
				"Ethereum",
				"ETH",
				"https://static.bitfinex.com/images/icons/ETH.svg",
				200.0,
				-20.0,
				18446462598732840960UL,
				false
			)
		)
	}


	private fun buildFundingList(): List<Funding> {
		return listOf(Funding("BTC"))
	}

	private fun buildSymbolsList(): List<Symbol> {
		return listOf(Symbol("BTC", "Bitcoin"), Symbol("ETH", "Ethereum"))
	}

	private fun buildTickersList(): List<Ticker> {
		return listOf(
			Ticker("BTCUSD", 100.0, 0.1),
			Ticker("ETHUSD", 200.0, -0.2)
		)
	}

	private fun buildSymbolPairsList(): List<String> {
		return listOf("BTCUSD", "ETHUSD")
	}
}