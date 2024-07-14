package neuro.swissborg.domain.usecase

import neuro.swissborg.domain.entity.CoinDetails
import neuro.swissborg.domain.repository.CoinDetailsRepository

class SaveCoinsDetailsUseCaseImpl(private val coinDetailsRepository: CoinDetailsRepository) :
	SaveCoinsDetailsUseCase {
	override suspend fun execute(coinsDetails: List<CoinDetails>) {
		coinDetailsRepository.saveAll(coinsDetails)
	}
}