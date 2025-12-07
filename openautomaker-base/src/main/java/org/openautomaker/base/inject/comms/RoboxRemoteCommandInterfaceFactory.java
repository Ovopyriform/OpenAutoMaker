package org.openautomaker.base.inject.comms;

import com.google.inject.assistedinject.Assisted;

import celtech.roboxbase.comms.PrinterStatusConsumer;
import celtech.roboxbase.comms.RemoteDetectedPrinter;
import celtech.roboxbase.comms.remote.RoboxRemoteCommandInterface;

public interface RoboxRemoteCommandInterfaceFactory {

	public RoboxRemoteCommandInterface create(
			@Assisted PrinterStatusConsumer controlInterface,
			@Assisted RemoteDetectedPrinter printerHandle,
			@Assisted boolean suppressPrinterIDChecks,
			@Assisted int sleepBetweenStatusChecks);
}
