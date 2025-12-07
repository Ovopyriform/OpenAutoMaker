package org.openautomaker.guice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.google.inject.Provider;

import jakarta.inject.Inject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

//TODO: I don't think this is useful.  Only looks useful.
public class FXMLGuicer {

	private final Provider<FXMLLoader> fxmlLoaderProvider;

	@Inject
	protected FXMLGuicer(Provider<FXMLLoader> fxmlLoaderProvider) {
		this.fxmlLoaderProvider = fxmlLoaderProvider;
	}

	public URL getNestedFXMLOfComponent(Node component) {
		Class<? extends Node> componentClass = component.getClass();
		String className = componentClass.getName().replace('.', '/');
		String baseName = className + ".fxml";
		return componentClass.getClassLoader().getResource(baseName);
	}

	public void loadNestedFXMLOfComponent(Node component) {
		FXMLLoader loader = fxmlLoaderProvider.get();
		URL xmlLocation = getNestedFXMLOfComponent(component);
		if (xmlLocation == null) {
			return;
		}
		loadFXMLForComponent(xmlLocation, component, loader);
	}

	private Optional<ResourceBundle> getNestedResourceBundleOfComponent(Node component) {
		try {
			return Optional.of(ResourceBundle.getBundle(component.getClass().getName()));
		}
		catch (MissingResourceException ex) {
			return Optional.empty();
		}
	}

	private void loadFXMLForComponent(URL xmlLocation, Node component, FXMLLoader loader) {
		loader.setLocation(xmlLocation);
		loader.setRoot(component);
		loader.setController(component);
		getNestedResourceBundleOfComponent(component)
				.ifPresent(loader::setResources);
		try {
			InputStream preloadedXML = preloadAndFilterController(xmlLocation);
			loader.load(preloadedXML);
		}
		catch (IOException ex) {
			throw new UncheckedIOException("fxml load error:", ex);
		}
	}

	private InputStream preloadAndFilterController(URL xmlLocation) throws IOException {
		try (InputStream inputStream = xmlLocation.openStream()) {
			String xmlString = convertToString(inputStream);
			String xmlFilteredString = xmlString.replaceAll("fx:controller.*=.*\".*?\"", "");
			return new ByteArrayInputStream(xmlFilteredString.getBytes(StandardCharsets.UTF_8));
		}
	}

	private String convertToString(InputStream inputStream) throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}
		return result.toString(StandardCharsets.UTF_8.name());
	}
}
