package org.openautomaker.environment.properties;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openautomaker.environment.preference.application.HomePathPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Encapsulates the native properties for the environment
 */
@Singleton
public class NativeProperties {

	private static final Logger LOGGER = LogManager.getLogger();

	private static final String NATIVE = "native";

	private static final String OPENAUTOMAKER = "openautomaker";

	private static final String OPENAUTOMAKER_NATIVE_PROPERTIES = "openautomaker-native.properties";

	private final Properties nativeProps;

	@Inject
	protected NativeProperties(HomePathPreference homePathPreference) {
		Path propPath = homePathPreference.getAppValue()
				.resolve(OPENAUTOMAKER)
				.resolve(NATIVE)
				.resolve(OPENAUTOMAKER_NATIVE_PROPERTIES);

		nativeProps = new Properties();
		try (InputStream is = Files.newInputStream(propPath)) {
			nativeProps.load(is);
		}
		catch (IOException e) {
			LOGGER.error("Native properties not found: " + propPath.toString());
		}
	}

	/**
	 * Returns the value of the provided named property
	 * 
	 * @param key - The property Key
	 * @return The value of the property or null
	 */
	public String get(String key) {
		return nativeProps.getProperty(key);
	}

	/**
	 * Get the set of all property names
	 * 
	 * @return Set<String> of property names
	 */
	public Set<String> getPropertyNames() {
		return nativeProps.stringPropertyNames();
	}
}
