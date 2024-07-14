package neuro.swissborg.presentation.model

import androidx.compose.ui.graphics.Color

data class CoinDetailsModel(
	val name: String,
	val symbol: String,
	val iconUrl: String,
	val price: String,
	val priceChangePercent: String,
	val priceChangeColor: Color,
	val allowsFunding: Boolean,
)
