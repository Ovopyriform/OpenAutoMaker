package org.openautomaker.base.inject.slicer;

import java.nio.file.Path;

import org.openautomaker.base.printerControl.model.Printer;
import org.openautomaker.base.services.slicer.ProgressReceiver;
import org.openautomaker.base.services.slicer.SlicerTask;
import org.openautomaker.base.utils.models.PrintableMeshes;

public interface SlicerTaskFactory {

	public SlicerTask create(
			String printJobUUID,
			PrintableMeshes printableMeshes,
			Path printJobDirectory,
			Printer printerToUse,
			ProgressReceiver progressReceiver);
}
