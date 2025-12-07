package org.openautomaker.ui.component.layout_status_menu_strip;

import java.io.IOException;

import org.openautomaker.guice.FXMLLoaderFactory;
import org.openautomaker.guice.GuiceContext;

import jakarta.inject.Inject;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class LayoutStatusMenuStrip extends VBox {

	private LayoutStatusMenuStripController controller = null;

	@Inject
	private FXMLLoaderFactory fxmlLoaderFactory;

	public LayoutStatusMenuStrip() {
		super();
		GuiceContext.get().injectMembers(this);
		init();
	}

	private void init() {
		FXMLLoader fxmlLoader = fxmlLoaderFactory.create(getClass().getResource("LayoutStatusMenuStrip.fxml"));
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
