package org.openautomaker.ui.inject.visualisation;

import celtech.appManager.Project;
import celtech.coreUI.visualisation.DimensionLineManager;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.scene.layout.Pane;

public interface DimensionLineManagerFactory {

	public DimensionLineManager create(
			Pane paneToAddDimensionsTo,
			Project project,
			ReadOnlyBooleanProperty hideDimensionsProperty);
}
