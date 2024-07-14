package neuro.swissborg.presentation.screens.marketplace

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import neuro.swissborg.presentation.theme.SwissBorgChallengeTheme
import neuro.swissborg.presentation.viewmodel.MarketplaceViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun MarketplaceScreen(viewModel: MarketplaceViewModel = koinViewModel()) {
	val state = viewModel.state

	SwissBorgChallengeTheme {
		LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
			items(state.coinsDetailsModels) { coinDetailsModel ->
				Column(modifier = Modifier.padding(horizontal = 8.dp)) {
					CoinDetailsComposable(coinDetailsModel)
				}
			}
		}
	}
}