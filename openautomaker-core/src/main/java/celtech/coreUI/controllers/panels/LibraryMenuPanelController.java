package celtech.coreUI.controllers.panels;

import org.openautomaker.base.configuration.RoboxProfile;
import org.openautomaker.base.configuration.fileRepresentation.CameraProfile;
import org.openautomaker.environment.I18N;

import celtech.configuration.ApplicationConfiguration;
import jakarta.inject.Inject;

/**
 *
 * @author Ian
 */
public class LibraryMenuPanelController extends MenuPanelController {

	private InnerPanelDetails cameraProfileDetails = null;
	private final CameraProfilesPanelController cameraProfileDetailsController;
	private final ProfileLibraryPanelController profileLibraryPanelController;
	private final FilamentLibraryPanelController filamentLibraryPanelController;
	private final I18N i18n;

	//	public LibraryMenuPanelController() {
	//		paneli18Name = "libraryMenu.title";
	//	}

	//TODO: Looks like these need restructuring.  Slightly odd components structure.  Have a look.
	@Inject
	protected LibraryMenuPanelController(
			I18N i18n,
			CameraProfilesPanelController cameraProfileDetailsController,
			ProfileLibraryPanelController profileLibraryPanelController,
			FilamentLibraryPanelController filamentLibraryPanelController) {

		super();

		this.i18n = i18n;
		this.cameraProfileDetailsController = cameraProfileDetailsController;
		this.profileLibraryPanelController = profileLibraryPanelController;
		this.filamentLibraryPanelController = filamentLibraryPanelController;

		paneli18Name = "libraryMenu.title";
	}

	@Override
	protected void setupInnerPanels() {
		//TODO: This doesn't seem like the right way to do this.  Perhaps the controllers should be specified in the FXML?
		loadInnerPanel(
				ApplicationConfiguration.fxmlPanelResourcePath + "filamentLibraryPanel.fxml",
				filamentLibraryPanelController);

		profileDetails = loadInnerPanel(
				ApplicationConfiguration.fxmlUtilityPanelResourcePath + "profileDetails.fxml",
				profileLibraryPanelController);

		cameraProfileDetails = loadInnerPanel(
				ApplicationConfiguration.fxmlPanelResourcePath + "cameraProfilesPanel.fxml",
				cameraProfileDetailsController);
	}

	public void showAndSelectPrintProfile(RoboxProfile roboxProfile) {
		String profileMenuItemName = i18n.t(profileDetails.innerPanel.getMenuTitle());
		panelMenu.selectItemOfName(profileMenuItemName);
		profileLibraryPanelController.setAndSelectPrintProfile(roboxProfile);
	}

	public void showAndSelectCameraProfile(CameraProfile profile) {
		String cameraProfileMenuItemName = i18n.t(cameraProfileDetails.innerPanel.getMenuTitle());
		panelMenu.selectItemOfName(cameraProfileMenuItemName);
		cameraProfileDetailsController.setAndSelectCameraProfile(profile);
	}
}
