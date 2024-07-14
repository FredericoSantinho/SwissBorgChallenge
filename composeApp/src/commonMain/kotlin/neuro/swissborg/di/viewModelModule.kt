package neuro.swissborg.di

import neuro.swissborg.presentation.screens.marketplace.viewmodel.MarketplaceViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
	viewModelOf(::MarketplaceViewModel)
}