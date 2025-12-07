package org.openautomaker.environment.preference.advanced;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleBooleanPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Preference to determine is we show the snapshot panel in advanced mode
 */
@Singleton
public class ShowSnapshotPreference extends ASimpleBooleanPreference {

	@Inject
	protected ShowSnapshotPreference() {
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
