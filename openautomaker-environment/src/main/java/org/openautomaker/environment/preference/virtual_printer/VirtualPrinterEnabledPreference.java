package org.openautomaker.environment.preference.virtual_printer;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleBooleanPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class VirtualPrinterEnabledPreference extends ASimpleBooleanPreference {

	@Inject
	protected VirtualPrinterEnabledPreference() {

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
