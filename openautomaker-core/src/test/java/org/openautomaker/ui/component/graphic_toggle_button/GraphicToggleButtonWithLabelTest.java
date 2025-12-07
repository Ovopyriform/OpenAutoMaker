package org.openautomaker.ui.component.graphic_toggle_button;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openautomaker.ui.component.graphic_toggle_button.GraphicToggleButtonTest.HEAD_FAN_BUTTON;
import static org.openautomaker.ui.component.graphic_toggle_button.GraphicToggleButtonTest.SNAP_TO_GROUND_BUTTON;
import static org.testfx.assertions.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openautomaker.test_library.GuiceExtension;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.framework.junit5.utils.FXUtils;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@ExtendWith({ GuiceExtension.class, ApplicationExtension.class })
public class GraphicToggleButtonWithLabelTest {

	protected static final String HEAD_FAN_LABEL = "head fan label";
	protected static final String NEW_HEAD_FAN_LABEL = "new head fan label";

	private GraphicToggleButtonWithLabel graphicToggleButtonWithLabel = null;

	@Start
	void start(Stage stage) {
		graphicToggleButtonWithLabel = new GraphicToggleButtonWithLabel(HEAD_FAN_BUTTON, HEAD_FAN_LABEL);

		stage.setScene(new Scene(new StackPane(graphicToggleButtonWithLabel), 300, 300));
		stage.setMaximized(true);

		stage.show();
	}

	@Test
	void componentLoad_test(FxRobot robot) throws Exception {
		assertThat(robot.lookup(".graphic-button-label").queryAs(Label.class)).hasText(HEAD_FAN_LABEL);
	}

	@Test
	void getSetFxmlFileName_test(FxRobot robot) throws Exception {
		assertEquals(HEAD_FAN_BUTTON, graphicToggleButtonWithLabel.getFxmlFileName());
		assertEquals(HEAD_FAN_BUTTON, graphicToggleButtonWithLabel.getFxmlFileNameProperty().get());

		FXUtils.runAndWait(() -> {
			graphicToggleButtonWithLabel.setFxmlFileName(SNAP_TO_GROUND_BUTTON);
		});

		assertEquals(SNAP_TO_GROUND_BUTTON, graphicToggleButtonWithLabel.getFxmlFileName());
		assertEquals(SNAP_TO_GROUND_BUTTON, graphicToggleButtonWithLabel.getFxmlFileNameProperty().get());
	}

	@Test
	void getSetLabelText(FxRobot robot) throws Exception {
		assertEquals(HEAD_FAN_LABEL, graphicToggleButtonWithLabel.getLabelText());

		FXUtils.runAndWait(() -> {
			graphicToggleButtonWithLabel.setLabelText(NEW_HEAD_FAN_LABEL);
		});

		assertEquals(NEW_HEAD_FAN_LABEL, graphicToggleButtonWithLabel.getLabelText());
		assertThat(robot.lookup(".graphic-button-label").queryAs(Label.class)).hasText(NEW_HEAD_FAN_LABEL);

		FXUtils.runAndWait(() -> {
			graphicToggleButtonWithLabel.setLabelText(HEAD_FAN_LABEL);
		});
	}
}
