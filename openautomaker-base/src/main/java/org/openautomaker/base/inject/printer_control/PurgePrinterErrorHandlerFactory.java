package org.openautomaker.base.inject.printer_control;

import org.openautomaker.base.printerControl.model.Printer;
import org.openautomaker.base.printerControl.model.statetransitions.purge.PurgePrinterErrorHandler;
import org.openautomaker.base.task_executor.Cancellable;

public interface PurgePrinterErrorHandlerFactory {

	public PurgePrinterErrorHandler create(
			Printer printer,
			Cancellable errorCancellable);
}
