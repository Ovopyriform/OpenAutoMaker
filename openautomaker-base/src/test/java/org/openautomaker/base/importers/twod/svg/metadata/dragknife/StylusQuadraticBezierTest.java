package org.openautomaker.base.importers.twod.svg.metadata.dragknife;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openautomaker.base.postprocessor.nouveau.nodes.GCodeEventNode;

public class StylusQuadraticBezierTest {

	/**
	 * Test of renderToGCodeNode method, of class StylusQuadraticBezier.
	 */
	@Test
	public void testRenderToGCodeNode() {
		System.out.println("renderToGCodeNode");
		StylusQuadraticBezier instance = new StylusQuadraticBezier(0, 0, 10, 10, 20, 0, "");
		//TODO: Need a way to generate this value.  Perhaps similar to ObjectMapper.readTree?
		GCodeEventNode expResult = null;
		List<GCodeEventNode> result = instance.renderToGCodeNode();
		//        assertEquals(expResult, result);
		//        fail("The test case is a prototype.");
	}

}
