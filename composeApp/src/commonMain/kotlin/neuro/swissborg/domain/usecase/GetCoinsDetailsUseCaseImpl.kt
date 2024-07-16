package neuro.swissborg.domain.usecase

import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import neuro.swissborg.domain.entity.CoinDetails
import neuro.swissborg.domain.entity.Funding
import neuro.swissborg.domain.entity.Symbol
import neuro.swissborg.domain.entity.Ticker
import neuro.swissborg.domain.repository.FundingRepository
import neuro.swissborg.domain.repository.SymbolsRepository
import neuro.swissborg.domain.repository.TickersRepository

class GetCoinsDetailsUseCaseImpl(
	private val tickersRepository: TickersRepository,
	private val symbolsRepository: SymbolsRepository,
	private val fundingRepository: FundingRepository,
) : GetCoinsDetailsUseCase {
	override suspend fun execute(symbolPairs: List<String>): List<CoinDetails> {
		return combine(
			flow { emit(tickersRepository.getTickers(symbolPairs)) },
			flow { emit(symbolsRepository.getSymbols()) },
			flow { emit(fundingRepository.getFunding(symbolPairs.map { getSymbol(it) })) }) { tickers, symbols, funding ->
			tickers.map { mapToCoinDetails(it, symbols, funding) }
		}.first()
	}

	private fun mapToCoinDetails(
		ticker: Ticker,
		symbols: List<Symbol>,
		funding: List<Funding>,
	): CoinDetails {
		val symbolPair = ticker.symbolPair
		val symbol = getSymbol(symbolPair)

		val name = symbols.firstOrNull { it.symbol == symbol }?.name ?: ""
		val iconUrl = buildIconUrl(symbol)
		val price = ticker.askPrice
		val priceChangePercent = ticker.change * 100
		val priceChangeColor = getPriceChangeColor(ticker.change)
		val allowsFunding = funding.any { it.symbol == symbol }

		return CoinDetails(
			name,
			symbol,
			iconUrl,
			price,
			priceChangePercent,
			priceChangeColor,
			allowsFunding
		)
	}

	private fun getSymbol(symbolPair: String): String {
		return symbolPair.replace(":USD", "").replace("USD", "")
	}

	private fun getPriceChangeColor(change: Double): ULong {
		return if (change > 0) {
			GREEN
		} else {
			RED
		}
	}

	private fun buildIconUrl(symbol: String): String {
		return "https://static.bitfinex.com/images/icons/$symbol.svg"
	}

	companion object {
		private const val GREEN = 18378557361896816640UL
		private const val RED = 18446462598732840960UL
	}
}