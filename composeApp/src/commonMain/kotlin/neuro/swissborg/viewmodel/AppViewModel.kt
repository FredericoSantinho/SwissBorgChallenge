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
			val symbolPairsList = getSymbolPairsList()
			val tickers = tickersRepository.getTickers(symbolPairsList)

			println("blaz $tickers")

			val symbols = symbolsRepository.getSymbols()
			println("blaz2 $symbols")

			println("blaz3 ${Color.Green.value} ${Color.Red.value}")

			val execute = getCoinsDetailsUseCase.execute(symbolPairsList)

			println("blaz4 $execute")
		}
	}

	private fun getSymbolPairsList(): List<String> {
		val symbolsQuery =
			"?symbols=tBTCUSD,tETHUSD,tCHSB:USD,tLTCUSD,tXRPUSD,tDSHUSD,tRRTUSD,tEOSUSD,tSANUSD,tDATUSD,tSNTUSD,tDOGE:USD,tLUNA:USD,tMATIC:USD,tNEXO:USD, tOCEAN:USD, tBEST:USD, tAAVE:USD, tPLUUSD, tFILUSD"
				.replace("?symbols=", "")
		val split = symbolsQuery.split(",").map { it.substring(1) }

		return split
	}
}