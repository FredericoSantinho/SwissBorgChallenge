package neuro.swissborg.domain.usecase

import neuro.swissborg.domain.entity.CoinDetails

interface SaveCoinsDetailsUseCase {
	suspend fun execute(coinsDetails: List<CoinDetails>)
}