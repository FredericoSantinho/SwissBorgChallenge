package neuro.swissborg.presentation.mapper

import androidx.compose.ui.graphics.Color
import neuro.swissborg.domain.entity.CoinDetails
import neuro.swissborg.presentation.model.CoinDetailsModel
import neuro.swissborg.presentation.util.format
import neuro.swissborg.presentation.util.formatCurrency

fun CoinDetails.toPresentation() = CoinDetailsModel(
	name,
	symbol,
	iconUrl,
	price.formatCurrency(3),
	formatPriceChange(priceChangePercent),
	Color(priceChangeColor),
	allowsFunding
)

fun formatPriceChange(priceChangePercent: Double): String {
	return if (priceChangePercent > 0) {
		"+${priceChangePercent.format()}%"
	} else {
		"${priceChangePercent.format()}%"
	}
}

fun List<CoinDetails>.toPresentation() = map { it.toPresentation() }