package neuro.swissborg.data.mapper

import neuro.swissborg.data.model.database.RoomCoinDetails
import neuro.swissborg.domain.entity.CoinDetails

fun CoinDetails.toDatabase() =
	RoomCoinDetails(name, symbol, iconUrl, price, priceChangePercent, priceChangeColor.toLong())

fun List<CoinDetails>.toDatabase() = map { it.toDatabase() }

fun RoomCoinDetails.toDomain() =
	CoinDetails(name, symbol, iconUrl, price, priceChangePercent, priceChangeColor.toULong())

fun List<RoomCoinDetails>.toDomain() = map { it.toDomain() }