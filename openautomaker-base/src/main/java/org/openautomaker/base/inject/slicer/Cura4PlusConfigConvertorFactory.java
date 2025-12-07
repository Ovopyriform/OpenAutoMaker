package org.openautomaker.base.inject.slicer;

import org.openautomaker.base.configuration.slicer.Cura4PlusConfigConvertor;
import org.openautomaker.base.printerControl.model.Printer;
import org.openautomaker.base.utils.models.PrintableMeshes;
import org.openautomaker.environment.Slicer;

public interface Cura4PlusConfigConvertorFactory {

	public Cura4PlusConfigConvertor create(
			Printer printer,
			PrintableMeshes printableMeshes,
			Slicer slicerType);
}
