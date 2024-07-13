package neuro.swissborg.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import neuro.swissborg.domain.repository.TickersRepository

class AppViewModel(private val tickersRepository: TickersRepository) : ViewModel() {
	fun onClick() {
		viewModelScope.launch {
			val tickers = tickersRepository.getTickers(listOf("BTCUSD", "ETHUSD"))

			println("blaz $tickers")
		}
	}
}