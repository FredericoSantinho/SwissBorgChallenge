package neuro.swissborg.domain.usecase

interface FetchCoinsDetailsUseCase {
	/**
	 * Fetches coins details given a list of symbol pairs.
	 *
	 * @param symbolPairs in the format "BTCUSD" or "CHSB:USD" depending on the API definition.
	 */
	suspend fun execute(symbolPairs: List<String>)
}