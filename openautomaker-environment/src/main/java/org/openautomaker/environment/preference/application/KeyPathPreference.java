package org.openautomaker.environment.preference.application;

import java.nio.file.Path;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.APathPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class KeyPathPreference extends APathPreference {

	private static final String KEY = "key";
	private static final String PRIVATE_KEY = "openautomaker-root.ssh";

	private final HomePathPreference homePathPreference;

	@Inject
	protected KeyPathPreference(
			HomePathPreference homePathPreference) {

		super();
		this.homePathPreference = homePathPreference;
	}

	@Override
	public Path getValue() {
		return homePathPreference.getAppValue().resolve(KEY).resolve(PRIVATE_KEY);
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
