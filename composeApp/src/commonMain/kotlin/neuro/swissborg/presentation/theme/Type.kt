package neuro.swissborg.presentation.theme


import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val swissBorgChallengeTypography = Typography(
	body1 = TextStyle(
		fontFamily = FontFamily.Default,
		fontSize = 18.sp,
		fontWeight = FontWeight.Normal,
	),
	body2 = TextStyle(
		fontFamily = FontFamily.Default,
		fontSize = 16.sp,
		fontWeight = FontWeight.Normal,
	),
	h1 = TextStyle(
		fontFamily = FontFamily.Default,
		fontSize = 96.sp,
		fontWeight = FontWeight.Normal,
	),
	h6 = TextStyle(
		fontFamily = FontFamily.Default,
		fontSize = 20.sp,
		fontWeight = FontWeight.Normal,
	)
)
