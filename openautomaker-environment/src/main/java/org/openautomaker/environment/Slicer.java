package org.openautomaker.environment;

import java.nio.file.Path;

/**
 *
 */
public enum Slicer {
	CURA_4("Cura 4", Path.of("Cura4")),
	CURA_5("Cura 5 (Experimental)", Path.of("Cura5"));

	//TODO: Cleanup
	//	// These two need to be in a constants file somewhere.
	//	private static final String OPENAUTOMAKER = "openautomaker";
	//	private static final String NATIVE = "native";
	//
	//	private static final String SLICER_EXECUTABLE = "openautomaker.native.slicer.executable";
	//	private static final String SLICER_PARAMS = "openautomaker.native.slicer.params";
	//	private static final String SLICER_PARAMS_MESH = "openautomaker.native.slicer.params.mesh";
	//
	//	//TODO: Slicer Shell configuration.  At the moment, only used for Windows
	//	private static final String SLICER_SHELL = "openautomaker.native.slicer.shell.executable";
	//	private static final String SLICER_SHELL_PARAMS = "openautomaker.native.slicer.shell.params";

	private final String friendlyName;
	private final Path pathModifier;

	private Slicer(String friendlyName, Path pathModifier) {
		this.friendlyName = friendlyName;
		this.pathModifier = pathModifier;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	/**
	 * Returns the path element modifier for this slicer
	 * 
	 * @return Path - contains the modifier for this slicer
	 */
	public Path getPathModifier() {
		return pathModifier;
	}

	@Override
	public String toString() {
		return name();
	}
}
