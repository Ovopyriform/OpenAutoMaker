package org.openautomaker.ui.inject.undo;

import celtech.appManager.Project;
import celtech.appManager.undo.UndoableProject;

public interface UndoableProjectFactory {

	public UndoableProject create(Project project);

}
