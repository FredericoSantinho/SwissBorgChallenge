package neuro.swissborg.presentation.screens.marketplace

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import neuro.swissborg.presentation.model.CoinDetailsModel
import neuro.swissborg.presentation.util.Spacer16
import neuro.swissborg.presentation.util.Spacer4

@Composable
fun CoinDetailsComposable(coinDetailsModel: CoinDetailsModel, modifier: Modifier = Modifier) {
	Surface(elevation = 8.dp, shape = MaterialTheme.shapes.large) {
		Row(modifier = modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
			val painter = rememberImagePainter(coinDetailsModel.iconUrl)
			Image(
				painter = painter,
				contentDescription = null,
				modifier = Modifier
					.size(64.dp)
			)
			Spacer16()
			Column {
				Text(
					coinDetailsModel.name,
					style = MaterialTheme.typography.body1,
					fontWeight = FontWeight.Bold
				)
				Spacer4()
				Text(coinDetailsModel.symbol, style = MaterialTheme.typography.body2)
			}
			Spacer(modifier = Modifier.weight(1.0f))
			Column(horizontalAlignment = Alignment.End) {
				Text(coinDetailsModel.price, fontWeight = FontWeight.Bold)
				Spacer4()
				Text(coinDetailsModel.priceChangePercent, color = coinDetailsModel.priceChangeColor)
			}
		}
	}
}
