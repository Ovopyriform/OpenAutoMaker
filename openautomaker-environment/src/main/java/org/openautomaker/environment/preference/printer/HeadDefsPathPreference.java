package org.openautomaker.environment.preference.printer;

import java.nio.file.Path;

import org.openautomaker.environment.preference.APairedPathPreference;
import org.openautomaker.environment.preference.application.HomePathPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Represents the user Print Profiles path
 */
@Singleton
public class HeadDefsPathPreference extends APairedPathPreference {

	private static final String HEADS = "heads";

	private final HomePathPreference homePathPreference;

	@Inject
	protected HeadDefsPathPreference(
			HomePathPreference homePathPreference) {

		this.homePathPreference = homePathPreference;
	}

	@Override
	/**
	 * Get the default value for the user print profiles path
	 * 
	 * @return Path - The default user print head definitions user path/openautomaker/heads
	 */
	public Path getDefault() {
		return homePathPreference.getUserValue().resolve(HEADS);
	}

	@Override
	public Path getUserValue() {
		Path userPath = super.getValue();
		return ensurePath(userPath) ? userPath : null;
	}

	@Override
	public Path getAppValue() {
		return homePathPreference.getAppValue().resolve(OPENAUTOMAKER).resolve(HEADS);
	}

}
