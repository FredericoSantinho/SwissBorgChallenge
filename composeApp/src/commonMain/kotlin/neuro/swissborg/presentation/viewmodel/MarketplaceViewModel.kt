package neuro.swissborg.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import neuro.swissborg.domain.usecase.FetchPeriodicallyCoinsDetailsUseCase
import neuro.swissborg.domain.usecase.ObserveCoinDetailsUseCase
import neuro.swissborg.presentation.mapper.toPresentation
import neuro.swissborg.presentation.model.CoinDetailsModel

class MarketplaceViewModel(
	private val observeCoinDetailsUseCase: ObserveCoinDetailsUseCase,
	private val fetchPeriodicallyCoinsDetailsUseCase: FetchPeriodicallyCoinsDetailsUseCase,
	private val mainDispatcher: CoroutineDispatcher,
) : ViewModel() {

	var state by mutableStateOf(MarketplaceState(isLoading = true))
		private set

	private val coinsDetailsModels = MutableStateFlow<List<CoinDetailsModel>>(emptyList())
	private val searchTerm = MutableStateFlow("")

	private var fetchCoinsDetailsJob: Job = Job()

	init {
		observeCoinsDetails()
		startFetchingCoinsDetails()
		setupStateUpdateWithSearchQuery()
	}

	fun onSearchTerm(searchTerm: String) {
		this.searchTerm.value = searchTerm
	}

	private fun setupStateUpdateWithSearchQuery() {
		coinsDetailsModels.combine(searchTerm) { coinModels, searchTerm ->
			coinModels.let {
				val filteredCoins = coinModels.filter { filterCoinsDetailsModels(it, searchTerm) }
				setCoinsDetailsModelsState(filteredCoins)
			}
		}.launchIn(viewModelScope)
	}

	private fun filterCoinsDetailsModels(coinDetailsModel: CoinDetailsModel, query: String): Boolean {
		return query.lowercase().split(" ").all { coinDetailsModel.name.lowercase().contains(it) }
	}

	private fun startFetchingCoinsDetails() {
		fetchCoinsDetailsJob.cancel()
		fetchCoinsDetailsJob = fetchPeriodicallyCoinsDetails()
	}

	private fun fetchPeriodicallyCoinsDetails() =
		viewModelScope.launch(CoroutineExceptionHandler { _, throwable -> handleError(throwable) } + mainDispatcher) {
			fetchPeriodicallyCoinsDetailsUseCase.execute(getSymbolPairsList())
		}

	private fun observeCoinsDetails() {
		viewModelScope.launch(mainDispatcher) {
			observeCoinDetailsUseCase.execute().catch { handleError(it) }.collectLatest {
				setCoinsDetailsModels(it.toPresentation())
				hideLoading()
			}
		}
	}

	private fun hideLoading() {
		state = state.copy(isLoading = false)
	}

	private fun setCoinsDetailsModels(coinsDetailsModels: List<CoinDetailsModel>) {
		this.coinsDetailsModels.value = coinsDetailsModels
	}

	private fun setCoinsDetailsModelsState(coinsDetailsModels: List<CoinDetailsModel>) {
		state = state.copy(coinsDetailsModels = coinsDetailsModels)
	}

	private fun handleError(throwable: Throwable) {
		// TODO: Implement
		println(throwable.message)
	}

	private fun getSymbolPairsList(): List<String> {
		val symbolsQuery =
			"?symbols=tBTCUSD,tETHUSD,tCHSB:USD,tLTCUSD,tXRPUSD,tDSHUSD,tRRTUSD,tEOSUSD,tSANUSD,tDATUSD,tSNTUSD,tDOGE:USD,tLUNA:USD,tMATIC:USD,tNEXO:USD, tOCEAN:USD, tBEST:USD, tAAVE:USD, tPLUUSD, tFILUSD"
				.replace("?symbols=", "")
		val split = symbolsQuery.split(",").map { it.substring(1) }

		return split
	}
}