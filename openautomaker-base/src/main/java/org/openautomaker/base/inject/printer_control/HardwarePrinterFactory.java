package org.openautomaker.base.inject.printer_control;

import org.openautomaker.base.printerControl.model.HardwarePrinter;
import org.openautomaker.base.printerControl.model.HardwarePrinter.FilamentLoadedGetter;

import celtech.roboxbase.comms.CommandInterface;
import celtech.roboxbase.comms.PrinterStatusConsumer;

public interface HardwarePrinterFactory {

	public HardwarePrinter create(
			PrinterStatusConsumer printerStatusConsumer,
			CommandInterface commandInterface);

	public HardwarePrinter create(
			PrinterStatusConsumer printerStatusConsumer,
			CommandInterface commandInterface,
			FilamentLoadedGetter filamentLoadedGetter,
			boolean doNotCheckForPresenceOfHead);
}
