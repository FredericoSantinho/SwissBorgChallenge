package neuro.swissborg.presentation.screens.marketplace

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import neuro.swissborg.presentation.screens.common.LoadingComposable
import neuro.swissborg.presentation.theme.SwissBorgChallengeTheme
import neuro.swissborg.presentation.viewmodel.MarketplaceViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun MarketplaceScreen(viewModel: MarketplaceViewModel = koinViewModel()) {
	val state = viewModel.state

	val snackState = remember { SnackbarHostState() }
	val snackScope = rememberCoroutineScope()

	SwissBorgChallengeTheme {
		Scaffold(
			topBar = { SearchAppBar { viewModel.onSearchTerm(it) } },
			snackbarHost = {
				SnackbarHost(hostState = snackState, modifier = Modifier.navigationBarsPadding()) { data ->
					Snackbar(
						backgroundColor = Color.DarkGray,
						snackbarData = data
					)
				}
			},
			modifier = Modifier
				.statusBarsPadding()
		) {
			if (state.isLoading) {
				Column(
					modifier = Modifier.fillMaxSize(),
					horizontalAlignment = Alignment.CenterHorizontally,
					verticalArrangement = Arrangement.Center
				) {
					LoadingComposable()
				}
			} else {
				LazyColumn(
					contentPadding = PaddingValues(horizontal = 8.dp),
					modifier = Modifier.padding(it)
				) {
					items(state.coinsDetailsModels) { coinDetailsModel ->
						Spacer(modifier = Modifier.padding(8.dp))
						Column {
							CoinDetailsComposable(coinDetailsModel)
						}
					}
					item {
						Spacer(modifier = Modifier.navigationBarsPadding())
					}
				}
			}
		}
	}
}