package org.openautomaker.ui.component.modal_dialog;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

@ExtendWith({ GuiceExtension.class, ApplicationExtension.class })
public class ModalDialogTest {

	protected static final String TEST_TITLE = "Test Title";
	protected static final String NEW_TEST_TITLE = "New Test Title";

	protected static final String TEST_MESSAGE = "This is a long, long, long, long, long, long, test message for the ModalDialog";
	protected static final String NEW_TEST_MESSAGE = "This is a new test message for the ModalDialog";

	protected static final String TEST_BUTTON_LABEL_0 = "Test button label 0";
	protected static final String TEST_BUTTON_LABEL_1 = "Test button label 1";

	private ModalDialog modalDialog;

	@Start
	void start(Stage stage) {
		modalDialog = new ModalDialog(TEST_TITLE);

		Scene scene = new Scene(modalDialog);

		stage.setScene(scene);
		stage.setMaximized(true);
		//stage.setFullScreen(true);
		stage.show(); //This blocks the current thread

		FXUtils.keepJavaFxAlive();

		// Stop the modal blocking the test thread.
		Platform.runLater(() -> {
			modalDialog.show();
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

	/**
	 * @param robot - Will be injected by the test runner.
	 * @throws Exception 
	 */
	@Test
	void setMessage_test(FxRobot robot) throws Exception {
		FXUtils.runAndWait(() -> {
			modalDialog.setMessage(TEST_MESSAGE);
		});

		assertThat(robot.lookup("#dialogMessage").queryAs(Label.class)).hasText(TEST_MESSAGE);

		FXUtils.runAndWait(() -> {
			modalDialog.setMessage(NEW_TEST_MESSAGE);
		});

		assertThat(robot.lookup("#dialogMessage").queryAs(Label.class)).hasText(NEW_TEST_MESSAGE);

	}

	@Test
	void addButton_test(FxRobot robot) throws Exception {
		FXUtils.runAndWait(() -> {
			int btnIdx = modalDialog.addButton(TEST_BUTTON_LABEL_0);
			assertEquals(0, btnIdx);
			btnIdx = modalDialog.addButton(TEST_BUTTON_LABEL_1);
			assertEquals(1, btnIdx);
		});

		assertThat(robot.lookup(".button").queryButton()).hasText(TEST_BUTTON_LABEL_0);
	}

	@Test
	void addButtonWithDisabler_test(FxRobot robot) throws Exception {
		FXUtils.runAndWait(() -> {
			modalDialog.addButton(TEST_BUTTON_LABEL_0, new SimpleBooleanProperty(true));
		});

		assertThat(robot.lookup(".button").queryButton()).isDisabled();
	}
}
