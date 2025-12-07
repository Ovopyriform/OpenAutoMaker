package org.openautomaker.base.inject.printer_control;

import org.openautomaker.base.printerControl.model.Printer;
import org.openautomaker.base.printerControl.model.statetransitions.calibration.CalibrationPrinterErrorHandler;
import org.openautomaker.base.task_executor.Cancellable;

public interface CalibrationPrinterErrorHandlerFactory {

	public CalibrationPrinterErrorHandler create(
			Printer printer,
			Cancellable errorCancellable);
}
