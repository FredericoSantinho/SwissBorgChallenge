package neuro.swissborg.di

import neuro.swissborg.domain.usecase.FetchPeriodicallyCoinsDetailsUseCase
import neuro.swissborg.domain.usecase.GetSymbolPairsUseCase
import neuro.swissborg.domain.usecase.ObserveCoinDetailsUseCase
import neuro.swissborg.presentation.screens.marketplace.viewmodel.MarketplaceViewModel
import neuro.swissborg.presentation.util.connection.ConnectionObserver
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
	viewModelOf<MarketplaceViewModel, GetSymbolPairsUseCase, ObserveCoinDetailsUseCase, FetchPeriodicallyCoinsDetailsUseCase, ConnectionObserver>(
		{ getSymbolPairsUseCase, observeCoinDetailsUseCase, fetchPeriodicallyCoinsDetailsUseCase, connectionObserver ->
			MarketplaceViewModel(
				getSymbolPairsUseCase,
				observeCoinDetailsUseCase,
				fetchPeriodicallyCoinsDetailsUseCase,
				connectionObserver
			)
		})
}