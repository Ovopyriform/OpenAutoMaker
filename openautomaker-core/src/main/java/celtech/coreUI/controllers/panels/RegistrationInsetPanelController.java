
package celtech.coreUI.controllers.panels;

import celtech.appManager.ApplicationStatus;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author tony
 */
public class RegistrationInsetPanelController {
	@FXML
	void backwardPressed(ActionEvent event) {
		applicationStatus.returnToLastMode();
	}

	private final ApplicationStatus applicationStatus;

	@Inject
	protected RegistrationInsetPanelController(
			ApplicationStatus applicationStatus) {

		this.applicationStatus = applicationStatus;
	}

	public void initialize() {

	}

}
