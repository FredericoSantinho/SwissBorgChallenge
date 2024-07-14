package neuro.swissborg.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
	primary = Color.White,
	primaryVariant = Color.White,
	secondary = Color.Red,
	surface = Color.White,
	onSurface = Color.Black,
	onPrimary = Color.Black,
	background = Color.White,
	onBackground = Color.Black


	/* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun SwissBorgChallengeTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	content: @Composable () -> Unit,
) {
	val colors = LightColorPalette

	CompositionLocalProvider {
		MaterialTheme(
			colors = colors,
			content = content,
			typography = swissBorgChallengeTypography,
			shapes = swissBorgChallengeShapes
		)
	}
}
