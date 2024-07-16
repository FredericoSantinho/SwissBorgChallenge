package neuro.swissborg.domain.usecase

import kotlinx.coroutines.flow.Flow
import neuro.swissborg.domain.entity.CoinDetails

interface ObserveCoinDetailsUseCase {
	/**
	 * Observe coins details from the database.
	 *
	 * @return a flow that will emit a list of coins details everytime there's an update in the database.
	 */
	fun execute(): Flow<List<CoinDetails>>
}