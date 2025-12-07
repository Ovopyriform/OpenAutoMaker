package org.openautomaker.base.inject.printer_control.model;

import org.openautomaker.base.configuration.fileRepresentation.HeadFile;
import org.openautomaker.base.printerControl.model.Head;

import com.google.inject.assistedinject.Assisted;

import celtech.roboxbase.comms.rx.HeadEEPROMDataResponse;

public interface HeadFactory {

	public Head create();

	public Head create(@Assisted HeadFile headData);

	public Head create(@Assisted HeadEEPROMDataResponse headResponse);
}
