package org.openautomaker.base.inject.postprocessor;

import org.openautomaker.base.configuration.RoboxProfile;
import org.openautomaker.base.postprocessor.nouveau.NodeManagementUtilities;
import org.openautomaker.base.postprocessor.nouveau.PostProcessorFeatureSet;
import org.openautomaker.base.postprocessor.nouveau.UtilityMethods;
import org.openautomaker.base.services.camera.CameraTriggerData;

public interface UtilityMethodsFactory {

	public UtilityMethods create(
			PostProcessorFeatureSet ppFeatureSet,
			RoboxProfile settings,
			String headType,
			NodeManagementUtilities nodeManagementUtilities,
			CameraTriggerData cameraTriggerData);
}
