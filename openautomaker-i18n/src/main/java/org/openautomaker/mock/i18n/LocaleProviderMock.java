package org.openautomaker.mock.i18n;

import java.util.Locale;
import java.util.prefs.PreferenceChangeListener;

import org.openautomaker.environment.LocaleProvider;

public class LocaleProviderMock implements LocaleProvider {

	Locale locale = Locale.UK;

	@Override
	public Locale getValue() {
		return locale;
	}

	@Override
	public void addChangeListener(PreferenceChangeListener pcl) {
		//No setter so no need for listener logic.
	}

}
