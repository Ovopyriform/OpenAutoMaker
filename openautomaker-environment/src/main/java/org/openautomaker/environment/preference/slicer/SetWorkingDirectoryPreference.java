package org.openautomaker.environment.preference.slicer;

import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

import org.openautomaker.environment.preference.ASimpleBooleanPreference;
import org.openautomaker.environment.properties.NativeProperties;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class SetWorkingDirectoryPreference extends ASimpleBooleanPreference {

	private static final String SET_WORKING_DIRECTORY = "openautomaker.native.slicer.set_working_directory";

	private final NativeProperties nativeProperties;

	@Inject
	protected SetWorkingDirectoryPreference(
			NativeProperties nativeProperties) {

		this.nativeProperties = nativeProperties;
	}

	@Override
	public Boolean getValue() {
		return Boolean.valueOf(nativeProperties.get(SET_WORKING_DIRECTORY));
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
	public void setValue(Boolean value) {
		throw new UnsupportedOperationException("setValue not implemented for preference: " + getClass().getSimpleName());
	}

}
