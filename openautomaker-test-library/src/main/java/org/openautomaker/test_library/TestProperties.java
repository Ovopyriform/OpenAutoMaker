package org.openautomaker.test_library;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Module;

public class TestProperties extends Properties {

	private static final Logger LOGGER = LogManager.getLogger();

	private static final long serialVersionUID = 173865802733841087L;

	private static final String TESTING_PROPERTIES = "openautomaker-testing.properties";
	
	private static final String TESTING_GUICE_MODULES = "guice.modules";

	public TestProperties() {
		super();

		LOGGER.info("Loading openautomaker-testing.properties");
		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		try (InputStream resourceStream = loader.getResourceAsStream(TESTING_PROPERTIES)) {

			LOGGER.debug("Bytes Available: " + resourceStream.available());

			load(resourceStream);
		}
		catch (IOException e) {
			LOGGER.error("Could not load openautomaker-testing.properties");
			// IO Exception.  Something bad happened we can't do anything about.
			e.printStackTrace();
		}
	}
	
	public List<Module> getGuiceModules() {
		List<String> moduleNames = Arrays.asList(getProperty(TESTING_GUICE_MODULES).split(","));
		LOGGER.debug("Guice Modules: " + moduleNames.toString());

		List<Module> moduleList = new LinkedList<>();
		moduleNames.forEach((moduleName) -> {
			try {
				@SuppressWarnings("unchecked")
				Class<? extends Module> clazz = (Class<? extends Module>) Class.forName(moduleName);
				Constructor<? extends Module> constructor = clazz.getConstructor();
				moduleList.add(constructor.newInstance());
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		return List.copyOf(moduleList);
	}

}
