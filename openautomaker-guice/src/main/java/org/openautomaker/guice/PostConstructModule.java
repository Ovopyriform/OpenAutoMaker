package org.openautomaker.guice;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.ProvisionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

import jakarta.annotation.PostConstruct;

/**
 * Module to allow methods annotated with @{@link javax.annotation.PostConstruct} to be executed
 * after Guice context has been setup.
 *
 * Guice by default has no support for @{@link javax.annotation.PostConstruct}.
 * The <a href="http://code.mycila.com/guice/">MycilaGuice library</a> has several Guice extentions among which
 * also support for JSR-250 (Object lifecycle, like @PostConstruct annotation).
 * But since just @{@link javax.annotation.PostConstruct} is needed and do not want to include yet another dependency, this module was created.
 *
 * Cannot be implemented in Groovy since Groovy has a problem with generic inner types
 * and therefore give a compiler error on the ```new InjectionListener<I> { .. }``` in the ```hear``` function.
 */
public final class PostConstructModule extends AbstractModule {

	@Override
	protected void configure() {
		//TODO: Feels like this shouldn't match on null provisions.  Look into how to sort this out.
		binder().bindListener(Matchers.any(), new PostConstructProvisionListener());
	}

	/**
	 * Utility class to execute methods annotated with @PostConstruct on an instance.
	 */
	private static class PostConstructProcessor {

		private static Logger LOGGER = LogManager.getLogger();

		/**
		 * Filter to get only the {@link Method}s annotated with {@link PostConstruct} annotation.
		 */
		private final Predicate<Method> filterPostConstructMethods = m -> {
			PostConstruct annotation = m.getAnnotation(PostConstruct.class);
			return annotation != null;
		};

		/**
		 * Invoke a method on an instance by consuming {@link Method}s.
		 *
		 * @param instance Instance of T on which to invoke the method.
		 * @param <T>      Instance type.
		 * @return {@link Consumer} of {@link Method}s which invokes the methods on behalf of the #instance.
		 */
		private final <T> Consumer<Method> invokeMethodOnInstance(final T instance) {
			return method -> {
				try {
					if (LOGGER.isInfoEnabled())
						LOGGER.info(method.getDeclaringClass().toString() + ":" + method.getName());

					method.invoke(instance);
				}
				catch (final Exception e) {
					throw new RuntimeException(String.format("@PostConstruct error: %s", e.getMessage()), e);
				}
			};
		}

		/**
		 * Invoke all methods annotated with @PostConstruct on given instance.
		 * 
		 * @param instance The instance on which to execute @PostConstruct methods
		 * @param <T>      Type of instance.
		 */
		final <T> void invokePostConstructMethodsOn(final T instance) {
			if (instance == null)
				return;

			Arrays.asList(instance.getClass().getMethods())
					.stream()
					.filter(filterPostConstructMethods)
					.forEach(invokeMethodOnInstance(instance));
		}
	}

	/**
	 * {@link ProvisionListener} implementation which executes the @{@link PostConstruct} annotated
	 * methods on instances created via a @{@link com.google.inject.Provides} annotation.
	 */
	final static class PostConstructProvisionListener extends PostConstructProcessor implements ProvisionListener {
		@Override
		public <T> void onProvision(final ProvisionInvocation<T> provision) {
			invokePostConstructMethodsOn(provision.provision());
		}
	}

	/**
	 * {@link TypeListener} implementation which executes the @{@link PostConstruct} annotated
	 * methods on instances created by Guice (not via @Provides annotation).
	 */
	final static class PostConstructTypeListener extends PostConstructProcessor implements TypeListener {
		@Override
		public <I> void hear(final TypeLiteral<I> type, final TypeEncounter<I> encounter) {
			// register listener on any injected type
			encounter.register(new InjectionListener<I>() {
				@Override
				public void afterInjection(final I injectee) {
					invokePostConstructMethodsOn(injectee);
				}
			});
		}
	}
}
