package org.openautomaker.ui.inject.model_loader;

import java.io.File;
import java.util.List;

import celtech.services.modelLoader.ModelLoaderTask;

public interface ModelLoaderTaskFactory {

	public ModelLoaderTask create(List<File> modelFilesToLoad);
}
