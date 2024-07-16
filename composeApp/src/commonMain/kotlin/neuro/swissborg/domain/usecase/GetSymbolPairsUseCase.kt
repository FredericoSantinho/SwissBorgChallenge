package neuro.swissborg.domain.usecase

interface GetSymbolPairsUseCase {
	/**
	 * Get symbol pairs list in the format "BTCUSD" or "CHSB:USD" as they're defined in the API.
	 */
	fun execute(): List<String>
}