package org.openautomaker.environment.preference.application;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleBooleanPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Preference to represent the users choice for show tooltips
 */
@Singleton
public class ShowTooltipsPreference extends ASimpleBooleanPreference {

	@Inject
	protected ShowTooltipsPreference() {
		super();
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
