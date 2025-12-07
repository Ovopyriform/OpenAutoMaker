package org.openautomaker.ui.component.graphic_toggle_button;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openautomaker.test_library.GuiceExtension;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.framework.junit5.utils.FXUtils;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@ExtendWith({ GuiceExtension.class, ApplicationExtension.class })
public class GraphicToggleButtonTest {

	protected static final String HEAD_FAN_BUTTON = "headFanButton";
	protected static final String SNAP_TO_GROUND_BUTTON = "snapToGroundButton";

	private VBox vBox = null;

	@Start
	void start(Stage stage) {
		vBox = new VBox();

		stage.setScene(new Scene(vBox, 300, 300));
		stage.setMaximized(true);

		stage.show();
	}

	@Test
	void componentLoad_test(FxRobot robot) throws Exception {
		GraphicToggleButton headFanButton = new GraphicToggleButton(HEAD_FAN_BUTTON);
		GraphicToggleButton snapToGridButton = new GraphicToggleButton(SNAP_TO_GROUND_BUTTON);

		FXUtils.runAndWait(() -> {
			vBox.getChildren().add(headFanButton);
			vBox.getChildren().add(snapToGridButton);
		});

		// Check both buttons are displayed.
		assertEquals(2, robot.lookup(".graphic-button").queryAll().size());
	}

	@Test
	void getSetFxmlFileName_test(FxRobot robot) throws Exception {
		GraphicToggleButton graphicToggleButton = new GraphicToggleButton();
		graphicToggleButton.setFxmlFileName(HEAD_FAN_BUTTON);

		FXUtils.runAndWait(() -> {
			vBox.getChildren().add(graphicToggleButton);
		});

		assertEquals(HEAD_FAN_BUTTON, graphicToggleButton.getFxmlFileName());
		assertEquals(HEAD_FAN_BUTTON, graphicToggleButton.getFxmlFileNameProperty().get());

		FXUtils.runAndWait(() -> {
			graphicToggleButton.setFxmlFileName(SNAP_TO_GROUND_BUTTON);
		});

		assertEquals(SNAP_TO_GROUND_BUTTON, graphicToggleButton.getFxmlFileName());
		assertEquals(SNAP_TO_GROUND_BUTTON, graphicToggleButton.getFxmlFileNameProperty().get());
	}
}
