package neuro.swissborg.di

import neuro.swissborg.domain.usecase.GetCoinsDetailsUseCase
import neuro.swissborg.domain.usecase.GetCoinsDetailsUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
	factory<GetCoinsDetailsUseCase> { GetCoinsDetailsUseCaseImpl(get(), get()) }
}