package org.openautomaker.environment.preference.printer;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.APreference;

import com.vdurmont.semver4j.Semver;

import jakarta.inject.Inject;

public class LastFirmwareVersionPreference extends APreference<Semver> {

	@Inject
	protected LastFirmwareVersionPreference() {

	}

	@Override
	public Semver getValue() {
		String version = getNode().get(getKey(), null);
		if (version == null)
			return null;

		return new Semver(version);
	}

	@Override
	protected Preferences getNode() {
		return getUserNode();
	}

	@Override
	public void setValue(Semver version) {
		getNode().put(getKey(), version.getValue());
	}
}
