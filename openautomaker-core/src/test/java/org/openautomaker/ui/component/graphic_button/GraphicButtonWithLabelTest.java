package org.openautomaker.ui.component.graphic_button;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openautomaker.ui.component.graphic_button.GraphicButtonTest.ABOUT_BUTTON;
import static org.openautomaker.ui.component.graphic_button.GraphicButtonTest.PREFERENCES_BUTTON;
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
public class GraphicButtonWithLabelTest {

	protected static final String ABOUT_LABEL = "about label";
	protected static final String NEW_ABOUT_LABEL = "new about label";

	private GraphicButtonWithLabel graphicButtonWithLabel = null;

	@Start
	void start(Stage stage) {
		graphicButtonWithLabel = new GraphicButtonWithLabel(ABOUT_BUTTON, ABOUT_LABEL);

		stage.setScene(new Scene(new StackPane(graphicButtonWithLabel), 300, 300));
		stage.setMaximized(true);

		stage.show();
	}
	
	@Test
	void componentLoad_test(FxRobot robot) throws Exception {
		assertThat(robot.lookup(".graphic-button-label").queryAs(Label.class)).hasText(ABOUT_LABEL);
	}

	@Test
	void getSetFxmlFileName_test(FxRobot robot) throws Exception {
		assertEquals(ABOUT_BUTTON, graphicButtonWithLabel.getFxmlFileName());
		assertEquals(ABOUT_BUTTON, graphicButtonWithLabel.getFxmlFileNameProperty().get());

		FXUtils.runAndWait(() -> {
			graphicButtonWithLabel.setFxmlFileName(PREFERENCES_BUTTON);
		});

		assertEquals(PREFERENCES_BUTTON, graphicButtonWithLabel.getFxmlFileName());
		assertEquals(PREFERENCES_BUTTON, graphicButtonWithLabel.getFxmlFileNameProperty().get());
	}

	@Test
	void getSetLabelText(FxRobot robot) throws Exception {
		assertEquals(ABOUT_LABEL, graphicButtonWithLabel.getLabelText());

		FXUtils.runAndWait(() -> {
			graphicButtonWithLabel.setLabelText(NEW_ABOUT_LABEL);
		});

		assertEquals(NEW_ABOUT_LABEL, graphicButtonWithLabel.getLabelText());
		assertThat(robot.lookup(".graphic-button-label").queryAs(Label.class)).hasText(NEW_ABOUT_LABEL);

		FXUtils.runAndWait(() -> {
			graphicButtonWithLabel.setLabelText(ABOUT_LABEL);
		});
	}

}
