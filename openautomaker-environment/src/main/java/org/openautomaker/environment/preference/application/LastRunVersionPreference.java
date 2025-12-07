package org.openautomaker.environment.preference.application;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.APreference;

import com.vdurmont.semver4j.Semver;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class LastRunVersionPreference extends APreference<Semver> {

	private final VersionPreference versionPreference;

	@Inject
	protected LastRunVersionPreference(VersionPreference versionPreference) {
		this.versionPreference = versionPreference;
	}

	@Override
	protected Preferences getNode() {
		return getUserNode();
	}

	@Override
	public Semver getValue() {
		String lastRunVersion = getNode().get(getKey(), null);
		if (lastRunVersion != null)
			return new Semver(lastRunVersion);

		Semver appVersion = versionPreference.getValue();
		setValue(appVersion);
		return appVersion;
	}

	@Override
	public void setValue(Semver value) {
		getNode().put(getKey(), value.getValue());
	}
}
