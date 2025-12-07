package org.openautomaker.base.inject.comms;

import com.google.inject.assistedinject.Assisted;

import celtech.roboxbase.comms.DetectedDevice;
import celtech.roboxbase.comms.HardwareCommandInterface;
import celtech.roboxbase.comms.PrinterStatusConsumer;

public interface HardwareCommandInterfaceFactory {

	public HardwareCommandInterface create(
			PrinterStatusConsumer controlInterface,
			DetectedDevice printerHandle,
			@Assisted("suppressPrinterIDChecks") boolean suppressPrinterIDChecks,
			int sleepBetweenStatusChecks);
}
