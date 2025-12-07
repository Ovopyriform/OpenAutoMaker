package org.openautomaker.base.inject.configuration.profile_settings;

import org.openautomaker.base.configuration.profilesettings.PrintProfileSettings;

public interface PrintProfileSettingsFactory {

	public PrintProfileSettings create(PrintProfileSettings settingsToCopy);
}
