package org.openautomaker.ui.component.gcode_panel;

import java.io.IOException;

import org.openautomaker.guice.FXMLLoaderFactory;
import org.openautomaker.guice.GuiceContext;

import jakarta.inject.Inject;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class GCodePanel extends VBox {

	private GCodePanelController controller = null;

	@Inject
	private FXMLLoaderFactory fxmlLoaderFactory;

	public GCodePanel() {
		super();
		GuiceContext.get().injectMembers(this);
		init();
	}

	private void init() {
		FXMLLoader fxmlLoader = fxmlLoaderFactory.create(getClass().getResource("GCodePanel.fxml"));
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
			controller = fxmlLoader.getController();
		}
		catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public GCodePanelController getController() {
		return controller;
	}
}
