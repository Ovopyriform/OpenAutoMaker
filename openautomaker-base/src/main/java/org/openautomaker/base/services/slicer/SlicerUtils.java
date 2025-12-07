package org.openautomaker.base.services.slicer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openautomaker.environment.preference.slicer.KillCommandPreference;
import org.openautomaker.environment.preference.slicer.SetWorkingDirectoryPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 *
 */
//TODO: Roll this into slicer manager?
@Singleton
public class SlicerUtils {
	private static final Logger LOGGER = LogManager.getLogger();

	private final KillCommandPreference killCommandPreference;
	private final SetWorkingDirectoryPreference setWorkingDirectoryPreference;

	@Inject
	protected SlicerUtils(
			KillCommandPreference killCommandPreference,
			SetWorkingDirectoryPreference setWorkingDirectoryPreference) {

		this.killCommandPreference = killCommandPreference;
		this.setWorkingDirectoryPreference = setWorkingDirectoryPreference;
	}

	//TODO: This should probably only kill the processes that have been created by this program.  Potentially this will kill any cura instances this user has created
	public void killSlicing() {
		Path killCmdPath = killCommandPreference.getValue();

		// Setup the process builder to call the kill command.  Commands are defined at build time
		ProcessBuilder killProcessBuilder = new ProcessBuilder(List.of(killCmdPath.toString()));

		boolean setWorkingDirectory = setWorkingDirectoryPreference.getValue().booleanValue();
		if (setWorkingDirectory)
			killProcessBuilder.directory(killCmdPath.getParent().toFile());

		try {
			killProcessBuilder.start().waitFor();
		}
		catch (IOException | InterruptedException ex) {
			LOGGER.error("Exception whilst killing slicer", ex);
		}
	}
}
