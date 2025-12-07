package org.openautomaker.base.inject.printing;

import org.openautomaker.base.configuration.fileRepresentation.CameraSettings;
import org.openautomaker.base.postprocessor.PrintJobStatistics;
import org.openautomaker.base.printerControl.model.Printer;
import org.openautomaker.base.services.printing.TransferGCodeToPrinterTask;

import com.google.inject.assistedinject.Assisted;

import javafx.beans.property.IntegerProperty;

public interface TransferGCodeToPrinterTaskFactory {

	public TransferGCodeToPrinterTask create(
			Printer printerToUse,
			@Assisted("modelFileToPrint") String modelFileToPrint,
			@Assisted("printJobID") String printJobID,
			IntegerProperty linesInFile,
			@Assisted("printUsingSDCard") boolean printUsingSDCard,
			int startFromSequenceNumber,
			@Assisted("thisJobCanBeReprinted") boolean thisJobCanBeReprinted,
			@Assisted("dontInitiatePrint") boolean dontInitiatePrint,
			PrintJobStatistics printJobStatistics,
			CameraSettings cameraData);
}
