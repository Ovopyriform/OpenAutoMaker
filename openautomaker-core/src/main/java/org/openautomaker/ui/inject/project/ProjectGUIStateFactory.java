package org.openautomaker.ui.inject.project;

import celtech.appManager.Project;
import celtech.coreUI.ProjectGUIState;

public interface ProjectGUIStateFactory {

	public ProjectGUIState create(Project project);

}
