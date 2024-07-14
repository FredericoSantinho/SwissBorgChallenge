package neuro.swissborg.presentation.screens.marketplace.viewmodel

import androidx.compose.ui.graphics.Color
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.every
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import neuro.swissborg.domain.entity.CoinDetails
import neuro.swissborg.domain.usecase.FetchPeriodicallyCoinsDetailsUseCase
import neuro.swissborg.domain.usecase.GetSymbolPairsUseCase
import neuro.swissborg.domain.usecase.ObserveCoinDetailsUseCase
import neuro.swissborg.presentation.model.CoinDetailsModel
import neuro.swissborg.presentation.util.connection.ConnectionObserver
import kotlin.test.Test
import kotlin.test.assertEquals

class MarketplaceViewModelTest {
	@OptIn(ExperimentalCoroutinesApi::class)
	@Test
	fun testInitialLoading() {
		val getSymbolPairsUseCase = mock<GetSymbolPairsUseCase> {
			every { execute() } returns buildSymbolPairsList()
		}
		val observeCoinDetailsUseCase = mock<ObserveCoinDetailsUseCase> {
			every { execute() } returns MutableSharedFlow()
		}
		val fetchPeriodicallyCoinsDetailsUseCase = mock<FetchPeriodicallyCoinsDetailsUseCase> {
			everySuspend { execute(buildSymbolPairsList()) } returns Unit
		}
		val connectionObserver = mock<ConnectionObserver> {
			every { observeHasConnection() } returns flowOf(true)
		}
		val mainDispatcher = UnconfinedTestDispatcher()

		val marketplaceViewModel = MarketplaceViewModel(
			getSymbolPairsUseCase,
			observeCoinDetailsUseCase,
			fetchPeriodicallyCoinsDetailsUseCase,
			connectionObserver,
			mainDispatcher
		)

		verify(VerifyMode.exactly(1)) {
			observeCoinDetailsUseCase.execute()
		}
		verify(VerifyMode.exactly(1)) {
			connectionObserver.observeHasConnection()
		}
		verifySuspend(VerifyMode.exactly(1)) {
			fetchPeriodicallyCoinsDetailsUseCase.execute(buildSymbolPairsList())
		}

		val initialState = marketplaceViewModel.state
		assertEquals(expectedInitialState(), initialState)
	}

	@OptIn(ExperimentalCoroutinesApi::class)
	@Test
	fun testInitialization() {
		val getSymbolPairsUseCase = mock<GetSymbolPairsUseCase> {
			every { execute() } returns buildSymbolPairsList()
		}
		val observeCoinDetailsUseCase = mock<ObserveCoinDetailsUseCase> {
			every { execute() } returns flowOf(buildCoinDetailsList())
		}
		val fetchPeriodicallyCoinsDetailsUseCase = mock<FetchPeriodicallyCoinsDetailsUseCase> {
			everySuspend { execute(buildSymbolPairsList()) } returns Unit
		}
		val connectionObserver = mock<ConnectionObserver> {
			every { observeHasConnection() } returns flowOf(true)
		}
		val mainDispatcher = UnconfinedTestDispatcher()

		val marketplaceViewModel = MarketplaceViewModel(
			getSymbolPairsUseCase,
			observeCoinDetailsUseCase,
			fetchPeriodicallyCoinsDetailsUseCase,
			connectionObserver,
			mainDispatcher
		)

		verify(VerifyMode.exactly(1)) {
			observeCoinDetailsUseCase.execute()
		}
		verify(VerifyMode.exactly(1)) {
			connectionObserver.observeHasConnection()
		}
		verifySuspend(VerifyMode.exactly(1)) {
			fetchPeriodicallyCoinsDetailsUseCase.execute(buildSymbolPairsList())
		}

		val state = marketplaceViewModel.state
		assertEquals(expectedState(), state)
	}

	@OptIn(ExperimentalCoroutinesApi::class)
	@Test
	fun testSearchTerm() {
		val getSymbolPairsUseCase = mock<GetSymbolPairsUseCase> {
			every { execute() } returns buildSymbolPairsList()
		}
		val observeCoinDetailsUseCase = mock<ObserveCoinDetailsUseCase> {
			every { execute() } returns flowOf(buildCoinDetailsList())
		}
		val fetchPeriodicallyCoinsDetailsUseCase = mock<FetchPeriodicallyCoinsDetailsUseCase> {
			everySuspend { execute(buildSymbolPairsList()) } returns Unit
		}
		val connectionObserver = mock<ConnectionObserver> {
			every { observeHasConnection() } returns flowOf(true)
		}
		val mainDispatcher = UnconfinedTestDispatcher()

		val marketplaceViewModel = MarketplaceViewModel(
			getSymbolPairsUseCase,
			observeCoinDetailsUseCase,
			fetchPeriodicallyCoinsDetailsUseCase,
			connectionObserver,
			mainDispatcher
		)

		assertEquals(expectedState(), marketplaceViewModel.state)

		marketplaceViewModel.onSearchTerm("Eth")

		assertEquals(expectedFilteredState(), marketplaceViewModel.state)
	}

	@OptIn(ExperimentalCoroutinesApi::class)
	@Test
	fun testConnectivity() {
		val getSymbolPairsUseCase = mock<GetSymbolPairsUseCase> {
			every { execute() } returns buildSymbolPairsList()
		}
		val observeCoinDetailsUseCase = mock<ObserveCoinDetailsUseCase> {
			every { execute() } returns flowOf(buildCoinDetailsList())
		}
		val fetchPeriodicallyCoinsDetailsUseCase = mock<FetchPeriodicallyCoinsDetailsUseCase> {
			everySuspend { execute(buildSymbolPairsList()) } returns Unit
		}
		val connectivityFlow = MutableStateFlow(false)
		val connectionObserver = mock<ConnectionObserver> {
			every { observeHasConnection() } returns connectivityFlow
		}
		val mainDispatcher = UnconfinedTestDispatcher()

		val marketplaceViewModel = MarketplaceViewModel(
			getSymbolPairsUseCase,
			observeCoinDetailsUseCase,
			fetchPeriodicallyCoinsDetailsUseCase,
			connectionObserver,
			mainDispatcher
		)

		verify(VerifyMode.exactly(1)) {
			observeCoinDetailsUseCase.execute()
		}
		verify(VerifyMode.exactly(1)) {
			connectionObserver.observeHasConnection()
		}
		verifySuspend(VerifyMode.exactly(0)) {
			fetchPeriodicallyCoinsDetailsUseCase.execute(buildSymbolPairsList())
		}

		val state = marketplaceViewModel.state
		assertEquals(expectedNoConnectivityState(), state)

		connectivityFlow.value = true

		verify(VerifyMode.exactly(0)) {
			observeCoinDetailsUseCase.execute()
		}
		verify(VerifyMode.exactly(0)) {
			connectionObserver.observeHasConnection()
		}
		verifySuspend(VerifyMode.exactly(1)) {
			fetchPeriodicallyCoinsDetailsUseCase.execute(buildSymbolPairsList())
		}
	}

	@OptIn(ExperimentalCoroutinesApi::class)
	@Test
	fun testFetchCoinsDetailsError() {
		val getSymbolPairsUseCase = mock<GetSymbolPairsUseCase> {
			every { execute() } returns buildSymbolPairsList()
		}
		val observeCoinDetailsUseCase = mock<ObserveCoinDetailsUseCase> {
			every { execute() } returns flowOf(buildCoinDetailsList())
		}
		val fetchPeriodicallyCoinsDetailsUseCase = mock<FetchPeriodicallyCoinsDetailsUseCase> {
			everySuspend { execute(buildSymbolPairsList()) } throws Throwable("FetchCoinsDetailsUseCase error")
		}
		val connectionObserver = mock<ConnectionObserver> {
			every { observeHasConnection() } returns flowOf(true)
		}
		val mainDispatcher = UnconfinedTestDispatcher()

		val marketplaceViewModel = MarketplaceViewModel(
			getSymbolPairsUseCase,
			observeCoinDetailsUseCase,
			fetchPeriodicallyCoinsDetailsUseCase,
			connectionObserver,
			mainDispatcher
		)

		verify(VerifyMode.exactly(1)) {
			observeCoinDetailsUseCase.execute()
		}
		verify(VerifyMode.exactly(1)) {
			connectionObserver.observeHasConnection()
		}
		verifySuspend(VerifyMode.exactly(1)) {
			fetchPeriodicallyCoinsDetailsUseCase.execute(buildSymbolPairsList())
		}

		val state = marketplaceViewModel.state
		assertEquals(expectedFetchCoinsDetailsErrorState(), state)
	}

	@OptIn(ExperimentalCoroutinesApi::class)
	@Test
	fun testObserveCoinDetailsError() {
		val getSymbolPairsUseCase = mock<GetSymbolPairsUseCase> {
			every { execute() } returns buildSymbolPairsList()
		}
		val observeCoinDetailsUseCase = mock<ObserveCoinDetailsUseCase> {
			every { execute() } returns flow { throw Exception("ObserveCoinDetailsUseCase error") }
		}
		val fetchPeriodicallyCoinsDetailsUseCase = mock<FetchPeriodicallyCoinsDetailsUseCase> {
			everySuspend { execute(buildSymbolPairsList()) } returns Unit
		}
		val connectionObserver = mock<ConnectionObserver> {
			every { observeHasConnection() } returns flowOf(true)
		}
		val mainDispatcher = UnconfinedTestDispatcher()

		val marketplaceViewModel = MarketplaceViewModel(
			getSymbolPairsUseCase,
			observeCoinDetailsUseCase,
			fetchPeriodicallyCoinsDetailsUseCase,
			connectionObserver,
			mainDispatcher
		)

		verify(VerifyMode.exactly(1)) {
			observeCoinDetailsUseCase.execute()
		}
		verify(VerifyMode.exactly(1)) {
			connectionObserver.observeHasConnection()
		}
		verifySuspend(VerifyMode.exactly(1)) {
			fetchPeriodicallyCoinsDetailsUseCase.execute(buildSymbolPairsList())
		}

		val state = marketplaceViewModel.state
		assertEquals(
			expectedObserveCoinDetailsUseCaseErrorState(),
			state
		)
	}

	private fun expectedFetchCoinsDetailsErrorState(): MarketplaceState {
		return MarketplaceState(
			buildCoinDetailsModelList(),
			Message.Literal("FetchCoinsDetailsUseCase error"),
			false
		)
	}

	private fun expectedObserveCoinDetailsUseCaseErrorState(): MarketplaceState {
		return MarketplaceState(emptyList(), Message.Literal("ObserveCoinDetailsUseCase error"), false)
	}

	private fun expectedNoConnectivityState(): MarketplaceState {
		return MarketplaceState(buildCoinDetailsModelList(), Message.NoConnectivity)
	}

	private fun expectedInitialState(): MarketplaceState {
		return MarketplaceState(emptyList(), null, true)
	}

	private fun expectedState(): MarketplaceState {
		return MarketplaceState(buildCoinDetailsModelList(), null, false)
	}

	private fun expectedFilteredState(): MarketplaceState {
		return MarketplaceState(buildFilteredCoinDetailsModelList(), null, false)
	}

	private fun buildFilteredCoinDetailsModelList(): List<CoinDetailsModel> {
		return listOf(
			CoinDetailsModel(
				"Ethereum",
				"ETH",
				"https://static.bitfinex.com/images/icons/ETH.svg",
				"$ 200.0",
				"-20.0%",
				Color.Red
			)
		)
	}

	private fun buildCoinDetailsModelList(): List<CoinDetailsModel> {
		return listOf(
			CoinDetailsModel(
				"Bitcoin",
				"BTC",
				"https://static.bitfinex.com/images/icons/BTC.svg",
				"$ 100.0",
				"+10.0%",
				Color(18378557361896816640UL)
			),
			CoinDetailsModel(
				"Ethereum",
				"ETH",
				"https://static.bitfinex.com/images/icons/ETH.svg",
				"$ 200.0",
				"-20.0%",
				Color.Red
			)
		)
	}

	private fun buildCoinDetailsList(): List<CoinDetails> {
		return listOf(
			CoinDetails(
				"Bitcoin",
				"BTC",
				"https://static.bitfinex.com/images/icons/BTC.svg",
				100.0,
				10.0,
				18378557361896816640UL
			),
			CoinDetails(
				"Ethereum",
				"ETH",
				"https://static.bitfinex.com/images/icons/ETH.svg",
				200.0,
				-20.0,
				18446462598732840960UL
			)
		)
	}

	private fun buildSymbolPairsList(): List<String> {
		return listOf(
			"BTCUSD",
			"ETHUSD"
		)
	}
}