package org.openautomaker.environment.preference.printer;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleBooleanPreference;

import jakarta.inject.Singleton;

/**
 * Preference to determine if we should detect loaded filament
 */
@Singleton
public class DetectLoadedFilamentPreference extends ASimpleBooleanPreference {

	@Override
	public Boolean getDefault() {
		return Boolean.TRUE;
	}

	@Override
	protected Preferences getNode() {
		return getUserNode();
	}

}
