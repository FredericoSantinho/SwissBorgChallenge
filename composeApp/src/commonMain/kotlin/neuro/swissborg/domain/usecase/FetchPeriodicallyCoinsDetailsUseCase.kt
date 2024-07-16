package neuro.swissborg.domain.usecase

interface FetchPeriodicallyCoinsDetailsUseCase {
	/**
	 * Fetches coins details periodically.
	 *
	 * This suspend function doesn't throw any errors.
	 *
	 * @param symbolPairs in the format "BTCUSD" or "CHSB:USD" depending on the API definition.
	 * @param periodSeconds period in seconds.
	 */
	suspend fun execute(symbolPairs: List<String>, periodSeconds: Int = 5)
}