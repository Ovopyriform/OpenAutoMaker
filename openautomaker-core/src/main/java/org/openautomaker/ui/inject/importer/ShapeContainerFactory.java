package org.openautomaker.ui.inject.importer;

import java.io.File;
import java.util.List;

import celtech.utils.threed.importers.svg.ShapeContainer;
import javafx.scene.shape.Shape;

public interface ShapeContainerFactory {

	public ShapeContainer create();

	public ShapeContainer create(File modelFile);

	public ShapeContainer create(
			String name,
			List<Shape> shapes);

	public ShapeContainer create(
			String name,
			Shape shape);
}
