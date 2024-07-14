package neuro.swissborg.presentation.mapper

import androidx.compose.runtime.Composable
import neuro.swissborg.presentation.viewmodel.Message
import org.jetbrains.compose.resources.stringResource
import swissborgmobiletechchallenge.composeapp.generated.resources.Res
import swissborgmobiletechchallenge.composeapp.generated.resources.no_connectivity

@Composable
fun Message.toPresentation(): String = when (this) {
	is Message.Literal -> message
	Message.NoConnectivity -> stringResource(Res.string.no_connectivity)
}