package org.openautomaker.ui.inject.project;

import celtech.appManager.Project;

public interface ContainerProjectFactory<T extends Project> {

	public T create();

}
