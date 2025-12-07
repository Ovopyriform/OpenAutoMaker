package org.openautomaker.base.inject.camera_control;

import org.openautomaker.base.printerControl.model.Printer;
import org.openautomaker.base.services.camera.CameraTriggerManager;

public interface CameraTriggerManagerFactory {

	public CameraTriggerManager create(Printer printer);
}
