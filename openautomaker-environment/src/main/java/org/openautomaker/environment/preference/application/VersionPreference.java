package org.openautomaker.environment.preference.application;

import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.APreference;
import org.openautomaker.environment.properties.ApplicationProperties;

import com.vdurmont.semver4j.Semver;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Application property provided in the same way as other app preferences
 */
@Singleton
public class VersionPreference extends APreference<Semver> {

	private static final String OPENAUTOMAKER_VERSION = "openautomaker.version";

	private final ApplicationProperties applicationProperties;

	@Inject
	protected VersionPreference(
			ApplicationProperties applicationProperties) {

		this.applicationProperties = applicationProperties;
	}

	@Override
	public Semver getValue() {
		return new Semver(applicationProperties.get(OPENAUTOMAKER_VERSION));
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
	public void setValue(Semver value) {
		throw new UnsupportedOperationException("setValue not implemented for preference: " + getClass().getSimpleName());
	}
}
