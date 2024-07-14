package neuro.swissborg.domain.usecase

import kotlinx.coroutines.flow.Flow
import neuro.swissborg.domain.entity.CoinDetails

interface ObserveCoinDetailsUseCase {
	fun execute(): Flow<List<CoinDetails>>
}