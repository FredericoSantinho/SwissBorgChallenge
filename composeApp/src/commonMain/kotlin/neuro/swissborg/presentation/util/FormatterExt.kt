package neuro.swissborg.presentation.util

import kotlin.math.min

fun Double.format(maxDecimals: Int = 2): String {
	val result = this.toString()
	val lastIndex = result.length - 1
	var pos = lastIndex
	while (pos >= 0 && result[pos] != '.') {
		pos--
	}
	return if (maxDecimals < 1 && pos >= 0) {
		result.substring(0, min(pos, result.length))
	} else if (pos >= 0) {
		val substring = result.substring(0, min(pos + 1 + maxDecimals + 1, result.length))
		val substringLastDigit = substring[substring.length - 1].digitToInt()
		if (substringLastDigit in 5..8) {
			val notRoundedResult = result.substring(0, min(pos + 1 + maxDecimals, result.length))
			notRoundedResult.substring(
				0,
				notRoundedResult.length - 1
			) + (notRoundedResult[notRoundedResult.length - 1].digitToInt() + 1)
		} else {
			result.substring(0, min(pos + 1 + maxDecimals, result.length))
		}
	} else {
		return result
	}
}

fun Double.formatCurrency(
	maxDecimals: Int = 2, currency: String = "$",
): String {
	return "$currency ${format(maxDecimals)}"
}