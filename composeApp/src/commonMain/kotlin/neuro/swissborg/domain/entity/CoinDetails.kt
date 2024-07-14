package neuro.swissborg.domain.entity

data class CoinDetails(
	val name: String,
	val symbol: String,
	val iconUrl: String,
	val price: Double,
	val priceChangePercent: Double,
	val priceChangeColor: ULong,
	val allowsFunding: Boolean,
)
