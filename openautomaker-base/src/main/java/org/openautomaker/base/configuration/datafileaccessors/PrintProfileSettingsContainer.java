package org.openautomaker.base.configuration.datafileaccessors;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openautomaker.base.configuration.profilesettings.PrintProfileSetting;
import org.openautomaker.base.configuration.profilesettings.PrintProfileSettings;
import org.openautomaker.environment.I18N;
import org.openautomaker.environment.Slicer;
import org.openautomaker.environment.preference.printer.PrintProfilesPathPreference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.util.Pair;

@Singleton
public class PrintProfileSettingsContainer {
	//TODO: Revisit this class

	private static final Logger LOGGER = LogManager.getLogger();

	private static final String PRINT_PROFILE_SETTINGS_JSON = "print_profile_settings.json";

	//private static PrintProfileSettingsContainer instance;

	private static Map<Slicer, PrintProfileSettings> printProfileSettings;
	private static Map<Slicer, PrintProfileSettings> defaultPrintProfileSettings;

	private final PrintProfilesPathPreference printProfilesPathPreference;

	private final I18N i18n;

	@Inject
	protected PrintProfileSettingsContainer(
			PrintProfilesPathPreference printProfilesPathPreference,
			I18N i18n) {

		this.printProfilesPathPreference = printProfilesPathPreference;
		this.i18n = i18n;

		printProfileSettings = new HashMap<>();
		defaultPrintProfileSettings = new HashMap<>();
		loadPrintProfileSettingsFile();

		//instance = this;
	}

	//	@Deprecated
	//	public static PrintProfileSettingsContainer getInstance() {
	//		return instance;
	//	}

	public PrintProfileSettings getPrintProfileSettingsForSlicer(Slicer slicerType) {
		return printProfileSettings.get(slicerType);
	}

	public PrintProfileSettings getDefaultPrintProfileSettingsForSlicer(Slicer slicerType) {
		return defaultPrintProfileSettings.get(slicerType);
	}

	public Map<String, List<PrintProfileSetting>> compareAndGetDifferencesBetweenSettings(PrintProfileSettings originalSettings, PrintProfileSettings newSettings) {
		Map<String, List<PrintProfileSetting>> changedValuesMap = new HashMap<>();

		List<Pair<PrintProfileSetting, String>> originalSettingsList = originalSettings.getAllEditableSettingsWithSections();
		List<Pair<PrintProfileSetting, String>> newSettingsList = newSettings.getAllEditableSettingsWithSections();

		originalSettingsList.forEach(settingToSection -> {
			String sectionTitle = i18n.t(settingToSection.getValue());
			PrintProfileSetting originalSetting = settingToSection.getKey();

			// From the new settings find one with the same id and different vaue from the old setting
			Optional<PrintProfileSetting> possibleChangedSetting = newSettingsList.stream()
					.map(newSettingToSection -> {
						return newSettingToSection.getKey();
					})
					.filter(newSetting -> originalSetting.getId().equals(newSetting.getId()))
					.filter(newSetting -> !originalSetting.getValue().equals(newSetting.getValue()))
					.findFirst();

			// If we have a changed value, add the setting to the map in the correct section
			if (possibleChangedSetting.isPresent()) {
				if (changedValuesMap.containsKey(sectionTitle)) {
					changedValuesMap.get(sectionTitle).add(possibleChangedSetting.get());
				}
				else {
					changedValuesMap.put(sectionTitle, new ArrayList<>(Arrays.asList(possibleChangedSetting.get())));
				}
			}
		});

		return changedValuesMap;
	}

	public void loadPrintProfileSettingsFile() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Jdk8Module());

		//Loop through enumeration and create all print profile settings.
		for (Slicer slicerType : Slicer.values()) {

			Path printProfileSettingsPath = printProfilesPathPreference
					.getAppPathForSlicer(slicerType)
					.resolve(PRINT_PROFILE_SETTINGS_JSON);

			if (LOGGER.isDebugEnabled())
				LOGGER.debug("Loading print profile: " + printProfileSettingsPath.toString());

			try {
				PrintProfileSettings profileSettings = objectMapper.readValue(printProfileSettingsPath.toFile(), PrintProfileSettings.class);
				printProfileSettings.put(slicerType, profileSettings);
				defaultPrintProfileSettings.put(slicerType, profileSettings);
			}
			catch (IOException e) {
				LOGGER.error("Could not load profile: " + printProfileSettingsPath.toString(), e);
			}
		}
	}
}
