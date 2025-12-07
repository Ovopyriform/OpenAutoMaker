package org.openautomaker.environment.preference.slicer;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleBooleanPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Preference to determine if we should show the GCode preview panel
 */
@Singleton
public class ShowGCodePreviewPreference extends ASimpleBooleanPreference {

	@Inject
	protected ShowGCodePreviewPreference() {

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
