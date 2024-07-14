package neuro.swissborg.domain.repository

import kotlinx.coroutines.flow.Flow
import neuro.swissborg.domain.entity.CoinDetails

interface CoinDetailsRepository {
	suspend fun saveAll(coinsDetails: List<CoinDetails>)
	fun observeAll(): Flow<List<CoinDetails>>
}