package org.openautomaker.base.postprocessor;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.openautomaker.base.inject.postprocessor.GCodeOutputWriterFactory;
import org.openautomaker.base.postprocessor.events.GCodeParseEvent;
import org.openautomaker.test_library.GuiceExtension;

import jakarta.inject.Inject;

//TODO: Test case needs filling out.  How to test?
@ExtendWith(GuiceExtension.class)
public class PostProcessingBufferTest {

	@Inject
	GCodeOutputWriterFactory gCodeOutputWriterFactory;

	@TempDir
	static Path sharedTempDir;

	/**
	 * Test of emptyBufferToOutput method, of class PostProcessingBuffer.
	 */
	@Test
	public void testEmptyBufferToOutput() throws Exception {
		System.out.println("emptyBufferToOutput");
		GCodeOutputWriter outputWriter = gCodeOutputWriterFactory.create(sharedTempDir.resolve("PostProcessingBufferTest.testEmptyBufferToOutput.gcode"));
		PostProcessingBuffer instance = new PostProcessingBuffer();
		instance.emptyBufferToOutput(outputWriter);
		// TODO review the generated test code and remove the default call to fail.
		//fail("The test case is a prototype.");
	}

	/**
	 * Test of closeNozzle method, of class PostProcessingBuffer.
	 */
	@Test
	public void testCloseNozzle() {
		System.out.println("closeNozzle");
		String comment = "";
		GCodeOutputWriter outputWriter = gCodeOutputWriterFactory.create(sharedTempDir.resolve("PostProcessingBufferTest.testCloseNozzle.gcode"));
		PostProcessingBuffer instance = new PostProcessingBuffer();
		instance.closeNozzle(comment, outputWriter);
		// TODO review the generated test code and remove the default call to fail.
		//fail("The test case is a prototype.");
	}

	/**
	 * Test of openNozzleFullyBeforeExtrusion method, of class PostProcessingBuffer.
	 */
	@Test
	public void testOpenNozzleFullyBeforeExtrusion() {
		System.out.println("openNozzleFullyBeforeExtrusion");
		PostProcessingBuffer instance = new PostProcessingBuffer();
		instance.openNozzleFullyBeforeExtrusion();
		// TODO review the generated test code and remove the default call to fail.
		//fail("The test case is a prototype.");
	}

	/**
	 * Test of add method, of class PostProcessingBuffer.
	 */
	@Test
	public void testAdd() {
		System.out.println("add");
		GCodeParseEvent e = null;
		PostProcessingBuffer instance = new PostProcessingBuffer();
		boolean expResult = false;
		boolean result = instance.add(e);
		//assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		//fail("The test case is a prototype.");
	}

	/**
	 * Test of clear method, of class PostProcessingBuffer.
	 */
	@Test
	public void testClear() {
		System.out.println("clear");
		PostProcessingBuffer instance = new PostProcessingBuffer();
		instance.clear();
		// TODO review the generated test code and remove the default call to fail.
		//fail("The test case is a prototype.");
	}

}
