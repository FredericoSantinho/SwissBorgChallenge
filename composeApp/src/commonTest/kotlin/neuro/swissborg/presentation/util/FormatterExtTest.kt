package neuro.swissborg.presentation.util

import kotlin.test.Test
import kotlin.test.assertEquals

class FormatterExtTest {
	@Test
	fun testFormat() {
		assertEquals("100.0", 100.0.format(2))
		assertEquals("100.0", 100.00.format(2))
		assertEquals("100.01", 100.01.format(2))
		assertEquals("100.02", 100.015.format(2))
		assertEquals("0.1", 0.1.format(2))
		assertEquals("0.01", 0.01.format(2))
		assertEquals("0.02", 0.015.format(2))
		// Rounding this type of input represents an effort that is not worth it.
		assertEquals("0.99", 0.999.format(2))
		assertEquals("-0.1", (-0.1).format(2))
		assertEquals("-0.01", (-0.01).format(2))
		assertEquals("-0.02", (-0.015).format(2))
		assertEquals("-1.1", (-1.1).format(2))
		assertEquals("-1.01", (-1.01).format(2))
		assertEquals("-1.02", (-1.015).format(2))
	}

	@Test
	fun testFormatCurrency() {
		assertEquals("$ 100.0", 100.0.formatCurrency(2))
		assertEquals("$ 100.0", 100.00.formatCurrency(2))
		assertEquals("$ 100.01", 100.01.formatCurrency(2))
		assertEquals("$ 100.02", 100.015.formatCurrency(2))
		assertEquals("$ 0.1", 0.1.formatCurrency(2))
		assertEquals("$ 0.01", 0.01.formatCurrency(2))
		assertEquals("$ 0.02", 0.015.formatCurrency(2))
		// Rounding this type of input represents an effort that is not worth it.
		assertEquals("$ 0.99", 0.999.formatCurrency(2))
		assertEquals("$ -0.1", (-0.1).formatCurrency(2))
		assertEquals("$ -0.01", (-0.01).formatCurrency(2))
		assertEquals("$ -0.02", (-0.015).formatCurrency(2))
		assertEquals("$ -1.1", (-1.1).formatCurrency(2))
		assertEquals("$ -1.01", (-1.01).formatCurrency(2))
		assertEquals("$ -1.02", (-1.015).formatCurrency(2))
	}
}