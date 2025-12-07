package org.openautomaker.ui.component.printer_side_panel.printer.svg;

import static javafx.scene.paint.Color.BLACK;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.assertions.api.Assertions.assertThat;
import static org.testfx.assertions.impl.Adapter.fromMatcher;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openautomaker.test_library.GuiceExtension;
import org.openautomaker.ui.component.printer_side_panel.printer.PrinterComponent.Size;
import org.openautomaker.ui.component.printer_side_panel.printer.PrinterComponent.Status;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.framework.junit5.utils.FXUtils;
import org.testfx.matcher.base.NodeMatchers;

import javafx.geometry.BoundingBox;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

@ExtendWith({ GuiceExtension.class, ApplicationExtension.class })
public class PrinterSVGTest {

	private static final String ROOT_INDICATOR_ID = "#rootIndicator";

	private static final String RBX01_PRINTER_ICON_ID = "#RBX01PrinterIcon";
	private static final String RBX10_PRINTER_ICON_ID = "#RBX10PrinterIcon";

	private static final String RBX01 = "RBX01";
	private static final String RBX10 = "RBX10";

	public static final String READY_ICON_ID = "#readyIcon";
	public static final String PRINTING_ICON_ID = "#printingIcon";
	public static final String PAUSED_ICON_ID = "#pausedIcon";
	public static final String NOTIFICATION_ICON_ID = "#notificationIcon";
	public static final String ERROR_ICON_ID = "#errorIcon";

	public static final Map<Status, String> EXPECTED_STATUS_ICON_ID = Map.of(
			Status.READY, READY_ICON_ID,
			Status.PAUSED, PAUSED_ICON_ID,
			Status.PRINTING, PRINTING_ICON_ID,
			Status.NOTIFICATION, NOTIFICATION_ICON_ID,
			Status.NO_INDICATOR, "");

	PrinterSVG printerSVG;

	@Start
	void start(Stage stage) {
		printerSVG = new PrinterSVG();

		stage.setScene(new Scene(new StackPane(printerSVG), 500, 500, BLACK));
		stage.setMaximized(true);

		stage.show();
	}

	@Test
	void setPrinterIcon_test(FxRobot robot) throws Exception {
		FXUtils.runAndWait(() -> {
			printerSVG.setPrinterIcon(RBX10);
		});

		assertThat(robot.lookup(RBX10_PRINTER_ICON_ID).queryAs(Pane.class)).isVisible();
		assertThat(robot.lookup(RBX01_PRINTER_ICON_ID).queryAs(Pane.class)).isInvisible();

		FXUtils.runAndWait(() -> {
			printerSVG.setPrinterIcon(RBX01);
		});

		assertThat(robot.lookup(RBX01_PRINTER_ICON_ID).queryAs(Pane.class)).isVisible();
		assertThat(robot.lookup(RBX10_PRINTER_ICON_ID).queryAs(Pane.class)).isInvisible();
	}

	@Test
	void setStatus_test(FxRobot robot) throws Exception {
		FXUtils.runAndWait(() -> {
			printerSVG.setPrinterIcon(RBX10);
		});

		List.of(Status.values()).forEach((status) -> {
			try {
				FXUtils.runAndWait(() -> {
					printerSVG.setStatus(status);
				});

				String visibleStatusId = EXPECTED_STATUS_ICON_ID.get(status);

				List.of(Status.values()).forEach((checkStatus) -> {
					String checkStatusId = EXPECTED_STATUS_ICON_ID.get(checkStatus);

					if ("".equals(checkStatusId))
						return;

					assertThat(robot.lookup(checkStatusId).queryAs(Pane.class)).is(fromMatcher(visibleStatusId == checkStatusId ? NodeMatchers.isVisible() : NodeMatchers.isInvisible()));
				});
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	void showErrorIndicator_test(FxRobot robot) throws Exception {
		FXUtils.runAndWait(() -> {
			printerSVG.setPrinterIcon(RBX10);
		});

		assertThat(robot.lookup(ERROR_ICON_ID).queryAs(Pane.class)).isInvisible();

		FXUtils.runAndWait(() -> {
			printerSVG.showErrorIndicator(true);
		});

		assertThat(robot.lookup(ERROR_ICON_ID).queryAs(Pane.class)).isVisible();
	}

	@Test
	void setSize_test(FxRobot robot) throws Exception {
		FXUtils.runAndWait(() -> {
			printerSVG.setPrinterIcon(RBX10);
		});

		List.of(Size.values()).forEach((size) -> {
			try {
				FXUtils.runAndWait(() -> {
					printerSVG.setSize(size);
				});

				double expectedScale = size.getSize() / 260d;

				// This checks the applied scale is equivalent to the expected scale of the element
				Node icon = robot.lookup(RBX10_PRINTER_ICON_ID).queryAs(Pane.class).getParent();
				assertTrue(icon.getLocalToParentTransform().similarTo(
						new Scale(expectedScale, expectedScale, 0, 0),
						new BoundingBox(0, 0, 260, 260),
						0));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	void setIsRoot_test(FxRobot robot) throws Exception {
		FXUtils.runAndWait(() -> {
			printerSVG.setPrinterIcon(RBX10);
		});

		assertThat(robot.lookup(ROOT_INDICATOR_ID).queryAs(Pane.class)).isInvisible();

		FXUtils.runAndWait(() -> {
			printerSVG.setIsRoot(true);
		});

		assertThat(robot.lookup(ROOT_INDICATOR_ID).queryAs(Pane.class)).isVisible();
	}
}
