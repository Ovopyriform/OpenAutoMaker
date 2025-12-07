package org.openautomaker.base.inject.printer_control;

import org.openautomaker.base.printerControl.model.PrintEngine;
import org.openautomaker.base.printerControl.model.Printer;

public interface PrintEngineFactory {

	public PrintEngine create(Printer associatedPrinter);
}
