package neuro.swissborg.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import neuro.swissborg.domain.repository.SymbolsRepository
import neuro.swissborg.domain.repository.TickersRepository
import neuro.swissborg.domain.usecase.GetCoinsDetailsUseCase

class AppViewModel(
	private val tickersRepository: TickersRepository,
	private val symbolsRepository: SymbolsRepository,
	private val getCoinsDetailsUseCase: GetCoinsDetailsUseCase,
) : ViewModel() {
	fun onClick() {
		viewModelScope.launch {
			val symbolsList = listOf("BTCUSD", "ETHUSD")
			val tickers = tickersRepository.getTickers(symbolsList)

			println("blaz $tickers")

			val symbols = symbolsRepository.getSymbols()
			println("blaz2 $symbols")

			println("blaz3 ${Color.Green.value} ${Color.Red.value}")

			val execute = getCoinsDetailsUseCase.execute(symbolsList)

			println("blaz4 $execute")
		}
	}
}