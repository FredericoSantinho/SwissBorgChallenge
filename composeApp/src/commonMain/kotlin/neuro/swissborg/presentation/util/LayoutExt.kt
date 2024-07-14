package neuro.swissborg.presentation.util

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.Spacer4() = Spacer(Modifier.height(4.dp))
@Composable
fun ColumnScope.Spacer8() = Spacer(Modifier.height(8.dp))
@Composable
fun ColumnScope.Spacer16() = Spacer(Modifier.height(16.dp))

@Composable
fun RowScope.Spacer4() = Spacer(Modifier.width(4.dp))
@Composable
fun RowScope.Spacer8() = Spacer(Modifier.width(8.dp))
@Composable
fun RowScope.Spacer16() = Spacer(Modifier.width(16.dp))