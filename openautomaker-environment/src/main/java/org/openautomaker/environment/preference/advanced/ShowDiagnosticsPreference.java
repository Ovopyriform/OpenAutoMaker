package org.openautomaker.environment.preference.advanced;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleBooleanPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Preference to determine if we should show diagnostic info
 */
@Singleton
public class ShowDiagnosticsPreference extends ASimpleBooleanPreference {

	@Inject
	protected ShowDiagnosticsPreference() {
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
