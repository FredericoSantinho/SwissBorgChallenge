package neuro.swissborg.domain.usecase

interface FetchPeriodicallyCoinsDetailsUseCase {
	/**
	 * Fetches coins details periodically.
	 * This suspend function doesn't throw errors.
	 */
	suspend fun execute(symbolPairs: List<String>, periodSeconds: Int = 5)
}