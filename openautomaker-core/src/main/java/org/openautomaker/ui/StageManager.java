package org.openautomaker.ui;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.stage.Stage;

/**
 * Simple object to allow management of the Main Stage
 */
@Singleton
public class StageManager {

	private Stage mainStage = null;

	@Inject
	protected StageManager() {

	}

	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}
}
