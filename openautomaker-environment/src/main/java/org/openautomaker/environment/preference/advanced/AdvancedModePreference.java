package org.openautomaker.environment.preference.advanced;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleBooleanPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Preference representing if the user has selected the Advanced Mode
 */
@Singleton
public class AdvancedModePreference extends ASimpleBooleanPreference {

	@Inject
	protected AdvancedModePreference(
			ShowDiagnosticsPreference showDiagnosticsPreference,
			ShowGCodeConsolePreference showGCodeConsolePreference,
			ShowAdjustmentsPreference showAdjustmentsPreference,
			ShowSnapshotPreference showSnapshotPreference) {

		super();

		// Chain the other advanced preferences from this preference
		addChangeListener((evt) -> {
			Boolean value = getValue();
			showDiagnosticsPreference.setValue(value);
			showGCodeConsolePreference.setValue(value);
			showAdjustmentsPreference.setValue(value);
			showSnapshotPreference.setValue(value);
		});
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
