
package org.openautomaker.base.comms.remote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

import celtech.roboxbase.comms.remote.FixedDecimalFloatFormat;

public class FixedDecimalFloatFormatTest {

	private final double epsilon = 1e-10;

	/**
	 * Test of instantiation of class FixedDecimalFloatFormat.
	 */
	@Test
	public void testCreateFixedDecimalFloatFormat() {
		System.out.println("create FixedDecimalFloatFormat");
		FixedDecimalFloatFormat result = new FixedDecimalFloatFormat();
		assertNotNull(result);
	}

	/**
	 * Test overflow during format (long)
	 */
	@Test()
	public void testFormatLongOverflow() {
		assertThrows(NumberFormatException.class, () -> {
			System.out.println("FixedDecimalFloatFormat format long overflow");
			FixedDecimalFloatFormat formatter = new FixedDecimalFloatFormat();
			String result = formatter.format(999999999);
		});
	}

	/**
	 * Test overflow during format (double)
	 */
	@Test()
	public void testFormatDoubleOverflow() {
		assertThrows(NumberFormatException.class, () -> {
			System.out.println("FixedDecimalFloatFormat format double overflow");
			FixedDecimalFloatFormat formatter = new FixedDecimalFloatFormat();
			String result = formatter.format(999999999.99);
		});
	}

	/**
	 * Test truncation during format (double)
	 */
	@Test
	public void testFormatDoubleRoundingUp() {
		System.out.println("FixedDecimalFloatFormat format double rounding up");
		FixedDecimalFloatFormat formatter = new FixedDecimalFloatFormat();
		String result = formatter.format(999999.999999999);
		assertEquals(" 1000000", result);
	}

	/**
	 * Test truncation during format (double)
	 */
	@Test
	public void testFormatDoubleRoundingDown() {
		System.out.println("FixedDecimalFloatFormat format double rounding down");
		FixedDecimalFloatFormat formatter = new FixedDecimalFloatFormat();
		String result = formatter.format(999999.00000001);
		assertEquals("  999999", result);
	}

	/**
	 * Test of format handling, of class FixedDecimalFloatFormat.
	 */
	@Test
	public void testFormatDoubleLeadingSpace() {
		System.out.println("FixedDecimalFloatFormat format double");
		FixedDecimalFloatFormat formatter = new FixedDecimalFloatFormat();
		String result = formatter.format(135.3);

		assertEquals("   135.3", result);
	}

	/**
	 * Test of format handling, of class FixedDecimalFloatFormat.
	 */
	@Test
	public void testFormatDoubleNoLeadingSpace() {
		System.out.println("FixedDecimalFloatFormat format double");
		FixedDecimalFloatFormat formatter = new FixedDecimalFloatFormat();
		String result = formatter.format(135442.4);

		assertEquals("135442.4", result);
	}

	/**
	 * Test of decimalFloatFormat handling, of class FixedDecimalFloatFormat.
	 */
	@Test
	public void testFormatNegativeDoubleLeadingSpace() {
		System.out.println("FixedDecimalFloatFormat format negative double");
		FixedDecimalFloatFormat formatter = new FixedDecimalFloatFormat();
		String result = formatter.format(-130.3);

		assertEquals("  -130.3", result);
	}

	/**
	 * Test of decimalFloatFormat handling, of class FixedDecimalFloatFormat.
	 */
	@Test
	public void testFormatNegativeDoubleNoLeadingSpace() {
		System.out.println("FixedDecimalFloatFormat format negative double");
		FixedDecimalFloatFormat formatter = new FixedDecimalFloatFormat();
		String result = formatter.format(-13078.3);

		assertEquals("-13078.3", result);
	}

	/**
	 * Test of parsing, of class FixedDecimalFloatFormat.
	 */
	@Test
	public void testParseDouble() {
		System.out.println("FixedDecimalFloatFormat parse double");
		FixedDecimalFloatFormat formatter = new FixedDecimalFloatFormat();

		try {
			double result = formatter.parse("   127.5").doubleValue();
			assertEquals(127.5, result, epsilon);
		}
		catch (ParseException ex) {
			fail("Parse exception");
		}

	}

	/**
	 * Test of parsing, of class FixedDecimalFloatFormat.
	 */
	@Test
	public void testParseDoubleHighPrecision() {
		System.out.println("FixedDecimalFloatFormat parse double - large number of digits");
		FixedDecimalFloatFormat formatter = new FixedDecimalFloatFormat();

		double testValue = 0.123456789012345;
		String result = formatter.format(testValue);
		assertEquals("0.123457", result);
	}

	/**
	 * Test of parsing, of class FixedDecimalFloatFormat.
	 */
	@Test
	public void testParseInt() {
		System.out.println("FixedDecimalFloatFormat parse int");
		FixedDecimalFloatFormat formatter = new FixedDecimalFloatFormat();

		try {
			int result = formatter.parse("     127").intValue();
			assertEquals(127, result);
		}
		catch (ParseException ex) {
			fail("Unexpected ParseException");
		}
	}

	/**
	 * Test overflow during parse
	 */
	@Test()
	public void testParseOverflow() {
		assertThrows(NumberFormatException.class, () -> {
			System.out.println("FixedDecimalFloatFormat format double");
			FixedDecimalFloatFormat formatter = new FixedDecimalFloatFormat();
			try {
				formatter.parse("999999999");
			}
			catch (ParseException ex) {
				fail("Unexpected ParseException");
			}
		});
	}

}
