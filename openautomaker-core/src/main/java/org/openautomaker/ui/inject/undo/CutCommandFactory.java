package org.openautomaker.ui.inject.undo;

import java.util.Set;

import celtech.appManager.ModelContainerProject;
import celtech.appManager.undo.CutCommand;
import celtech.modelcontrol.ModelContainer;

public interface CutCommandFactory {

	public CutCommand create(
			ModelContainerProject project,
			Set<ModelContainer> modelContainers,
			float cutHeightValue);

}
