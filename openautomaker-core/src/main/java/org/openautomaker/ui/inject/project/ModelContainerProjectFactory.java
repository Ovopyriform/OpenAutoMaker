package org.openautomaker.ui.inject.project;

import celtech.appManager.ModelContainerProject;

public interface ModelContainerProjectFactory extends ContainerProjectFactory<ModelContainerProject> {

	@Override
	public ModelContainerProject create();
}
