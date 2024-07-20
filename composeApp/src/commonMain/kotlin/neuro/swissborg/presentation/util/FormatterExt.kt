package neuro.swissborg.presentation.util

import kotlin.math.pow

fun Double.format(decimals: Int = 2): String {
	val stringValue = this.toString()
	// We need this 0.0000000000001 bias to overcome double representation limitations
	val initialLongValue = ((10.0.pow(decimals) + 0.0000000000001) * this).toLong()
	var longValue = initialLongValue

	if (stringValue.indexOf('.') + decimals + 1 < stringValue.length
		&& stringValue[stringValue.indexOf('.') + decimals + 1].digitToInt() >= 5
	) {
		if (longValue > 0) {
			longValue++
		} else {
			longValue--
		}
	}

	var lostAZero = false
	if (longValue.toString().length > initialLongValue.toString().length) {
		lostAZero = true
	}

	val leftZeroes = countLeftZeroes(stringValue, decimals)

	return buildString(longValue, decimals, leftZeroes, lostAZero)
}

private fun countLeftZeroes(stringValue: String, decimals: Int): Int {
	var n = 0
	for (i in 0..if (stringValue.startsWith("-")) decimals + 1 else decimals) {
		if (stringValue[i] == '0' || stringValue[i] == '.' || stringValue[i] == '-') {
			if (stringValue[i] == '0') {
				n++
			}
		} else {
			break
		}
	}
	return n
}

private fun buildString(
	longValue: Long,
	decimals: Int,
	leftZeroes: Int,
	lostAZero: Boolean,
): String {
	val tmpString = longValue.toString()
	val newLeftZeroes = if (lostAZero) leftZeroes - 1 else leftZeroes
	val string = addLeftZeroes(tmpString, newLeftZeroes)

	return if (string.startsWith("-")) {
		"-" + string.substring(1, string.length - decimals) +
				"." + string.substring(string.length - decimals, string.length)
	} else {
		string.substring(0, string.length - decimals) +
				"." + string.substring(string.length - decimals, string.length)
	}
}

private fun addLeftZeroes(string: String, leftZeroes: Int): String {
	return if (string.startsWith("-")) "-" + (1..leftZeroes).map { "0" }
		.joinToString(separator = "") { it } + string.substring(1) else (1..leftZeroes).map { "0" }
		.joinToString(separator = "") { it } + string
}

fun Double.formatCurrency(
	maxDecimals: Int = 2, currency: String = "$",
): String {
	return "$currency ${format(maxDecimals)}"
}