package org.openautomaker.base.inject.service;

import org.openautomaker.base.printerControl.model.Printer;
import org.openautomaker.base.services.firmware.FirmwareLoadTask;

public interface FirmwareLoadTaskFactory {

	public FirmwareLoadTask create(String firmwareFileToLoad, Printer printerToUpdate);

}
