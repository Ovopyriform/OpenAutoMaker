package org.openautomaker.base.postprocessor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GCodeValidatorTest {

	/**
	 * Test of validate method, of class GCodeValidator.
	 */
	@Test
	public void testValidateFailure() {
		System.out.println("validate");
		GCodeValidator instance = new GCodeValidator(this.getClass().getResource("/postprocessor/validatorTest_1.gcode").getFile());
		assertFalse(instance.validate());
	}

	/**
	 * Test of validate method, of class GCodeValidator.
	 */
	@Test
	public void testValidateSuccess() {
		System.out.println("validate");
		GCodeValidator instance = new GCodeValidator(this.getClass().getResource("/postprocessor/validatorTest_2.gcode").getFile());
		assertTrue(instance.validate());
	}

	/**
	 * Test of validate method, of class GCodeValidator.
	 */
	@Test
	public void testValidateSuccessFromPartialOpen() {
		System.out.println("validate");
		GCodeValidator instance = new GCodeValidator(this.getClass().getResource("/postprocessor/validatorTest_3.gcode").getFile());
		assertTrue(instance.validate());
	}

	/**
	 * Test of validate method, of class GCodeValidator.
	 */
	@Test
	public void testValidateFailureOpenWithoutClose() {
		System.out.println("validate");
		GCodeValidator instance = new GCodeValidator(this.getClass().getResource("/postprocessor/validatorTest_openWithoutClose.gcode").getFile());
		assertFalse(instance.validate());
	}

}
