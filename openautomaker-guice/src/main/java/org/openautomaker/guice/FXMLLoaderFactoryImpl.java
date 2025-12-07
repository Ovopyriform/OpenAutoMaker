package org.openautomaker.guice;

import java.net.URL;
import java.util.ResourceBundle;

import com.google.inject.Injector;
import com.google.inject.Provider;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.fxml.FXMLLoader;

@Singleton
final class FXMLLoaderFactoryImpl implements FXMLLoaderFactory {

	private final Provider<FXMLLoader> fxmlLoaderProvider;

	private final Injector injector;

	@Inject
	public FXMLLoaderFactoryImpl(
			Injector injector,
			Provider<FXMLLoader> fxmlLoaderProvider) {

		this.injector = injector;
		this.fxmlLoaderProvider = fxmlLoaderProvider;
	}

	@Override
	public FXMLLoader create() {
		FXMLLoader loader = fxmlLoaderProvider.get();

		return loader;
	}

	@Override
	public FXMLLoader create(URL location) {
		FXMLLoader loader = fxmlLoaderProvider.get();
		loader.setLocation(location);
		return loader;
	}
	@Override
	public FXMLLoader create(URL location, ResourceBundle resourceBundle) {
		FXMLLoader loader = create(location);
		loader.setResources(resourceBundle);
		return loader;
	}

	@Override
	public FXMLLoader create(URL location, Class<?> controllerClass) {
		FXMLLoader loader = create(location);
		loader.setController(injector.getInstance(controllerClass));
		return loader;
	}

	@Override
	public FXMLLoader create(URL location, Class<?> controllerClass, ResourceBundle resourceBundle) {
		FXMLLoader loader = create(location, controllerClass);
		loader.setResources(resourceBundle);
		return loader;
	}

}
