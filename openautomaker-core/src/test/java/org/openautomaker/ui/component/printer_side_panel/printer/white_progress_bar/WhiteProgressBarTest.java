package org.openautomaker.ui.component.printer_side_panel.printer.white_progress_bar;

import static javafx.scene.paint.Color.BLACK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.assertions.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openautomaker.test_library.GuiceExtension;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.framework.junit5.utils.FXUtils;

import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

@ExtendWith({ GuiceExtension.class, ApplicationExtension.class })
public class WhiteProgressBarTest {

	public static final String SOLID_BAR_ID = "#solidBar";
	public static final String CLEAR_BAR_ID = "#clearBar";

	WhiteProgressBar whiteProgressBar;

	@Start
	void start(Stage stage) {
		whiteProgressBar = new WhiteProgressBar();

		stage.setScene(new Scene(new StackPane(whiteProgressBar), 500, 300, BLACK));
		stage.setMaximized(true);

		stage.show();
	}

	@Test
	void componentLoad_test(FxRobot robot) throws InterruptedException {
		assertThat(robot.lookup(SOLID_BAR_ID).queryAs(Polygon.class)).isVisible();
		assertThat(robot.lookup(CLEAR_BAR_ID).queryAs(Polygon.class)).isVisible();
	}

	@Test
	void setControlWidthHeight_test(FxRobot robot) throws Exception {
		FXUtils.runAndWait(() -> {
			whiteProgressBar.setControlWidth(450);
			whiteProgressBar.setControlHeight(50);
		});

		Bounds solidBounds = robot.lookup(SOLID_BAR_ID).queryAs(Polygon.class).getBoundsInLocal();
		Bounds clearBounds = robot.lookup(CLEAR_BAR_ID).queryAs(Polygon.class).getBoundsInLocal();

		assertEquals(450, solidBounds.getWidth() + clearBounds.getWidth());
		assertEquals(50, solidBounds.getHeight());
		assertEquals(50, clearBounds.getHeight());
	}

	@Test
	void setProgress_test(FxRobot robot) throws Exception {
		FXUtils.runAndWait(() -> {
			whiteProgressBar.setControlWidth(400);
			whiteProgressBar.setProgress(0.5);
		});

		Bounds solidBounds = robot.lookup("#solidBar").queryAs(Polygon.class).getBoundsInLocal();
		assertEquals(200, solidBounds.getWidth());

		FXUtils.runAndWait(() -> {
			whiteProgressBar.setProgress(0.25);
		});

		solidBounds = robot.lookup("#solidBar").queryAs(Polygon.class).getBoundsInLocal();
		assertEquals(100, solidBounds.getWidth());

		FXUtils.runAndWait(() -> {
			whiteProgressBar.setProgress(0.75);
		});

		solidBounds = robot.lookup("#solidBar").queryAs(Polygon.class).getBoundsInLocal();
		assertEquals(300, solidBounds.getWidth());

	}
}
