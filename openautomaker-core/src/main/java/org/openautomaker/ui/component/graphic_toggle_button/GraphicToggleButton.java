package org.openautomaker.ui.component.graphic_toggle_button;

import java.io.IOException;

import org.openautomaker.guice.FXMLLoaderFactory;
import org.openautomaker.guice.GuiceContext;

import jakarta.inject.Inject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleButton;

public class GraphicToggleButton extends ToggleButton {

	private final StringProperty fxmlFileNameProp = new SimpleStringProperty("");

	@Inject
	private FXMLLoaderFactory fxmlLoaderFactory;

	public GraphicToggleButton() {
		GuiceContext.get().injectMembers(this);
		getStyleClass().add("graphic-button");
		loadFXML();
	}

	public GraphicToggleButton(String fxmlFileName) {
		GuiceContext.get().injectMembers(this);
		fxmlFileNameProp.set(fxmlFileName);
		getStyleClass().add("graphic-button");
		loadFXML();
	}

	public String getFxmlFileName() {
		return fxmlFileNameProp.get();
	}

	public void setFxmlFileName(String fxmlFileName) {
		fxmlFileNameProp.set(fxmlFileName);

		loadFXML();
	}

	public StringProperty getFxmlFileNameProperty() {
		return fxmlFileNameProp;
	}

	private void loadFXML() throws RuntimeException {
		String fxmlFileName = fxmlFileNameProp.get();
		if ("".equals(fxmlFileName))
			return;

		FXMLLoader fxmlLoader = fxmlLoaderFactory.create(getClass().getResource(fxmlFileName + ".fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		}
		catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
