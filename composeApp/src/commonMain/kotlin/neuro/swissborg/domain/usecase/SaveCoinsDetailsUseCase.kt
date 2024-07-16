package neuro.swissborg.domain.usecase

import neuro.swissborg.domain.entity.CoinDetails

interface SaveCoinsDetailsUseCase {
	/**
	 * Save a list of coins details.
	 *
	 * @param coinsDetails list to save.
	 */
	suspend fun execute(coinsDetails: List<CoinDetails>)
}