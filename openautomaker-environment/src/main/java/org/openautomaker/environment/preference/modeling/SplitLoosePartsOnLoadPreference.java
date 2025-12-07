package org.openautomaker.environment.preference.modeling;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleBooleanPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Preference to determine if loose mode parts should be split on load
 */
@Singleton
public class SplitLoosePartsOnLoadPreference extends ASimpleBooleanPreference {

	@Inject
	protected SplitLoosePartsOnLoadPreference() {

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
