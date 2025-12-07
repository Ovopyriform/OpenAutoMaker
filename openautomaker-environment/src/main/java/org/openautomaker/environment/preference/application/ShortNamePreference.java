package org.openautomaker.environment.preference.application;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleStringPreference;
import org.openautomaker.environment.properties.ApplicationProperties;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ShortNamePreference extends ASimpleStringPreference {

	private static final String OPENAUTOMAKER_SHORT_NAME = "openautomaker.short_name";

	private final ApplicationProperties applicationProperties;

	@Inject
	protected ShortNamePreference(
			ApplicationProperties applicationProperties) {

		this.applicationProperties = applicationProperties;
	}

	@Override
	public String getValue() {
		return applicationProperties.get(OPENAUTOMAKER_SHORT_NAME);
	}

	@Override
	public void setValue(String value) {
		throw new UnsupportedOperationException("setValue not supported for preference: " + getClass().getSimpleName());
	}

	@Override
	protected Preferences getNode() {
		return getSystemNode();
	}

}
