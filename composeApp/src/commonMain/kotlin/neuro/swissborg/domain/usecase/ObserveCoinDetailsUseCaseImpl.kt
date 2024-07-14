package neuro.swissborg.domain.usecase

import kotlinx.coroutines.flow.Flow
import neuro.swissborg.domain.entity.CoinDetails
import neuro.swissborg.domain.repository.CoinDetailsRepository

class ObserveCoinDetailsUseCaseImpl(private val coinDetailsRepository: CoinDetailsRepository) :
	ObserveCoinDetailsUseCase {
	override fun execute(): Flow<List<CoinDetails>> {
		return coinDetailsRepository.observeAll()
	}
}