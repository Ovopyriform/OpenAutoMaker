package org.openautomaker;

import org.openautomaker.base.task_executor.TaskExecutor;
import org.openautomaker.environment.preference.application.LastRunVersionPreference;
import org.openautomaker.environment.preference.application.VersionPreference;

import celtech.appManager.ApplicationMode;
import celtech.appManager.ApplicationStatus;
import jakarta.inject.Inject;

/**
 *
 * @author Ian
 */
class WelcomeToApplicationManager {

	private final TaskExecutor taskExecutor;
	private final LastRunVersionPreference lastRunVersionPreference;
	private final VersionPreference versionPreference;
	private final ApplicationStatus applicationStatus;

	@Inject
	protected WelcomeToApplicationManager(
			TaskExecutor taskExecutor,
			LastRunVersionPreference lastRunVersionPreference,
			VersionPreference versionPreference,
			ApplicationStatus applicationStatus) {

		this.taskExecutor = taskExecutor;
		this.lastRunVersionPreference = lastRunVersionPreference;
		this.versionPreference = versionPreference;
		this.applicationStatus = applicationStatus;

	}

	public void displayWelcomeIfRequired() {
		if (!applicationJustInstalled())
			return;

		showWelcomePage();

		lastRunVersionPreference.setValue(versionPreference.getValue());
	}

	private boolean applicationJustInstalled() {
		return !(versionPreference.getValue().isEqualTo(lastRunVersionPreference.getValue()));
	}

	private void showWelcomePage() {
		taskExecutor.runOnGUIThread(() -> {
			applicationStatus.setMode(ApplicationMode.WELCOME);
		});
	}
}
