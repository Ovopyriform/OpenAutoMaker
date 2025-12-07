package org.openautomaker.environment.preference.product;

import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleStringPreference;
import org.openautomaker.environment.properties.ApplicationProperties;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ProductKeyPreference extends ASimpleStringPreference {

	private static final String OPENAUTOMAKER_PRODUCT_KEY = "openautomaker.product_key";

	private final ApplicationProperties applicationProperties;

	@Inject
	protected ProductKeyPreference(
			ApplicationProperties applicationProperties) {

		this.applicationProperties = applicationProperties;
	}

	@Override
	public String getValue() {
		return applicationProperties.get(OPENAUTOMAKER_PRODUCT_KEY);
	}

	@Override
	public void addChangeListener(PreferenceChangeListener listener) {
		throw new UnsupportedOperationException("addChangeListener not implemented for preference: " + getClass().getSimpleName());
	}

	@Override
	protected Preferences getNode() {
		throw new UnsupportedOperationException("getNode not implemented for preference: " + getClass().getSimpleName());
	}

	@Override
	public void setValue(String value) {
		throw new UnsupportedOperationException("setValue not implemented for preference: " + getClass().getSimpleName());
	}
}
