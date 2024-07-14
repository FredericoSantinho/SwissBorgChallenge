package neuro.swissborg.domain.usecase

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode.Companion.atMost
import dev.mokkery.verify.VerifyMode.Companion.exactly
import dev.mokkery.verifySuspend
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class FetchPeriodicallyCoinsDetailsUseCaseImplTest {
	@Test
	fun test() = runTest {
		val symbolPairs = buildSymbolPairsList()

		val fetchCoinsDetailsUseCase = mock<FetchCoinsDetailsUseCase> {
			everySuspend { execute(symbolPairs) } returns Unit
		}

		val fetchPeriodicallyCoinsDetailsUseCase =
			FetchPeriodicallyCoinsDetailsUseCaseImpl(fetchCoinsDetailsUseCase)

		val job = launch {
			fetchPeriodicallyCoinsDetailsUseCase.execute(symbolPairs)
		}

		delay(5_000)

		verifySuspend(exactly(1)) {
			fetchCoinsDetailsUseCase.execute(symbolPairs)
		}

		delay(50_000)

		verifySuspend(atMost(10)) {
			fetchCoinsDetailsUseCase.execute(symbolPairs)
		}

		job.cancel()
	}

	private fun buildSymbolPairsList(): List<String> {
		return listOf("BTCUSD", "ETHUSD")
	}
}