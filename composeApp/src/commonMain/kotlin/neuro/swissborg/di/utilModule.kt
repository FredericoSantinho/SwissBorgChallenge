package neuro.swissborg.di

import neuro.swissborg.presentation.util.connection.ConnectionObserver
import neuro.swissborg.presentation.util.connection.ConnectionObserverImpl
import org.koin.dsl.module

val utilModule = module {
	factory<ConnectionObserver> { ConnectionObserverImpl() }
}