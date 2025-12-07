package org.openautomaker.ui.component.arrow_tag;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openautomaker.test_library.GuiceExtension;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//TODO: Write this test.  Is the ArrowTag class even used any more?
@ExtendWith({ GuiceExtension.class, ApplicationExtension.class })
public class ArrowTagTest {

	private ArrowTag arrowTag;

	@Start
	public void start(Stage stage) {
		arrowTag = new ArrowTag();

		stage.setScene(new Scene(new StackPane(arrowTag), 500, 500, Color.DARKGRAY));
		stage.setMaximized(true);

		stage.show();
	}

	@Test
	void testComponentLoad() throws InterruptedException {
		Thread.sleep(2000);
	}

}
