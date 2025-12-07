package org.openautomaker.ui.inject.project;

import celtech.appManager.ShapeContainerProject;

public interface ShapeContainerProjectFactory extends ContainerProjectFactory<ShapeContainerProject> {

	@Override
	public ShapeContainerProject create();

}
