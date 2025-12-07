package org.openautomaker.base.configuration.datafileaccessors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openautomaker.base.configuration.RoboxProfile;
import org.openautomaker.base.configuration.profilesettings.PrintProfileSetting;
import org.openautomaker.environment.Slicer;
import org.openautomaker.environment.preference.printer.PrintProfilesPathPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

@Singleton
public class RoboxProfileSettingsContainer {

	private static final Logger LOGGER = LogManager.getLogger();

	private static final String DOT_ROBOXPROFILE = ".roboxprofile";

	private static final String TITLE_BORDER = "//==============";
	private static final String METADATA = "Metadata";
	private static final String PROFILE_NAME = "profileName";
	private static final String HEAD_TYPE = "headType";

	private Map<Slicer, Map<String, List<RoboxProfile>>> roboxProfiles = new HashMap<>();
	private Map<Slicer, Map<String, ObservableList<RoboxProfile>>> customProfiles = new HashMap<>();

	private final PrintProfilesPathPreference printProfilesPathPreference;

	@Inject
	protected RoboxProfileSettingsContainer(
			PrintProfilesPathPreference printProfilesPathPreference) {

		this.printProfilesPathPreference = printProfilesPathPreference;

		for (Slicer slicerType : Slicer.values())
			loadRoboxProfiles(slicerType);

	}

	public Map<String, List<RoboxProfile>> getRoboxProfilesForSlicer(Slicer slicerType) {
		Map<String, List<RoboxProfile>> profiles = roboxProfiles.get(slicerType);

		if (profiles != null)
			return profiles;

		roboxProfiles.put(slicerType, new HashMap<>());
		return roboxProfiles.get(slicerType);
	}

	public Map<String, ObservableList<RoboxProfile>> getCustomRoboxProfilesForSlicer(Slicer slicerType) {
		Map<String, ObservableList<RoboxProfile>> profiles = customProfiles.get(slicerType);

		if (profiles != null)
			return profiles;

		customProfiles.put(slicerType, new HashMap<>());
		return customProfiles.get(slicerType);
	}

	public Optional<RoboxProfile> getRoboxProfileWithName(String profileName, Slicer slicerType, String headType) {
		List<RoboxProfile> profilesForHead = getRoboxProfilesForSlicer(slicerType).get(headType);
		Optional<RoboxProfile> roboxProfile = (profilesForHead != null)
				? profilesForHead.stream()
						.filter(profile -> profile.getName().equals(profileName))
						.findAny()
				: Optional.empty();
		return roboxProfile;
	}

	public RoboxProfile loadHeadProfileForSlicer(String headType, Slicer slicerType) {

		// TODO: Could this use the default slicer and not require it to be passed in?
		Path headProfilePath = printProfilesPathPreference.getAppPathForSlicer(slicerType).resolve(headType);

		Map<String, String> settingsMap = loadHeadSettingsIntoMap(headProfilePath, slicerType);
		RoboxProfile headProfile = new RoboxProfile(headType, headType, true, settingsMap);
		return headProfile;
	}

	public RoboxProfile saveCustomProfile(Map<String, List<PrintProfileSetting>> settingsToWrite, String nameForProfile, String headType, Slicer slicerType) {
		List<RoboxProfile> allProfilesForHead = getRoboxProfilesForSlicer(slicerType).get(headType);
		List<RoboxProfile> customProfilesForHead = getCustomRoboxProfilesForSlicer(slicerType).get(headType);
		RoboxProfile roboxProfile;

		Optional<RoboxProfile> existingProfile = customProfilesForHead.stream()
				.filter(profile -> profile.getName().equals(nameForProfile))
				.findAny();
		if (existingProfile.isPresent()) {
			customProfilesForHead.remove(existingProfile.get());
			allProfilesForHead.remove(existingProfile.get());
			roboxProfile = saveUserProfile(nameForProfile, slicerType, settingsToWrite, headType);
		}
		else {
			roboxProfile = saveUserProfile(nameForProfile, slicerType, settingsToWrite, headType);
		}
		customProfilesForHead.add(roboxProfile);
		allProfilesForHead.add(roboxProfile);
		return roboxProfile;
	}

	public void deleteCustomProfile(String profileName, Slicer slicerType, String headType) {

		ObservableList<RoboxProfile> customRoboxProfiles = getCustomRoboxProfilesForSlicer(slicerType).get(headType);
		List<RoboxProfile> allRoboxProfiles = getRoboxProfilesForSlicer(slicerType).get(headType);
		Optional<RoboxProfile> profileToDelete = customRoboxProfiles.stream()
				.filter(profile -> profile.getName().equals(profileName))
				.findAny();

		if (profileToDelete.isPresent()) {
			allRoboxProfiles.remove(profileToDelete.get());
			customRoboxProfiles.remove(profileToDelete.get());
		}
		else {
			LOGGER.error("File " + profileName + ", doesn't exist in profiles list for slicer: " + slicerType);
		}

		File profileFile = printProfilesPathPreference.getUserPathForSlicer(slicerType).resolve(headType).resolve(profileName + DOT_ROBOXPROFILE).toFile();
		if (!profileFile.exists())
			return;

		if (!profileFile.delete())
			LOGGER.error("Could not delete file: " + profileFile.getAbsolutePath());
		;
	}

	public void addProfileChangeListener(ListChangeListener<? super RoboxProfile> listChangeListener) {
		for (Slicer slicerType : Slicer.values()) {
			getCustomRoboxProfilesForSlicer(slicerType).values()
					.forEach(observableList -> observableList.addListener(listChangeListener));
		}
	}

	public void removeProfileChangeListener(ListChangeListener<? super RoboxProfile> listChangeListener) {
		for (Slicer slicerType : Slicer.values()) {
			getCustomRoboxProfilesForSlicer(slicerType).values()
					.forEach(observableList -> observableList.removeListener(listChangeListener));
		}
	}

	private void loadRoboxProfiles(Slicer slicerType) {
		loadRoboxProfilesIntoMap(printProfilesPathPreference.getAppPathForSlicer(slicerType),
				slicerType, true);

		loadRoboxProfilesIntoMap(printProfilesPathPreference.getUserPathForSlicer(slicerType),
				slicerType, false);
	}

	// TODO: This seems mental.  Could just use properties.
	private void loadRoboxProfilesIntoMap(Path profilePath, Slicer slicerType, boolean standardProfile) {
		Map<String, List<RoboxProfile>> allProfilesMap = getRoboxProfilesForSlicer(slicerType);
		Map<String, ObservableList<RoboxProfile>> customProfilesMap = getCustomRoboxProfilesForSlicer(slicerType);

		File profileFile = profilePath.toFile();
		if (!(profileFile.exists() && profileFile.isDirectory()))
			return;

		for (File headDir : profileFile.listFiles()) {
			if (headDir.isDirectory()) {
				String headType = headDir.getName();
				List<String> usedNames = allProfilesMap.getOrDefault(headType, new ArrayList<>()).stream()
						.map(profile -> profile.getName())
						.collect(Collectors.toList());

				Map<String, String> settings = loadHeadSettingsIntoMap(headDir.toPath(), slicerType);

				List<RoboxProfile> allRoboxProfiles = new ArrayList<>();
				ObservableList<RoboxProfile> customRoboxProfiles = FXCollections.observableArrayList();

				for (File profile : headDir.listFiles((fileDir, fileName) -> {
					return fileName.endsWith(DOT_ROBOXPROFILE);
				})) {
					String profileName = profile.getName().split("\\.")[0];
					if (!profileName.equals(headType) && !usedNames.contains(profileName)) {
						Map<String, String> profileSettings = new HashMap<>(settings);
						addOrOverriteSettings(profile, profileSettings);
						RoboxProfile roboxProfile = new RoboxProfile(profileName, headType, standardProfile, profileSettings);
						allRoboxProfiles.add(roboxProfile);

						if (!standardProfile) {
							customRoboxProfiles.add(roboxProfile);
						}
						usedNames.add(profileName);
					}
				}

				if (allProfilesMap.containsKey(headType)) {
					allProfilesMap.get(headType).addAll(allRoboxProfiles);
				}
				else {
					allProfilesMap.put(headType, allRoboxProfiles);
				}

				if (customProfilesMap.containsKey(headType)) {
					customProfilesMap.get(headType).addAll(customRoboxProfiles);
				}
				else {
					customProfilesMap.put(headType, customRoboxProfiles);
				}
			}
		}
	}

	private static Map<String, String> loadHeadSettingsIntoMap(Path profilePath, Slicer slicerType) {

		File headFile = profilePath.resolve(profilePath.getFileName() + DOT_ROBOXPROFILE).toFile();

		if (!headFile.exists()) {
			LOGGER.warn("Head profile not found: " + headFile.toString());
			return new HashMap<>();
		}

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Loading print head settings from '" + headFile.toString());

		Map<String, String> settingsMap = new HashMap<>();
		addOrOverriteSettings(headFile, settingsMap);

		return settingsMap;
	}

	private static void addOrOverriteSettings(File settings, Map<String, String> settingsMap) {
		try (BufferedReader br = new BufferedReader(new FileReader(settings))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (!(line.trim().startsWith("//") || line.trim().equals(""))) {
					String[] keyValuePair = line.split("=");
					settingsMap.put(keyValuePair[0], keyValuePair[1]);
				}
			}
		}
		catch (IOException ex) {
			LOGGER.error("Could not load profile from: " + settings.toString(), ex);
		}
	}

	private RoboxProfile saveUserProfile(String profileName, Slicer slicerType, Map<String, List<PrintProfileSetting>> settingsToWrite, String headType) {

		Path profilePath = printProfilesPathPreference.getUserPathForSlicer(slicerType).resolve(headType);
		try {
			Files.createDirectories(profilePath);
		}
		catch (IOException e) {
			LOGGER.error("Could not create directory: " + profilePath.toString());
		}

		profilePath = profilePath.resolve(profileName + DOT_ROBOXPROFILE);

		//String headDirPath = FileUtilities.findOrCreateFileInDir(BaseConfiguration.getUserPrintProfileDirectoryForSlicer(slicerType), headType);

		//String profileFilePath = headDirPath + "/" + profileName + BaseConfiguration.printProfileFileExtension;
		//File file = new File(profileFilePath);

		File profileFile = profilePath.toFile();
		// Is this required?  FileWriter will overwrite
		if (profileFile.exists())
			profileFile.delete();

		List<String> metaData = List.of(PROFILE_NAME + "=" + profileName, HEAD_TYPE + "=" + headType);

		writeRoboxProfile(profileFile, settingsToWrite, metaData);

		Path headPath = printProfilesPathPreference.getAppPathForSlicer(slicerType).resolve(headType);

		Map<String, String> settingsMap = loadHeadSettingsIntoMap(headPath, slicerType);
		addOrOverriteSettings(profileFile, settingsMap);
		RoboxProfile roboxProfile = new RoboxProfile(profileName, headType, false, settingsMap);
		return roboxProfile;
	}

	private void writeRoboxProfile(File profileFile, Map<String, List<PrintProfileSetting>> settingsToWrite, List<String> metaData) {
		try (PrintWriter printWriter = new PrintWriter(new FileWriter(profileFile))) {

			printWriter.println(TITLE_BORDER);
			printWriter.println("//" + METADATA);
			printWriter.println(TITLE_BORDER);
			metaData.forEach(data -> printWriter.println(data));
			printWriter.println("");

			for (Entry<String, List<PrintProfileSetting>> entry : settingsToWrite.entrySet()) {
				String settingsSection = entry.getKey();
				printWriter.println(TITLE_BORDER);
				printWriter.println("//" + settingsSection);
				printWriter.println(TITLE_BORDER);

				entry.getValue().forEach(setting -> printWriter.println(setting.getId() + "=" + setting.getValue()));

				printWriter.println("");
			}
		}
		catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		}
	}

}
