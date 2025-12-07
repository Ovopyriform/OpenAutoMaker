package org.openautomaker.base.inject.postprocessor;

import java.nio.file.Path;

import org.openautomaker.base.printerControl.model.Printer;
import org.openautomaker.base.services.postProcessor.PostProcessorTask;
import org.openautomaker.base.utils.models.PrintableMeshes;
import org.openautomaker.environment.Slicer;

import javafx.beans.property.DoubleProperty;

public interface PostProcessorTaskFactory {

	public PostProcessorTask create(
			String printJobUUID,
			PrintableMeshes printableMeshes,
			Path printJobDirectory,
			Printer printerToUse,
			DoubleProperty taskProgress,
			Slicer slicerType);
}
