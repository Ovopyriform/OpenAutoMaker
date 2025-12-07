package org.openautomaker.environment.preference.slicer;

import java.nio.file.Path;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.APairedPathPreference;
import org.openautomaker.environment.preference.application.HomePathPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Represents a Macro path preference
 */
@Singleton
public class MacroPathPreference extends APairedPathPreference {

	protected static final String MACROS = "macros";

	private final HomePathPreference homePathPreference;

	@Inject
	protected MacroPathPreference(
			HomePathPreference homePathPreference) {

		this.homePathPreference = homePathPreference;
	}

	@Override
	public Path getAppValue() {
		return homePathPreference.getAppValue().resolve(OPENAUTOMAKER).resolve(MACROS);
	}

	@Override
	public Path getUserValue() {
		Path macrosPath = homePathPreference.getUserValue().resolve(MACROS);
		return ensurePath(macrosPath) ? macrosPath : null;
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
