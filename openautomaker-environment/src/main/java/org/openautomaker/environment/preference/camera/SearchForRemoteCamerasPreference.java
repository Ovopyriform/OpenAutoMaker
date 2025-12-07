package org.openautomaker.environment.preference.camera;

import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleBooleanPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Preference to determine if we should search for remote cameras
 */
@Singleton
public class SearchForRemoteCamerasPreference extends ASimpleBooleanPreference {

	@Inject
	protected SearchForRemoteCamerasPreference() {

	}

	@Override
	public Boolean getDefault() {
		return Boolean.TRUE;
	}

	@Override
	protected Preferences getNode() {
		return getUserNode();
	}
}
