package org.openautomaker.environment.preference.root;

import java.nio.file.Path;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.APathPreference;
import org.openautomaker.environment.preference.application.HomePathPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Location of firmware files
 */
@Singleton
public class FirmwarePathPreference extends APathPreference {

	private static final String FIRMWARE = "firmware";

	private final HomePathPreference homePathPreference;

	@Inject
	protected FirmwarePathPreference(
			HomePathPreference homePathPreference) {

		this.homePathPreference = homePathPreference;
	}

	@Override
	public Path getValue() {
		return homePathPreference.getAppValue().resolve(OPENAUTOMAKER).resolve(FIRMWARE);
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
