package org.openautomaker.environment.preference.l10n;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleBooleanPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ShowMetricUnitsPreference extends ASimpleBooleanPreference {

	@Inject
	protected ShowMetricUnitsPreference() {

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
