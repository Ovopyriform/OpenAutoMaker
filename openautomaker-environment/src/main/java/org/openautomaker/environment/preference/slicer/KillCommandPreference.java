package org.openautomaker.environment.preference.slicer;

import java.nio.file.Path;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.APathPreference;
import org.openautomaker.environment.preference.application.HomePathPreference;
import org.openautomaker.environment.properties.NativeProperties;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

//TODO: add in kill command params
@Singleton
public class KillCommandPreference extends APathPreference {

	private static final String KILL_COMMAND = "openautomaker.native.slicer.kill_command";
	private static final String NATIVE = "native";

	private final NativeProperties nativeProperties;
	private final HomePathPreference homePathPreference;

	@Inject
	protected KillCommandPreference(
			NativeProperties nativeProperties,
			HomePathPreference homePathPreference) {

		this.nativeProperties = nativeProperties;
		this.homePathPreference = homePathPreference;
	}

	@Override
	public Path getValue() {
		return homePathPreference.getAppValue()
				.resolve(OPENAUTOMAKER)
				.resolve(NATIVE)
				.resolve(nativeProperties.get(KILL_COMMAND));
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
