package org.openautomaker.ui.inject.model;

import java.util.Set;

import celtech.modelcontrol.ModelContainer;
import celtech.modelcontrol.ModelGroup;

public interface ModelGroupFactory {

	public ModelGroup create(Set<ModelContainer> modelContainers);

	public ModelGroup create(
			Set<ModelContainer> modelContainers,
			int groupModelId);

}
