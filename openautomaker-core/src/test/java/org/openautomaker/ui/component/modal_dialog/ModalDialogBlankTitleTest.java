package org.openautomaker.ui.component.modal_dialog;

import static org.openautomaker.ui.component.modal_dialog.ModalDialogTest.NEW_TEST_TITLE;
import static org.openautomaker.ui.component.modal_dialog.ModalDialogTest.TEST_TITLE;
import static org.testfx.assertions.api.Assertions.assertThat;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openautomaker.test_library.GuiceExtension;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.framework.junit5.utils.FXUtils;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

@ExtendWith({ GuiceExtension.class, ApplicationExtension.class })
public class ModalDialogBlankTitleTest {

	private ModalDialog modalDialog;

	@Start
	void start(Stage stage) {
		modalDialog = new ModalDialog(null);
		modalDialog.setMessage("Test Message");

		stage.setScene(new Scene(modalDialog));
		stage.setMaximized(true);

		stage.show();

		FXUtils.keepJavaFxAlive();

		// Stop the modal blocking the test thread.
		Platform.runLater(() -> {
			modalDialog.show(); //This will block the thread unless 'runLater'
		});

		waitForFxEvents();
	}

	/**
	 * @param robot - Will be injected by the test runner.
	 * @throws Exception
	 */
	@Test
	void setTitle_test(FxRobot robot) throws Exception {
		FXUtils.runAndWait(() -> {
			modalDialog.setTitle(TEST_TITLE);
		});

		assertThat(robot.lookup("#dialogTitle").queryAs(Label.class)).hasText(TEST_TITLE);

		FXUtils.runAndWait(() -> {
			modalDialog.setTitle(NEW_TEST_TITLE);
		});

		assertThat(robot.lookup("#dialogTitle").queryAs(Label.class)).hasText(NEW_TEST_TITLE);
	}
}
