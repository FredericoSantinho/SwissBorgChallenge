package neuro.swissborg.presentation.mapper

import androidx.compose.runtime.Composable
import neuro.swissborg.presentation.screens.marketplace.viewmodel.Message
import org.jetbrains.compose.resources.stringResource
import swissborgmobiletechchallenge.composeapp.generated.resources.Res
import swissborgmobiletechchallenge.composeapp.generated.resources.database_error
import swissborgmobiletechchallenge.composeapp.generated.resources.fetch_error
import swissborgmobiletechchallenge.composeapp.generated.resources.no_connectivity

@Composable
fun Message.toPresentation(): String = when (this) {
	Message.NoConnectivity -> stringResource(Res.string.no_connectivity)
	Message.DatabaseError -> stringResource(Res.string.database_error)
	Message.FetchError -> stringResource(Res.string.fetch_error)
}