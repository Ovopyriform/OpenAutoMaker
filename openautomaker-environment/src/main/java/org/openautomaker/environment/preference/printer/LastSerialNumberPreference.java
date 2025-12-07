package org.openautomaker.environment.preference.printer;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleStringPreference;

import jakarta.inject.Inject;

public class LastSerialNumberPreference extends ASimpleStringPreference {

	@Inject
	protected LastSerialNumberPreference() {

	}

	@Override
	protected Preferences getNode() {
		return getUserNode();
	}
}
