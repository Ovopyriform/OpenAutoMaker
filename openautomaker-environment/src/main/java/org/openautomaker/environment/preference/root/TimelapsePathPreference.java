package org.openautomaker.environment.preference.root;

import java.nio.file.Path;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.APathPreference;
import org.openautomaker.environment.preference.application.HomePathPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Location to store timelapse videos/photos
 */
@Singleton
public class TimelapsePathPreference extends APathPreference {

	private static final String TIMELAPSE = "timelapse";

	private final HomePathPreference homePathPreference;

	@Inject
	protected TimelapsePathPreference(
			HomePathPreference homePathPreference) {

		this.homePathPreference = homePathPreference;
	}

	@Override
	public Path getValue() {
		Path timelapsePath = homePathPreference.getUserValue().resolve(TIMELAPSE);
		return ensurePath(timelapsePath) ? timelapsePath : null;
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
