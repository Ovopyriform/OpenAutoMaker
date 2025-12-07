package org.openautomaker.environment.preference.modeling;

import java.nio.file.Path;

import org.openautomaker.environment.preference.APairedPathPreference;
import org.openautomaker.environment.preference.application.HomePathPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ModelsPathPreference extends APairedPathPreference {

	private static final String MODELS = "models";

	private final HomePathPreference homePathPreference;

	@Inject
	protected ModelsPathPreference(
			HomePathPreference homePathPreference) {

		this.homePathPreference = homePathPreference;
	}

	@Override
	public Path getAppValue() {
		return homePathPreference.getAppValue().resolve(OPENAUTOMAKER).resolve(MODELS);
	}

	@Override
	public Path getUserValue() {
		return getPath();
	}
}
