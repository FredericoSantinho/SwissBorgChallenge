package neuro.swissborg.presentation.screens.marketplace.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import neuro.swissborg.domain.usecase.FetchPeriodicallyCoinsDetailsUseCase
import neuro.swissborg.domain.usecase.GetSymbolPairsUseCase
import neuro.swissborg.domain.usecase.ObserveCoinDetailsUseCase
import neuro.swissborg.presentation.mapper.toPresentation
import neuro.swissborg.presentation.model.CoinDetailsModel
import neuro.swissborg.presentation.util.connection.ConnectionObserver

class MarketplaceViewModel(
	private val getSymbolPairsUseCase: GetSymbolPairsUseCase,
	private val observeCoinDetailsUseCase: ObserveCoinDetailsUseCase,
	private val fetchPeriodicallyCoinsDetailsUseCase: FetchPeriodicallyCoinsDetailsUseCase,
	private val connectionObserver: ConnectionObserver,
	private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
) : ViewModel() {

	var state by mutableStateOf(MarketplaceState(isLoading = true))
		private set

	private val coinsDetailsModels = MutableStateFlow<List<CoinDetailsModel>>(emptyList())
	private val searchTerm = MutableStateFlow("")

	private var fetchCoinsDetailsJob: Job = Job()

	init {
		observeCoinsDetails()
		setupStateUpdateWithSearchQuery()
		observeConnectivityChanges()
	}

	fun onSearchTerm(searchTerm: String) {
		this.searchTerm.value = searchTerm
	}

	fun consumeMessage() {
		state = state.copy(message = null)
	}

	private fun observeConnectivityChanges() {
		viewModelScope.launch(mainDispatcher) {
			connectionObserver.observeHasConnection()
				.collect { hasConnection ->
					if (hasConnection) {
						startFetchingCoinsDetails()
					} else {
						stopFetchingCoinsDetails()
						showMessage(Message.NoConnectivity)
					}
				}
		}
	}

	@OptIn(FlowPreview::class)
	private fun setupStateUpdateWithSearchQuery() {
		viewModelScope.launch(mainDispatcher) {
			coinsDetailsModels.combine(searchTerm.debounce(150)) { coinModels, searchTerm ->
				coinModels.let {
					val filteredCoins = coinModels.filter { filterCoinsDetailsModels(it, searchTerm) }
					setCoinsDetailsModelsState(filteredCoins)
				}
			}.collectLatest { }
		}
	}

	private fun filterCoinsDetailsModels(coinDetailsModel: CoinDetailsModel, query: String): Boolean {
		return query.lowercase().split(" ").all { coinDetailsModel.name.lowercase().contains(it) }
	}

	private fun startFetchingCoinsDetails() {
		stopFetchingCoinsDetails()
		fetchCoinsDetailsJob = fetchPeriodicallyCoinsDetails()
	}

	private fun stopFetchingCoinsDetails() {
		fetchCoinsDetailsJob.cancel()
	}

	private fun fetchPeriodicallyCoinsDetails() =
		viewModelScope.launch(mainDispatcher) {
			fetchPeriodicallyCoinsDetailsUseCase.execute(getSymbolPairsUseCase.execute())
		}

	private fun observeCoinsDetails() {
		viewModelScope.launch(mainDispatcher) {
			observeCoinDetailsUseCase.execute().catch { handleDatabaseError() }.collectLatest {
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

	private fun handleDatabaseError() {
		hideLoading()
		showMessage(Message.DatabaseError)
	}

	private fun showMessage(message: Message) {
		state = state.copy(message = message)
	}
}