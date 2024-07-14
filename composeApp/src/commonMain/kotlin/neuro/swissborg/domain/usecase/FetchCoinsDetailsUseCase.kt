package neuro.swissborg.domain.usecase

interface FetchCoinsDetailsUseCase {
	suspend fun execute(symbolPairs: List<String>)
}