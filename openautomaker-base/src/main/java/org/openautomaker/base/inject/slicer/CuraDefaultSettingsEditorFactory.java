package org.openautomaker.base.inject.slicer;

import org.openautomaker.base.utils.cura.CuraDefaultSettingsEditor;
import org.openautomaker.environment.Slicer;

public interface CuraDefaultSettingsEditorFactory {

	public CuraDefaultSettingsEditor create(boolean singleNozzleHead, Slicer slicerType);
}
