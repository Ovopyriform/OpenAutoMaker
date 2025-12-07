package org.openautomaker.environment.preference.paths;

import java.nio.file.Path;
import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.APathPreference;
import org.openautomaker.environment.preference.application.HomePathPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

//TODO: This looks incorrect.  Work out how to fix.
@Singleton
public class GCodePathPreference extends APathPreference {

	private final HomePathPreference homePathPreference;

	@Inject
	protected GCodePathPreference(
			HomePathPreference homePathPreference) {

		this.homePathPreference = homePathPreference;
	}

	@Override
	public Path getDefault() {
		return homePathPreference.getUserValue();
	}

	@Override
	protected Preferences getNode() {
		return getUserNode();
	}

}
