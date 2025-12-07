package org.openautomaker.guice;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.inject.Injector;

import javafx.scene.Node;
import javafx.util.Builder;

/**
 * Builder which injects Guice dependencies. Acts as either a proxy of an existing builder or as a default builder for guiced components.
 * 
 * @param <T>
 */
public class GuicedBuilder<T> extends AbstractMap<String, Object> implements Builder<T> {

	private final Builder<T> wrappedBuilder;
	private final Class<? extends T> type;
	private final Injector injector;

	private final Map<String, Object> userValues = new HashMap<>();

	/*
	 * Look at adding in builder elements from ObjectBuilder. Looks like it requires build of properties and the like.
	 * 
	 */

	@SuppressWarnings("unchecked")
	public GuicedBuilder(Builder<T> wrappedBuilder, Class<?> type, Injector injector) {
		this.wrappedBuilder = wrappedBuilder;
		this.type = (Class<? extends T>) type;
		this.injector = injector;
	}

	@Override
	public T build() {
		if (wrappedBuilder != null) {
			T object = wrappedBuilder.build();
			injector.injectMembers(object);
			return object;
		}

		T instance = injector.getInstance(type);

		if (instance instanceof Node)
			((Node) instance).getProperties().putAll(userValues);

		return instance;
	}

	@Override
	public int size() {
		if (wrappedBuilder != null && wrappedBuilder instanceof Map)
			return ((Map<?, ?>) wrappedBuilder).size();

		return userValues.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Entry<String, Object>> entrySet() {
		if (wrappedBuilder != null && wrappedBuilder instanceof Map)
			return ((Map<String, Object>) wrappedBuilder).entrySet();

		return userValues.entrySet();
	}

	@Override
	public boolean isEmpty() {
		if (wrappedBuilder != null && wrappedBuilder instanceof Map)
			return ((Map<?, ?>) wrappedBuilder).isEmpty();

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsKey(Object key) {
		if (wrappedBuilder != null && wrappedBuilder instanceof Map)
			return ((Map<?, ?>) wrappedBuilder).containsKey(key);

		return userValues.containsKey(key.toString());
	}

	@Override
	public boolean containsValue(Object value) {
		if (wrappedBuilder != null && wrappedBuilder instanceof Map)
			return ((Map<?, ?>) wrappedBuilder).containsValue(value);

		return userValues.containsValue(value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object put(String key, Object value) {
		if (wrappedBuilder != null && wrappedBuilder instanceof Map)
			return ((Map<String, Object>) wrappedBuilder).put(key, value);

		return userValues.put(key, value);
	}

}
