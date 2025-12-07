package org.openautomaker.base.importers.twod.svg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ShortestArcTest {

	@Test
	public void testShortestArc1() {
		ShortestArc shortestArc = new ShortestArc(2.14, -2.14);

		assertEquals(2.00318, shortestArc.getAngularDifference(), 0.001);
		assertTrue(shortestArc.getStepValue() > 0);
	}

	@Test
	public void testShortestArc2() {
		ShortestArc shortestArc = new ShortestArc(2.14, 2.8);

		assertEquals(0.66, shortestArc.getAngularDifference(), 0.001);
		assertTrue(shortestArc.getStepValue() > 0);
	}

}
