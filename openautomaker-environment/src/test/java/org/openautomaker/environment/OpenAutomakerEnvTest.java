package org.openautomaker.environment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openautomaker.test_library.GuiceExtension;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.framework.junit5.utils.FXUtils;

import jakarta.inject.Inject;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.stage.Stage;

@ExtendWith({ GuiceExtension.class, ApplicationExtension.class })
public class OpenAutomakerEnvTest {

	private static final String TEST_TRANSLATION_KEY = "buttonText.addModel";

	private static final String TEST_PROPERTY_KEY = "test.property.key";
	private static final String TEST_PROPERTY_VALUE = "testPropertyValue";

	private static final String OPENAUTOMAKER = "openautomaker";
	private static final String OPENAUTOMAKER_LOCALE = OPENAUTOMAKER + ".locale";
	private static final String OPENAUTOMAKER_PROPERTIES = OPENAUTOMAKER + ".properties";

	@Inject
	private OpenAutomakerEnv environment;

	@Start
	public void start(Stage stage) {

	}

	@Test
	void has3DSupport_test() throws Exception {
		FXUtils.runAndWait(() -> {
			assertEquals(Platform.isSupported(ConditionalFeature.SCENE3D), environment.has3DSupport());
		});
	}

	@Test
	void getMachineType_test() {

	}

	//	@Test
	//	void getLocale_test() {
	//		OpenAutomakerEnv env = OpenAutomakerEnv.get();
	//		Locale locale = env.getLocale();
	//
	//		Properties systemProps = System.getProperties();
	//
	//		assertEquals(systemProps.getProperty("user.country"), locale.getCountry());
	//		assertEquals(systemProps.getProperty("user.language"), locale.getLanguage());
	//
	//		// Set language should alter this
	//
	//	}

	//	@Test
	//	void getApplicationPath_test() {
	//		OpenAutomakerEnv env = OpenAutomakerEnv.get();
	//		Properties systemProps = System.getProperties();
	//		assertEquals(Paths.get(systemProps.getProperty("user.dir"), "..", "openautomaker-test-environment", "env", "app", OPENAUTOMAKER).toString(), env.getApplicationPath().toString());
	//
	//		//TODO: Check named path retrieval
	//	}

	//	@Test
	//	void getUserPath_test() {
	//		OpenAutomakerEnv env = OpenAutomakerEnv.get();
	//		Properties systemProps = System.getProperties();
	//		assertEquals(Paths.get(systemProps.getProperty("user.dir"), "..", "openautomaker-test-environment", "env", "usr", OPENAUTOMAKER).toString(), new HomePathPreference().getUserValue().toString());
	//
	//		//TODO: Check named path retrieval
	//	}

	//	@Test
	//	void getName_test() {
	//		OpenAutomakerEnv env = OpenAutomakerEnv.get();
	//		assertEquals("OpenAutomaker.org: Test Environment", env.getName());
	//	}
	//
	//	@Test
	//	void getShortName_test() {
	//		OpenAutomakerEnv env = OpenAutomakerEnv.get();
	//		assertEquals("OpenAutomaker: Test Environment", env.getShortName());
	//	}
}
