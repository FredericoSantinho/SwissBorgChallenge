package neuro.swissborg.presentation.util

import kotlin.test.Test
import kotlin.test.assertEquals

class FormatterExtTest {
	@Test
	fun testFormatTwoDecimals() {
		assertEquals("100.00", 100.0.format(2))
		assertEquals("100.00", 100.00.format(2))
		assertEquals("100.01", 100.01.format(2))
		assertEquals("100.02", 100.015.format(2))
		assertEquals("0.10", 0.1.format(2))
		assertEquals("0.01", 0.01.format(2))
		assertEquals("0.02", 0.015.format(2))
		assertEquals("1.00", 0.999.format(2))
		assertEquals("-0.10", (-0.1).format(2))
		assertEquals("-0.01", (-0.01).format(2))
		assertEquals("-0.02", (-0.015).format(2))
		assertEquals("-1.10", (-1.1).format(2))
		assertEquals("-1.01", (-1.01).format(2))
		assertEquals("-1.02", (-1.015).format(2))
	}

	@Test
	fun testFormatThreeDecimals() {
		assertEquals("100.000", 100.0.format(3))
		assertEquals("100.000", 100.00.format(3))
		assertEquals("100.001", 100.001.format(3))
		assertEquals("100.002", 100.0015.format(3))
		assertEquals("0.010", 0.01.format(3))
		assertEquals("0.001", 0.001.format(3))
		assertEquals("0.002", 0.0015.format(3))
		assertEquals("1.000", 0.9999.format(3))
		assertEquals("-0.100", (-0.1).format(3))
		assertEquals("-0.001", (-0.001).format(3))
		assertEquals("-0.002", (-0.0015).format(3))
		assertEquals("-1.100", (-1.1).format(3))
		assertEquals("-1.001", (-1.001).format(3))
		assertEquals("-1.002", (-1.0015).format(3))
	}

	@Test
	fun testFormatCurrency() {
		assertEquals("$ 100.00", 100.0.formatCurrency(2))
		assertEquals("$ 100.00", 100.00.formatCurrency(2))
		assertEquals("$ 100.01", 100.01.formatCurrency(2))
		assertEquals("$ 100.02", 100.015.formatCurrency(2))
		assertEquals("$ 0.10", 0.1.formatCurrency(2))
		assertEquals("$ 0.01", 0.01.formatCurrency(2))
		assertEquals("$ 0.02", 0.015.formatCurrency(2))
		assertEquals("$ 1.00", 0.999.formatCurrency(2))
		assertEquals("$ -0.10", (-0.1).formatCurrency(2))
		assertEquals("$ -0.01", (-0.01).formatCurrency(2))
		assertEquals("$ -0.02", (-0.015).formatCurrency(2))
		assertEquals("$ -1.10", (-1.1).formatCurrency(2))
		assertEquals("$ -1.01", (-1.01).formatCurrency(2))
		assertEquals("$ -1.02", (-1.015).formatCurrency(2))
	}

	@Test
	fun testFormatCurrencyThreeDecimals() {
		assertEquals("$ 100.000", 100.0.formatCurrency(3))
		assertEquals("$ 100.000", 100.00.formatCurrency(3))
		assertEquals("$ 100.001", 100.001.formatCurrency(3))
		assertEquals("$ 100.002", 100.0015.formatCurrency(3))
		assertEquals("$ 0.010", 0.01.formatCurrency(3))
		assertEquals("$ 0.001", 0.001.formatCurrency(3))
		assertEquals("$ 0.002", 0.0015.formatCurrency(3))
		assertEquals("$ 1.000", 0.9999.formatCurrency(3))
		assertEquals("$ -0.100", (-0.1).formatCurrency(3))
		assertEquals("$ -0.001", (-0.001).formatCurrency(3))
		assertEquals("$ -0.002", (-0.0015).formatCurrency(3))
		assertEquals("$ -1.100", (-1.1).formatCurrency(3))
		assertEquals("$ -1.001", (-1.001).formatCurrency(3))
		assertEquals("$ -1.002", (-1.0015).formatCurrency(3))
	}
}