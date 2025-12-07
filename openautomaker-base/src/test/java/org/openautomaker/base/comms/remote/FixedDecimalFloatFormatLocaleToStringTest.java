
package org.openautomaker.base.comms.remote;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import celtech.roboxbase.comms.remote.FixedDecimalFloatFormat;

public class FixedDecimalFloatFormatLocaleToStringTest {

	private FixedDecimalFloatFormat formatter = new FixedDecimalFloatFormat();
	private static Locale originalLocale = Locale.getDefault();

	@AfterAll
	public static void tearDownClass() {
		Locale.setDefault(originalLocale);
	}

	/**
	 * Test of parsing, of class FixedDecimalFloatFormat.
	 */
	@ParameterizedTest
	@MethodSource("provideValuesForTestParse")
	public void testParse(double inputValue, String expectedResult, Locale localeToUse) {
		String result = null;

		System.out.println("Formatting " + inputValue + " with Locale " + localeToUse.toString());
		result = formatter.format(inputValue);
		assertEquals(expectedResult, result);
	}

	private static Stream<Arguments> provideValuesForTestParse() {
		return Stream.of(Arguments.of(132.34, "  132.34", Locale.UK),
				Arguments.of(132.3, "   132.3", Locale.UK),
				Arguments.of(123.1456, "123.1456", Locale.UK),
				Arguments.of(-132.3, "  -132.3", Locale.UK),
				Arguments.of(132.3, "   132.3", Locale.FRANCE),
				Arguments.of(123.1456, "123.1456", Locale.FRANCE),
				Arguments.of(-132.3, "  -132.3", Locale.FRANCE),
				Arguments.of(132.3, "   132.3", Locale.CHINA),
				Arguments.of(123.1456, "123.1456", Locale.CHINA),
				Arguments.of(-132.3, "  -132.3", Locale.CHINA),
				Arguments.of(132.3, "   132.3", Locale.KOREA),
				Arguments.of(123.1456, "123.1456", Locale.KOREA),
				Arguments.of(-132.3, "  -132.3", Locale.KOREA));
	}
}
