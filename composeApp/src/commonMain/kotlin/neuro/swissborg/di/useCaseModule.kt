package neuro.swissborg.di

import neuro.swissborg.domain.usecase.FetchCoinsDetailsUseCase
import neuro.swissborg.domain.usecase.FetchCoinsDetailsUseCaseImpl
import neuro.swissborg.domain.usecase.GetCoinsDetailsUseCase
import neuro.swissborg.domain.usecase.GetCoinsDetailsUseCaseImpl
import neuro.swissborg.domain.usecase.ObserveCoinDetailsUseCase
import neuro.swissborg.domain.usecase.ObserveCoinDetailsUseCaseImpl
import neuro.swissborg.domain.usecase.SaveCoinsDetailsUseCase
import neuro.swissborg.domain.usecase.SaveCoinsDetailsUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
	factory<GetCoinsDetailsUseCase> { GetCoinsDetailsUseCaseImpl(get(), get()) }
	factory<SaveCoinsDetailsUseCase> { SaveCoinsDetailsUseCaseImpl(get()) }
	factory<FetchCoinsDetailsUseCase> { FetchCoinsDetailsUseCaseImpl(get(), get()) }
	factory<ObserveCoinDetailsUseCase> { ObserveCoinDetailsUseCaseImpl(get()) }
}