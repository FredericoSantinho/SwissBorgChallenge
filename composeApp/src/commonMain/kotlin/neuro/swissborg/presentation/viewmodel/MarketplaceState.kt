package neuro.swissborg.presentation.viewmodel

import neuro.swissborg.presentation.model.CoinDetailsModel

data class MarketplaceState(
	val coinsDetailsModels: List<CoinDetailsModel> = emptyList(),
	val isLoading: Boolean = false,
)
