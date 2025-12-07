package org.openautomaker.ui.inject.utils.settings_generation;

import org.openautomaker.base.configuration.profilesettings.PrintProfileSettings;

import celtech.utils.settingsgeneration.ProfileDetailsGenerator;
import javafx.beans.value.WritableBooleanValue;

public interface ProfileDetailsGeneratorFactory {

	public ProfileDetailsGenerator create(PrintProfileSettings printProfileSettings, WritableBooleanValue isDirty);
}
