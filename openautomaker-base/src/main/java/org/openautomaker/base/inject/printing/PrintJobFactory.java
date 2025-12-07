package org.openautomaker.base.inject.printing;

import java.nio.file.Path;

import org.openautomaker.base.printerControl.PrintJob;

public interface PrintJobFactory {

	public PrintJob create(String jobUUID);

	public PrintJob create(String jobUUID, Path printJobDirectory);
}
