package neuro.swissborg.domain.usecase

class FetchCoinsDetailsUseCaseImpl(
	private val getCoinsDetailsUseCase: GetCoinsDetailsUseCase,
	private val saveCoinsDetailsUseCase: SaveCoinsDetailsUseCase,
) : FetchCoinsDetailsUseCase {
	override suspend fun execute(symbolPairs: List<String>) {
		val coinDetailsList = getCoinsDetailsUseCase.execute(symbolPairs)
		saveCoinsDetailsUseCase.execute(coinDetailsList)
	}
}