package org.openautomaker.ui.component.layout_status_menu_strip;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openautomaker.test_library.GuiceExtension;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@ExtendWith({ GuiceExtension.class, ApplicationExtension.class })
public class LayoutStatusMenuStripTest {

	private StackPane stackPane = null;

	@Start
	void start(Stage stage) {
		// TODO: Need to do a lot of stuff before this works...
		LayoutStatusMenuStrip layoutStatusMenuStrip = new LayoutStatusMenuStrip();

		stackPane = new StackPane(layoutStatusMenuStrip);

		stage.setScene(new Scene(stackPane, 500, 300, Color.DARKGRAY));
		stage.setMaximized(true);

		stage.show();
	}

	@Test
	void componentLoad_test(FxRobot robot) throws Exception {

	}
}
