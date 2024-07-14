package neuro.swissborg.presentation.screens.marketplace

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import neuro.swissborg.presentation.screens.common.TextFieldWithoutPadding
import neuro.swissborg.presentation.theme.SwissBorgChallengeTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import swissborgmobiletechchallenge.composeapp.generated.resources.Res
import swissborgmobiletechchallenge.composeapp.generated.resources.crypto_marketplace
import swissborgmobiletechchallenge.composeapp.generated.resources.search_for_a_coin

@Composable
fun SearchAppBar(onSearchTerm: (String) -> Unit = {}) {
	var searching by rememberSaveable {
		mutableStateOf(false)
	}
	var searchTerm by rememberSaveable {
		mutableStateOf("")
	}
	val focusRequester = remember { FocusRequester() }

	TopAppBar(
		elevation = 4.dp,
		title = {
			if (!searching) Text(
				text = stringResource(Res.string.crypto_marketplace),
				color = MaterialTheme.colors.onPrimary
			) else {
				TextFieldWithoutPadding(
					searchTerm,
					{
						searchTerm = it
						onSearchTerm(it)
					},
					colors = TextFieldDefaults.textFieldColors(
						cursorColor = MaterialTheme.colors.onPrimary,
						backgroundColor = MaterialTheme.colors.primary
					),
					textStyle = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.onPrimary),
					label = {
						Text(
							text = stringResource(Res.string.search_for_a_coin),
							color = Color.DarkGray
						)
					},
					maxLines = 1,
					modifier = Modifier.focusRequester(focusRequester)
				)

				LaunchedEffect(key1 = Unit) {
					focusRequester.requestFocus()
				}
			}
		},
		backgroundColor = MaterialTheme.colors.primary,
		actions = {
			IconButton(onClick = {
				if (searching) {
					searchTerm = ""
					onSearchTerm(searchTerm)
				}
				searching = !searching
			}) {
				Icon(
					if (searching)
						Icons.Filled.Close
					else Icons.Filled.Search, null, tint = MaterialTheme.colors.onPrimary
				)
			}
		})
}

@Preview
@Composable
fun PreviewSearchAppBar() {
	SwissBorgChallengeTheme {
		SearchAppBar {}
	}
}