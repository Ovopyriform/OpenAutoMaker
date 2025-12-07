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
 * Encapsulates the application properties defined in the app_root/openautomaker.properties file
 *
 */
@Singleton
public final class ApplicationProperties {
	
	private static final Logger LOGGER = LogManager.getLogger();

	private static final String OPENAUTOMAKER_PROPERTIES = "openautomaker.properties";

	//private static ApplicationProperties INSTANCE;
	
	private final Properties appProps;

	@Inject
	protected ApplicationProperties(HomePathPreference homePathPreference) {
		Path propPath = homePathPreference.getAppValue().resolve(OPENAUTOMAKER_PROPERTIES);
		
		appProps = new Properties();
		try (InputStream is = Files.newInputStream(propPath)) {
			appProps.load(is);
		}
		catch (IOException e) {
			LOGGER.error("Application properties not found: " + propPath.toString());
		}
	}
	
	public String get(String key) {
		return appProps.getProperty(key);
	}

	public Set<String> getPropertyNames() {
		return appProps.stringPropertyNames();
	}
}
