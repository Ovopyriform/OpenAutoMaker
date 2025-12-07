package org.openautomaker.test_library;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.junit.platform.commons.support.AnnotationSupport;
import org.junit.platform.commons.support.HierarchyTraversalMode;
import org.junit.platform.commons.support.ReflectionSupport;
import org.openautomaker.guice.GuiceContext;

import jakarta.inject.Inject;

/**
 * Adds Guice DI to a test
 */
public class GuiceExtension implements TestInstancePostProcessor {
	
	private static TestProperties testProps = new TestProperties();

	private final GuiceContext guiceContext;

	public GuiceExtension() {
		guiceContext = new GuiceContext(this, testProps::getGuiceModules);
		guiceContext.init();
	}

	@Override
	public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
		List<Field> fields = ReflectionSupport.findFields(
				testInstance.getClass(),
				field -> AnnotationSupport.isAnnotated(field, Inject.class), HierarchyTraversalMode.BOTTOM_UP);

		for (Field field : fields) {
			field.trySetAccessible();
			if (field.get(testInstance) == null) {
				field.set(testInstance, guiceContext.getInstance(field.getType()));
			}
		}

	}

}
