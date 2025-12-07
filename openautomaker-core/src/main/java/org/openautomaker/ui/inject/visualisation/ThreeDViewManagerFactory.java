package org.openautomaker.ui.inject.visualisation;

import com.google.inject.assistedinject.Assisted;

import celtech.appManager.ModelContainerProject;
import celtech.coreUI.visualisation.ThreeDViewManager;
import javafx.beans.property.ReadOnlyDoubleProperty;

public interface ThreeDViewManagerFactory {

	public ThreeDViewManager create(
			ModelContainerProject project,
			@Assisted("widthProperty") ReadOnlyDoubleProperty widthProperty,
			@Assisted("heightProperty") ReadOnlyDoubleProperty heightProperty);
}
