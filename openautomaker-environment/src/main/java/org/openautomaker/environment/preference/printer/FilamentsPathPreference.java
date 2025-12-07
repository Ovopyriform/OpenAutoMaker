package org.openautomaker.environment.preference.printer;

import java.nio.file.Path;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.APairedPathPreference;
import org.openautomaker.environment.preference.application.HomePathPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class FilamentsPathPreference extends APairedPathPreference {

	private static final String FILAMENTS = "filaments";

	private final HomePathPreference homePathPreference;

	@Inject
	protected FilamentsPathPreference(
			HomePathPreference homePathPreference) {

		this.homePathPreference = homePathPreference;
	}

	@Override
	public Path getAppValue() {
		return homePathPreference.getAppValue().resolve(OPENAUTOMAKER).resolve(FILAMENTS);
	}

	@Override
	public Path getUserValue() {
		Path filamentPath = homePathPreference.getUserValue().resolve(FILAMENTS);
		return ensurePath(filamentPath) ? filamentPath : null;
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
