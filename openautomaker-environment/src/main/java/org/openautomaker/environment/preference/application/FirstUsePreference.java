package org.openautomaker.environment.preference.application;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleBooleanPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Preference representing if this is the first use of the package
 */
@Singleton
public class FirstUsePreference extends ASimpleBooleanPreference {

	@Inject
	protected FirstUsePreference() {
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
