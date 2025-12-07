package org.openautomaker.ui.component.printer_side_panel;

import java.io.IOException;

import org.openautomaker.guice.FXMLLoaderFactory;
import org.openautomaker.guice.GuiceContext;

import jakarta.inject.Inject;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class PrinterSidePanel extends VBox {

	private PrinterSidePanelController controller = null;

	@Inject
	private FXMLLoaderFactory fxmlLoaderFactory;

	public PrinterSidePanel() {
		super();
		GuiceContext.get().injectMembers(this);
		init();
	}

	private void init() {
		FXMLLoader fxmlLoader = fxmlLoaderFactory.create(getClass().getResource("PrinterSidePanel.fxml"));
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
			controller = fxmlLoader.getController();
		}
		catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
