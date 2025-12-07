package org.openautomaker.environment.preference.camera;

import java.nio.file.Path;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.APairedPathPreference;
import org.openautomaker.environment.preference.application.HomePathPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class CameraProfilesPathPreference extends APairedPathPreference {

	protected static final String CAMERA_PROFILES = "camera-profiles";

	private final HomePathPreference homePathPreference;

	@Inject
	protected CameraProfilesPathPreference(
			HomePathPreference homePathPreference) {

		this.homePathPreference = homePathPreference;
	}

	@Override
	public Path getUserValue() {
		Path cameraProfiles = homePathPreference.getUserValue().resolve(CAMERA_PROFILES);
		return ensurePath(cameraProfiles) ? cameraProfiles : null;
	}

	@Override
	public Path getAppValue() {
		return homePathPreference.getAppValue().resolve(OPENAUTOMAKER).resolve(CAMERA_PROFILES);
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
	public void setValue(Path value) {
		throw new UnsupportedOperationException("setValue not implemented for preference: " + getClass().getSimpleName());
	}

}
