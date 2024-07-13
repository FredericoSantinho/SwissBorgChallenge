package neuro.swissborg.di

import neuro.swissborg.viewmodel.AppViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
	viewModelOf(::AppViewModel)
}