package neuro.swissborg.domain.usecase

import kotlin.test.Test
import kotlin.test.assertEquals

class GetSymbolPairsUseCaseImplTest {
	@Test
	fun test() {
		val getSymbolPairsUseCase = GetSymbolPairsUseCaseImpl()

		val symbolPairs = getSymbolPairsUseCase.execute()

		assertEquals(expectedSymbolPairs(), symbolPairs)
	}

	private fun expectedSymbolPairs(): List<String> {
		return listOf(
			"BTCUSD",
			"ETHUSD",
			"CHSB:USD",
			"LTCUSD",
			"XRPUSD",
			"DSHUSD",
			"RRTUSD",
			"EOSUSD",
			"SANUSD",
			"DATUSD",
			"SNTUSD",
			"DOGE:USD",
			"LUNA:USD",
			"MATIC:USD",
			"NEXO:USD",
			"OCEAN:USD",
			"BEST:USD",
			"AAVE:USD",
			"PLUUSD",
			"FILUSD"
		)
	}
}