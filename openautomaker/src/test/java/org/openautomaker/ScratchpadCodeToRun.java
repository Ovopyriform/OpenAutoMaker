package org.openautomaker;

import org.junit.jupiter.api.Test;

public class ScratchpadCodeToRun {

	public ScratchpadCodeToRun() {
	}

	@Test
	public void runSomeCode() throws Exception {

		String osName = System.getProperty("os.name");

		System.out.println("OSName: " + osName);
		System.out.println("Does it match ^Mac.*?: " + osName.matches("^Mac.*"));

	}

}
