package org.openautomaker.environment.preference.printer;

import java.nio.file.Path;
import java.util.prefs.Preferences;

import org.openautomaker.environment.Slicer;
import org.openautomaker.environment.preference.APairedPathPreference;
import org.openautomaker.environment.preference.application.HomePathPreference;
import org.openautomaker.environment.preference.slicer.SlicerPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Represents the user Print Profiles path
 */
@Singleton
public class PrintProfilesPathPreference extends APairedPathPreference {

	private static final String PRINT_PROFILES = "print-profiles";

	private final HomePathPreference homePathPreference;
	private final SlicerPreference slicerPreference;

	@Inject
	protected PrintProfilesPathPreference(
			HomePathPreference homePathPreference,
			SlicerPreference slicerPreference) {

		this.homePathPreference = homePathPreference;
		this.slicerPreference = slicerPreference;
	}

	@Override
	protected Preferences getNode() {
		return getUserNode();
	}

	@Override
	/**
	 * Get the default value for the user print profiles path
	 * 
	 * @return Path - The default user print profiles path user path/openautomaker/print-profiles
	 */
	public Path getDefault() {
		return homePathPreference.getUserValue().resolve(PRINT_PROFILES);
	}

	@Override
	public Path getUserValue() {
		Path userPath = getDefault();
		return ensurePath(userPath) ? userPath : null;
	}

	@Override
	public Path getAppValue() {
		return homePathPreference.getAppValue().resolve(OPENAUTOMAKER).resolve(PRINT_PROFILES);
	}

	/**
	 * Returns the user print profile path for the slicer defined in SlicerPreference
	 * 
	 * @return Path - path to the user print profile
	 */
	public Path getUserPathForSlicer() {
		return getUserPathForSlicer(slicerPreference.getValue());

	}

	/**
	 * Returns the user print profile path for the provided Slicer
	 * 
	 * @return Path - path to the user print profile
	 */
	public Path getUserPathForSlicer(Slicer slicer) {
		Path slicerPath = getUserValue().resolve(slicer.getPathModifier());
		return ensurePath(slicerPath) ? slicerPath : null;
	}

	/**
	 * Returns the app print profile path for the slicer defined in SlicerPreference
	 * 
	 * @return Path - path to the app print profile
	 */
	public Path getAppPathForSlicer() {
		return getAppPathForSlicer(slicerPreference.getValue());
	}

	/**
	 * Returns the app print profile path for the provided Slicer
	 * 
	 * @return Path - path to the app print profile
	 */
	public Path getAppPathForSlicer(Slicer slicer) {
		return getAppValue().resolve(slicer.getPathModifier());
	}

}
