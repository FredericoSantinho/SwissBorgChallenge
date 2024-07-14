package neuro.swissborg.domain.usecase

import kotlinx.coroutines.delay

class FetchPeriodicallyCoinsDetailsUseCaseImpl(private val fetchCoinsDetailsUseCase: FetchCoinsDetailsUseCase) :
	FetchPeriodicallyCoinsDetailsUseCase {
	override suspend fun execute(symbolPairs: List<String>, periodSeconds: Int) {
		while (true) {
			fetchCoinsDetailsUseCase.execute(symbolPairs)
			delay(periodSeconds * 1000L)
		}
	}
}