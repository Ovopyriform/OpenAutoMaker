package org.openautomaker.ui.inject.project;

import celtech.appManager.Project;
import celtech.coreUI.visualisation.ProjectSelection;

public interface ProjectSelectionFactory {

	public ProjectSelection create(Project project);

}
