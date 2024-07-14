package neuro.swissborg.di

import neuro.swissborg.data.repository.CoinDetailsRepositoryImpl
import neuro.swissborg.data.repository.FundingRepositoryImpl
import neuro.swissborg.data.repository.SymbolsRepositoryImpl
import neuro.swissborg.data.repository.TickersRepositoryImpl
import neuro.swissborg.domain.repository.CoinDetailsRepository
import neuro.swissborg.domain.repository.FundingRepository
import neuro.swissborg.domain.repository.SymbolsRepository
import neuro.swissborg.domain.repository.TickersRepository
import org.koin.dsl.module

val repositoryModule = module {
	factory<TickersRepository> { TickersRepositoryImpl(get()) }
	factory<SymbolsRepository> { SymbolsRepositoryImpl(get()) }
	factory<CoinDetailsRepository> { CoinDetailsRepositoryImpl(get()) }
	factory<FundingRepository> { FundingRepositoryImpl(get()) }
}