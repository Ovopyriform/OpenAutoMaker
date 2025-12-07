package org.openautomaker.base.inject.utils.models;

import java.util.List;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.openautomaker.base.configuration.RoboxProfile;
import org.openautomaker.base.configuration.fileRepresentation.PrinterSettingsOverrides;
import org.openautomaker.base.services.camera.CameraTriggerData;
import org.openautomaker.base.services.slicer.PrintQualityEnumeration;
import org.openautomaker.base.utils.models.MeshForProcessing;
import org.openautomaker.base.utils.models.PrintableMeshes;
import org.openautomaker.environment.Slicer;

import com.google.inject.assistedinject.Assisted;

public interface PrintableMeshesFactory {

	public PrintableMeshes create(
			List<MeshForProcessing> meshesForProcessing,
			List<Boolean> usedExtruders,
			List<Integer> extruderForModel,
			@Assisted("projectName") String projectName,
			@Assisted("requiredPrintJobID") String requiredPrintJobID,
			RoboxProfile settings,
			PrinterSettingsOverrides printOverrides,
			PrintQualityEnumeration printQuality,
			Slicer defaultSlicerType,
			Vector3D centreOfPrintedObject,
			@Assisted("safetyFeaturesRequired") boolean safetyFeaturesRequired,
			@Assisted("cameraEnabled") boolean cameraEnabled,
			CameraTriggerData cameraTriggerData);
}
