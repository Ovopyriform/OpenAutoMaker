package org.openautomaker.environment.preference.root;

import java.nio.file.Path;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.APathPreference;
import org.openautomaker.environment.preference.application.HomePathPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Temp path for OpenAutomaker. Should be a platform temp location, not in user home
 */
//TODO: This shouldn't be a temporary file in the users openautomaker home.  Should be an actual temp file
@Singleton
public class TempPathPreference extends APathPreference {

	private static final String TEMP = "temp";

	private final HomePathPreference homePathPreference;

	@Inject
	protected TempPathPreference(
			HomePathPreference homePathPreference) {

		this.homePathPreference = homePathPreference;
	}

	@Override
	public Path getValue() {
		Path printJobsPath = homePathPreference.getUserValue().resolve(TEMP);
		return ensurePath(printJobsPath) ? printJobsPath : null;
	}

	@Override
	public void addChangeListener(PreferenceChangeListener listener) {
		throw new UnsupportedOperationException("addChangeListener not implemented for preference: " + getClass().getSimpleName());
	}

	@Override
	protected Preferences getNode() {
		throw new UnsupportedOperationException("getNode not implemented for preference: " + getClass().getSimpleName());
	}

	@Override
	public void setValue(Path value) {
		throw new UnsupportedOperationException("setValue not implemented for preference: " + getClass().getSimpleName());
	}

}
