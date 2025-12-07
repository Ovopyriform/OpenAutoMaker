
package celtech.coreUI.components;

import java.io.IOException;

import org.openautomaker.guice.FXMLLoaderFactory;
import org.openautomaker.guice.GuiceContext;

import jakarta.inject.Inject;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleButton;

public class InsetPanelMenuItem extends ToggleButton {

	@Inject
	public FXMLLoaderFactory fxmlLoaderFactory;

	public InsetPanelMenuItem() {

		GuiceContext.get().injectMembers(this);

		FXMLLoader fxmlLoader = fxmlLoaderFactory.create(getClass().getResource("/celtech/resources/fxml/components/insetPanelMenuItem.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		fxmlLoader.setClassLoader(this.getClass().getClassLoader());

		try {
			fxmlLoader.load();
		}
		catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		getStyleClass().add("inset-panel-menu-item");
	}

	public void setTitle(String title) {
		setText(title);
	}
}
