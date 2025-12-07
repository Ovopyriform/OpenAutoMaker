package org.openautomaker.mock.printer_control.model;

import org.openautomaker.base.configuration.fileRepresentation.HeadFile;

public interface MockHeadFactory {

	public MockHead create(HeadFile headFile);
}
