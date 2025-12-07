package org.openautomaker.base.inject.postprocessor;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.openautomaker.base.configuration.RoboxProfile;
import org.openautomaker.base.configuration.fileRepresentation.HeadFile;
import org.openautomaker.base.configuration.fileRepresentation.PrinterSettingsOverrides;
import org.openautomaker.base.postprocessor.nouveau.PostProcessor;
import org.openautomaker.base.postprocessor.nouveau.PostProcessorFeatureSet;
import org.openautomaker.base.printerControl.model.Printer;
import org.openautomaker.base.services.camera.CameraTriggerData;
import org.openautomaker.environment.Slicer;

import com.google.inject.assistedinject.Assisted;

import javafx.beans.property.DoubleProperty;

public interface PostProcessorFactory {

	public PostProcessor create(
			@Assisted("printJobUUID") String printJobUUID,
			@Assisted("nameOfPrint") String nameOfPrint,
			List<Boolean> usedExtruders,
			Printer printer,
			@Assisted("gcodeFileToProcess") Path gcodeFileToProcess,
			@Assisted("gcodeOutputFile") Path gcodeOutputFile,
			HeadFile headFile,
			RoboxProfile settings,
			PrinterSettingsOverrides printerOverrides,
			PostProcessorFeatureSet postProcessorFeatureSet,
			@Assisted("headType") String headType,
			DoubleProperty taskProgress,
			Map<Integer, Integer> objectToNozzleNumberMap,
			CameraTriggerData cameraTriggerData,
			boolean safetyFeaturesRequired,
			Slicer slicerType);
}
