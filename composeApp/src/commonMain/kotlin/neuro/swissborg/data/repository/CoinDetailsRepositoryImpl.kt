package neuro.swissborg.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import neuro.swissborg.data.dao.CoinDetailsDao
import neuro.swissborg.data.mapper.toDatabase
import neuro.swissborg.data.mapper.toDomain
import neuro.swissborg.domain.entity.CoinDetails
import neuro.swissborg.domain.repository.CoinDetailsRepository

class CoinDetailsRepositoryImpl(private val coinDetailsDao: CoinDetailsDao) :
	CoinDetailsRepository {
	override suspend fun saveAll(coinsDetails: List<CoinDetails>) {
		coinDetailsDao.upsertCoinsDetails(coinsDetails.toDatabase())
	}

	override fun observeAll(): Flow<List<CoinDetails>> {
		return coinDetailsDao.observeCoinsDetails().map { it.toDomain() }
	}
}