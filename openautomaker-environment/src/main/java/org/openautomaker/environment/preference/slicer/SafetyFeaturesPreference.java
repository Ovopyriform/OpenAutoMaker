package org.openautomaker.environment.preference.slicer;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleBooleanPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Boolean Preference to represent if safety features are enabled
 */
@Singleton
public class SafetyFeaturesPreference extends ASimpleBooleanPreference {

	@Inject
	protected SafetyFeaturesPreference() {

	}

	@Override
	public Boolean getDefault() {
		return Boolean.TRUE;
	}

	@Override
	protected Preferences getNode() {
		return getUserNode();
	}

}
