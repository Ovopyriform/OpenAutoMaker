
package org.openautomaker.base.comms.remote;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.Locale;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import celtech.roboxbase.comms.remote.FixedDecimalFloatFormat;

public class FixedDecimalFloatFormatLocaleFromStringTest {

	private final double epsilon = 1e-10;
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
	public void testParse(String inputString, double expectedResult, Locale localeToUse) {
		double result = 0;

		System.out.println("Parsing " + inputString + " with Locale " + localeToUse.toString());
		try {
			result = formatter.parse(inputString).doubleValue();
		}
		catch (ParseException ex) {
			System.err.println("Error parsing input:" + inputString);
		}
		assertEquals(expectedResult, result, epsilon);
	}

	private static Stream<Arguments> provideValuesForTestParse() {
		return Stream.of(
				Arguments.of("-132.3", -132.3, Locale.UK),
				Arguments.of("132.3", 132.3, Locale.UK),
				Arguments.of("  131.3", 131.3, Locale.UK),
				Arguments.of("123.1456", 123.1456, Locale.UK),
				Arguments.of("132.3", 132.3, Locale.FRANCE),
				Arguments.of("  131.3", 131.3, Locale.FRANCE),
				Arguments.of("123.1456", 123.1456, Locale.FRANCE),
				Arguments.of("132.3", 132.3, Locale.CHINA),
				Arguments.of("  131.3", 131.3, Locale.CHINA),
				Arguments.of("123.1456", 123.1456, Locale.CHINA),
				Arguments.of("132.3", 132.3, Locale.KOREA),
				Arguments.of("  131.3", 131.3, Locale.KOREA),
				Arguments.of("123.1456", 123.1456, Locale.KOREA));
	}
}
