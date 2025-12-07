package org.openautomaker.base.inject.comms;

import com.google.inject.assistedinject.Assisted;

import celtech.roboxbase.comms.DetectedDevice;
import celtech.roboxbase.comms.PrinterStatusConsumer;
import celtech.roboxbase.comms.VirtualPrinterCommandInterface;

public interface VirtualPrinterCommandInterfaceFactory {

	public VirtualPrinterCommandInterface create(
			PrinterStatusConsumer controlInterface,
			DetectedDevice printerHandle,
			boolean suppressPrinterIDChecks,
			int sleepBetweenStatusChecks,
			@Assisted("printerName") String printerName,
			@Assisted("printerModel") String printerModel);

	public VirtualPrinterCommandInterface create(
			PrinterStatusConsumer controlInterface,
			DetectedDevice printerHandle,
			boolean suppressPrinterIDChecks,
			int sleepBetweenStatusChecks);

	public VirtualPrinterCommandInterface create(
			PrinterStatusConsumer controlInterface,
			DetectedDevice printerHandle,
			boolean suppressPrinterIDChecks);
}
