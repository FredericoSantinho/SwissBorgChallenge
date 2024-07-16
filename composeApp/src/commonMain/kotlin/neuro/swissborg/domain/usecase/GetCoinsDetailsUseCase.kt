package neuro.swissborg.domain.usecase

import neuro.swissborg.domain.entity.CoinDetails

interface GetCoinsDetailsUseCase {
	/**
	 * Get a list of coins details.
	 *
	 * @param symbolPairs in the format "BTCUSD" or "CHSB:USD" depending on the API definition.
	 */
	suspend fun execute(symbolPairs: List<String>): List<CoinDetails>
}