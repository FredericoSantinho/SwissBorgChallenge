package neuro.swissborg.domain.usecase

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay

class FetchPeriodicallyCoinsDetailsUseCaseImpl(private val fetchCoinsDetailsUseCase: FetchCoinsDetailsUseCase) :
	FetchPeriodicallyCoinsDetailsUseCase {
	override suspend fun execute(symbolPairs: List<String>, periodSeconds: Int) {
		while (true) {
			try {
				fetchCoinsDetailsUseCase.execute(symbolPairs)
			} catch (e: Exception) {
				if (e is CancellationException) {
					throw e
				}
			}
			delay(periodSeconds * 1000L)
		}
	}
}