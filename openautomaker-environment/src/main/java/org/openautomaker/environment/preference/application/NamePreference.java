package org.openautomaker.environment.preference.application;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleStringPreference;
import org.openautomaker.environment.properties.ApplicationProperties;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class NamePreference extends ASimpleStringPreference {

	private static final String OPENAUTOMAKER_NAME = "openautomaker.name";

	private final ApplicationProperties applicationProperties;

	@Inject
	protected NamePreference(
			ApplicationProperties applicationProperties) {

		this.applicationProperties = applicationProperties;
	}

	@Override
	public String getValue() {
		return applicationProperties.get(OPENAUTOMAKER_NAME);
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
