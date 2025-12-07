package org.openautomaker.environment.preference;

import java.nio.file.Path;
import java.util.prefs.Preferences;

/**
 * Abstract representing a paired preference. A preference which has a modifiable user value and a fixed application value.
 * 
 * Provides getUserValue and getAppValue. getValue always defaults to the user value
 */
public abstract class APairedPathPreference extends APathPreference implements PairedPreference<Path> {

	/**
	 * Returns the user node. Should default to user node for paired preferences
	 */
	@Override
	protected Preferences getNode() {
		return getUserNode();
	}

	@Override
	public Path getValue() {
		return getUserValue();
	}

	/**
	 * Defaults to the application value for paired preferences
	 * 
	 * @return The application value
	 */
	@Override
	public Path getDefault() {
		return getAppValue();
	};
}
