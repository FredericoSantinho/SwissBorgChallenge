package neuro.swissborg.di

import neuro.swissborg.data.repository.TickersRepositoryImpl
import neuro.swissborg.domain.repository.TickersRepository
import org.koin.dsl.module

val repositoryModule = module {
	factory<TickersRepository> { TickersRepositoryImpl(get()) }
}