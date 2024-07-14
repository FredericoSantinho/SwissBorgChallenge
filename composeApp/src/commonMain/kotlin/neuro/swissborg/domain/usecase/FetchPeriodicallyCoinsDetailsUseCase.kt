package neuro.swissborg.domain.usecase

interface FetchPeriodicallyCoinsDetailsUseCase {
	suspend fun execute(symbolPairs: List<String>, periodSeconds: Int = 5)
}