package celtech.coreUI.controllers.panels;

import celtech.configuration.ApplicationConfiguration;
import celtech.coreUI.controllers.panels.userpreferences.Preferences;
import celtech.coreUI.controllers.utilityPanels.HeadEEPROMController;
import jakarta.inject.Inject;

//TODO: Look at this binding of FXML to classes
public class ExtrasMenuPanelController extends MenuPanelController {

	//	public ExtrasMenuPanelController() {
	//		paneli18Name = "extrasMenu.title";
	//	}

	private final HeadEEPROMController headEEPROMController;
	private final RootScannerPanelController rootScannerPanelController;
	private final MaintenanceInsetPanelController maintenanceInsetPanelController;

	private final Preferences preferences;

	@Inject
	protected ExtrasMenuPanelController(
			HeadEEPROMController headEEPROMController,
			RootScannerPanelController rootScannerPanelController,
			MaintenanceInsetPanelController maintenanceInsetPanelController,
			Preferences preferences) {

		super();

		this.preferences = preferences;

		paneli18Name = "extrasMenu.title";
		this.headEEPROMController = headEEPROMController;
		this.rootScannerPanelController = rootScannerPanelController;
		this.maintenanceInsetPanelController = maintenanceInsetPanelController;
	}

	/**
	 * Define the inner panels to be offered in the main menu. For the future this is configuration information that could be e.g. stored in XML or in a plugin.
	 */
	@Override
	protected void setupInnerPanels() {
		//TODO: These all look like the controller should be defined in the fxml.  Look to moving there.
		loadInnerPanel(
				ApplicationConfiguration.fxmlUtilityPanelResourcePath + "headEEPROM.fxml",
				headEEPROMController);

		//UserPreferences userPreferences = Lookup.getUserPreferences();
		loadInnerPanel(
				ApplicationConfiguration.fxmlPanelResourcePath + "preferencesPanel.fxml",
				new PreferencesInnerPanelController("preferences.environment",
						preferences.createEnvironmentPreferences()));
		loadInnerPanel(
				ApplicationConfiguration.fxmlPanelResourcePath + "preferencesPanel.fxml",
				new PreferencesInnerPanelController("preferences.printing",
						preferences.createPrintingPreferences()));
		//        loadInnerPanel(
		//                ApplicationConfiguration.fxmlPanelResourcePath + "preferencesPanel.fxml",
		//                new PreferencesInnerPanelController("preferences.timelapse",
		//                        Preferences.createTimelapsePreferences(userPreferences)));

		loadInnerPanel(
				ApplicationConfiguration.fxmlPanelResourcePath + "rootScanner.fxml",
				rootScannerPanelController);

		loadInnerPanel(
				ApplicationConfiguration.fxmlPanelResourcePath + "MaintenanceInsetPanel.fxml",
				maintenanceInsetPanelController);

		//TODO: These should all be injected
		loadInnerPanel(
				ApplicationConfiguration.fxmlPanelResourcePath + "preferencesPanel.fxml",
				new PreferencesInnerPanelController("preferences.advanced",
						preferences.createAdvancedPreferences()));
		loadInnerPanel(
				ApplicationConfiguration.fxmlPanelResourcePath + "preferencesPanel.fxml",
				new PreferencesInnerPanelController("preferences.customPrinter",
						preferences.createCustomPrinterPreferences()));
	}
}
