package neuro.swissborg.di

import neuro.swissborg.presentation.viewmodel.MarketplaceViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
	viewModelOf(::MarketplaceViewModel)
}