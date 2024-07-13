package neuro.swissborg.domain.usecase

import neuro.swissborg.domain.entity.CoinDetails

interface GetCoinsDetailsUseCase {
	suspend fun execute(symbolPairs: List<String>): List<CoinDetails>
}