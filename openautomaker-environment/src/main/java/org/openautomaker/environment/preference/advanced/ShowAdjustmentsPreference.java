package org.openautomaker.environment.preference.advanced;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleBooleanPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Preference to determine if we show the adjustments panel in advanced mode
 */
@Singleton
public class ShowAdjustmentsPreference extends ASimpleBooleanPreference {

	@Inject
	protected ShowAdjustmentsPreference() {
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
