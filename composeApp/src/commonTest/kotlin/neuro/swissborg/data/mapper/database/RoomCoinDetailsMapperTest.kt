package neuro.swissborg.data.mapper.database

import neuro.swissborg.data.model.database.RoomCoinDetails
import neuro.swissborg.domain.entity.CoinDetails
import kotlin.test.Test
import kotlin.test.assertEquals

class RoomCoinDetailsMapperTest {
	@Test
	fun test() {
		assertEquals(buildCoinDetails(), buildRoomCoinDetails().toDomain())
		assertEquals(buildCoinDetailsList(), buildRoomCoinDetailsList().toDomain())

		assertEquals(buildRoomCoinDetails(), buildCoinDetails().toDatabase())
		assertEquals(buildRoomCoinDetailsList(), buildCoinDetailsList().toDatabase())
	}

	private fun buildCoinDetailsList(): List<CoinDetails> {
		return listOf(
			CoinDetails(
				"Bitcoin",
				"BTC",
				"https://static.bitfinex.com/images/icons/BTC.svg",
				100.0,
				10.0,
				18374966855136706560UL,
				true
			),
			CoinDetails(
				"Ethereum",
				"ETH",
				"https://static.bitfinex.com/images/icons/ETH.svg",
				200.0,
				-20.0,
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
				100.0,
				10.0,
				(18374966855136706560UL).toLong(),
				true
			),
			RoomCoinDetails(
				"Ethereum",
				"ETH",
				"https://static.bitfinex.com/images/icons/ETH.svg",
				200.0,
				-20.0,
				(18446462598732840960UL).toLong(),
				true
			)
		)
	}

	private fun buildRoomCoinDetails(): RoomCoinDetails {
		return RoomCoinDetails(
			"Bitcoin",
			"BTC",
			"https://static.bitfinex.com/images/icons/BTC.svg",
			100.0,
			10.0,
			(18374966855136706560UL).toLong(),
			true
		)
	}

	private fun buildCoinDetails(): CoinDetails {
		return CoinDetails(
			"Bitcoin",
			"BTC",
			"https://static.bitfinex.com/images/icons/BTC.svg",
			100.0,
			10.0,
			18374966855136706560UL,
			true
		)
	}
}