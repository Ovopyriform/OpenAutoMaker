package org.openautomaker.environment.preference.l10n;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleFloatPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class GBPToLocalMultiplierPreference extends ASimpleFloatPreference {

	@Inject
	protected GBPToLocalMultiplierPreference() {

	}

	@Override
	public Float getDefault() {
		return Float.valueOf(1);
	}

	@Override
	protected Preferences getNode() {
		return getUserNode();
	}
}
