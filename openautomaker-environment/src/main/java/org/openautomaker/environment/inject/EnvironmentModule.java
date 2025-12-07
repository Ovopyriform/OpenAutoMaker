package org.openautomaker.environment.inject;

import org.openautomaker.environment.LocaleProvider;
import org.openautomaker.environment.preference.l10n.LocalePreference;

import com.google.inject.AbstractModule;

public class EnvironmentModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(LocaleProvider.class).to(LocalePreference.class);
	}
}
