package neuro.swissborg.data.repository

import app.cash.turbine.test
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode.Companion.exactly
import dev.mokkery.verifyNoMoreCalls
import dev.mokkery.verifySuspend
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import neuro.swissborg.data.dao.CoinDetailsDao
import neuro.swissborg.data.model.database.RoomCoinDetails
import neuro.swissborg.domain.entity.CoinDetails
import kotlin.test.Test
import kotlin.test.assertEquals

class CoinDetailsRepositoryImplTest {
	@Test
	fun testSaveAll() = runTest {
		val roomCoinsTickers = buildRoomCoinDetailsList()

		val coinDetailsDao = mock<CoinDetailsDao> {
			everySuspend { upsertCoinsDetails(roomCoinsTickers) } returns Unit
		}

		val coinDetailsRepository = CoinDetailsRepositoryImpl(coinDetailsDao)

		coinDetailsRepository.saveAll(buildCoinDetailsList())

		verifySuspend(exactly(1)) { coinDetailsDao.upsertCoinsDetails(roomCoinsTickers) }
		verifyNoMoreCalls()
	}

	@Test
	fun testObserveAll() = runTest {
		val roomCoinsTickers = buildRoomCoinDetailsList()

		val coinDetailsDao = mock<CoinDetailsDao> {
			everySuspend { observeCoinsDetails() } returns flowOf(buildRoomCoinDetailsList())
		}

		val coinDetailsRepository = CoinDetailsRepositoryImpl(coinDetailsDao)

		coinDetailsRepository.observeAll().test {
			assertEquals(buildCoinDetailsList(), awaitItem())
			awaitComplete()
		}

		verify(exactly(1)) { coinDetailsDao.observeCoinsDetails() }
		verifyNoMoreCalls()
	}

	private fun buildCoinDetailsList(): List<CoinDetails> {
		return listOf(
			CoinDetails(
				"Bitcoin",
				"BTC",
				"https://static.bitfinex.com/images/icons/BTC.svg",
				59469.0,
				0.0235,
				18378557361896816640UL,
				true
			),
			CoinDetails(
				"Ethereum",
				"ETH",
				"https://static.bitfinex.com/images/icons/ETH.svg",
				3283.5,
				-0.02621617,
				18446462598732840960UL,
				true
			)
		)
	}

	private fun buildRoomCoinDetailsList(): List<RoomCoinDetails> {
		return listOf(
			RoomCoinDetails(
				"Bitcoin",
				"BTC",
				"https://static.bitfinex.com/images/icons/BTC.svg",
				59469.0,
				0.0235,
				(18378557361896816640UL).toLong(),
				true
			),
			RoomCoinDetails(
				"Ethereum",
				"ETH",
				"https://static.bitfinex.com/images/icons/ETH.svg",
				3283.5,
				-0.02621617,
				(18446462598732840960UL).toLong(),
				true
			)
		)
	}
}