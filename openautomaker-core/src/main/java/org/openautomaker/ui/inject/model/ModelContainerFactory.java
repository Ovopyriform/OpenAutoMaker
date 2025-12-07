package org.openautomaker.ui.inject.model;

import java.io.File;

import celtech.modelcontrol.ModelContainer;
import javafx.scene.shape.MeshView;

public interface ModelContainerFactory {

	public ModelContainer create();

	public ModelContainer create(
			File modelFile,
			MeshView meshView);

	public ModelContainer create(
			File modelFile,
			MeshView meshView,
			int extruderAssociation);
}
