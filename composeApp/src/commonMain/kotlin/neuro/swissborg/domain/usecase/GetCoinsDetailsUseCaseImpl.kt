package neuro.swissborg.domain.usecase

import neuro.swissborg.domain.entity.CoinDetails
import neuro.swissborg.domain.entity.Symbol
import neuro.swissborg.domain.entity.Ticker
import neuro.swissborg.domain.repository.SymbolsRepository
import neuro.swissborg.domain.repository.TickersRepository

class GetCoinsDetailsUseCaseImpl(
	private val tickersRepository: TickersRepository,
	private val symbolsRepository: SymbolsRepository,
) : GetCoinsDetailsUseCase {
	override suspend fun execute(symbolPairs: List<String>): List<CoinDetails> {
		val tickers = tickersRepository.getTickers(symbolPairs)
		val symbols = symbolsRepository.getSymbols()

		return tickers.map { mapToCoinDetails(it, symbols) }
	}

	private fun mapToCoinDetails(ticker: Ticker, symbols: List<Symbol>): CoinDetails {
		val symbolPair = ticker.symbolPair
		val symbol = getSymbol(symbolPair)

		val name = symbols.firstOrNull { it.symbol == symbol }?.name ?: ""
		val iconUrl = buildIconUrl(symbol)
		val price = ticker.askPrice
		val priceChangePercent = ticker.change * 100
		val priceChangeColor = getPriceChangeColor(ticker.change)

		return CoinDetails(name, symbol, iconUrl, price, priceChangePercent, priceChangeColor)
	}

	private fun getSymbol(symbolPair: String): String {
		return symbolPair.replace("USD", "")
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
		private const val GREEN = 18374966855136706560UL
		private const val RED = 18446462598732840960UL
	}
}