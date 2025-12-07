package org.openautomaker.environment.preference.advanced;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleBooleanPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Preference to determine if we should show the GCode console in advanced mode
 */
@Singleton
public class ShowGCodeConsolePreference extends ASimpleBooleanPreference {

	@Inject
	protected ShowGCodeConsolePreference() {
		super();
	}

	@Override
	public Boolean getDefault() {
		return Boolean.FALSE;
	}

	@Override
	protected Preferences getNode() {
		return getUserNode();
	}
}
