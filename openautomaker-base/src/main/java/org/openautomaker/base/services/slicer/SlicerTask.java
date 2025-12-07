package org.openautomaker.base.services.slicer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openautomaker.base.configuration.BaseConfiguration;
import org.openautomaker.base.inject.exporters.STLOutputConverterFactory;
import org.openautomaker.base.printerControl.model.Head;
import org.openautomaker.base.printerControl.model.Printer;
import org.openautomaker.base.slicer.SlicerManager;
import org.openautomaker.base.utils.TimeUtils;
import org.openautomaker.base.utils.exporters.MeshExportResult;
import org.openautomaker.base.utils.exporters.MeshFileOutputConverter;
import org.openautomaker.base.utils.models.PrintableMeshes;
import org.openautomaker.environment.Slicer;
import org.openautomaker.environment.preference.slicer.SetWorkingDirectoryPreference;

import com.google.inject.assistedinject.Assisted;

import jakarta.inject.Inject;
import javafx.concurrent.Task;

/**
 *
 * @author ianhudson
 */

public class SlicerTask extends Task<SliceResult> implements ProgressReceiver {
	private static final Logger LOGGER = LogManager.getLogger();

	private static final TimeUtils TIME_UTILS = new TimeUtils();
	private static final String SLICER_TIMER_NAME = "Slicer";

	private final STLOutputConverterFactory stlOutputConverterFactory;
	private final SlicerManager slicerManager;
	private final SetWorkingDirectoryPreference setWorkingDirectoryPreference;

	private final String printJobUUID;
	private final PrintableMeshes printableMeshes;
	private final Path printJobDirectory;
	private final Printer printerToUse;
	private final ProgressReceiver progressReceiver;

	@Inject
	protected SlicerTask(
			STLOutputConverterFactory stlOutputConverterFactory,
			SlicerManager slicerManager,
			SetWorkingDirectoryPreference setWorkingDirectoryPreference,
			@Assisted String printJobUUID,
			@Assisted PrintableMeshes printableMeshes,
			@Assisted Path printJobDirectory,
			@Assisted Printer printerToUse,
			@Assisted ProgressReceiver progressReceiver) {

		this.stlOutputConverterFactory = stlOutputConverterFactory;
		this.slicerManager = slicerManager;
		this.setWorkingDirectoryPreference = setWorkingDirectoryPreference;

		this.printJobUUID = printJobUUID;
		this.printableMeshes = printableMeshes;
		this.printJobDirectory = printJobDirectory;
		this.printerToUse = printerToUse;
		this.progressReceiver = progressReceiver;
		updateProgress(0.0, 100.0);
	}

	@Override
	protected SliceResult call() throws Exception {
		if (isCancelled()) {
			LOGGER.debug("Slice cancelled");
			return null;
		}

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Slice " + printableMeshes.getSettings().getName());

		updateTitle("Slicer");
		updateMessage("Preparing model for conversion");
		updateProgress(0.0, 100.0);

		LOGGER.debug("Starting slicing");
		String timerUUID = UUID.randomUUID().toString();
		TIME_UTILS.timerStart(timerUUID, SLICER_TIMER_NAME);

		MeshFileOutputConverter outputConverter = null;

		outputConverter = stlOutputConverterFactory.create();

		MeshExportResult meshExportResult = null;

		// Output multiple files if we are using Cura
		if (printerToUse == null
				|| printerToUse.headProperty().get() == null
				|| printerToUse.headProperty().get().headTypeProperty().get() == Head.HeadType.SINGLE_MATERIAL_HEAD) {
			meshExportResult = outputConverter.outputFile(printableMeshes.getMeshesForProcessing(), printJobUUID, printJobDirectory,
					true);
		}
		else {
			meshExportResult = outputConverter.outputFile(printableMeshes.getMeshesForProcessing(), printJobUUID, printJobDirectory,
					false);
		}

		if (isCancelled()) {
			LOGGER.debug("Slice cancelled");
			return null;
		}

		Vector3D centreOfPrintedObject = meshExportResult.getCentre();

		boolean succeeded = sliceFile(
				meshExportResult.getCreatedFiles(),
				printableMeshes.getExtruderForModel(),
				centreOfPrintedObject,
				progressReceiver,
				printableMeshes.getNumberOfNozzles());

		try {
			TIME_UTILS.timerStop(timerUUID, SLICER_TIMER_NAME);
			LOGGER.debug("Slicer Timer Report");
			LOGGER.debug("============");
			LOGGER.debug(SLICER_TIMER_NAME + " " + 0.001 * TIME_UTILS.timeTimeSoFar_ms(timerUUID, SLICER_TIMER_NAME) + " seconds");
			LOGGER.debug("============");
			TIME_UTILS.timerDelete(timerUUID, SLICER_TIMER_NAME);
		}
		catch (TimeUtils.TimerNotFoundException ex) {
			// This really should not happen!
			LOGGER.debug("Slicer Timer Report - timer not found!");
		}

		return new SliceResult(printJobUUID, printableMeshes, printerToUse, succeeded);
	}

	private boolean sliceFile(
			List<String> createdMeshFiles,
			List<Integer> extrudersForMeshes,
			Vector3D centreOfPrintedObject,
			ProgressReceiver progressReceiver,
			int numberOfNozzles) {

		//TODO: Look into this comment more
		// Heads with a single nozzle are anomalous because
		// tool zero uses the "E" extruder, which is usually
		// extruder number 1. So for these kinds of head, the
		// extruder number needs to be reset to 0, hence the
		// need for the numberOfNozzles parameter.
		// This hack is closely related to the hack in
		// CuraDefaultSettingsEditor that also sets the extruder
		// number to zero for single nozzle heads.

		boolean succeeded = false;

		String tempGcodeFilename = printJobUUID + BaseConfiguration.gcodeTempFileExtension;

		String jsonSettingsFile = "fdmprinter_robox.def.json";

		Slicer slicer = slicerManager.getSlicer();

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Selected slicer is " + slicer + ": " + Thread.currentThread().getName());

		// Will probably have to revisit this for windows.
		List<String> commandElements = new ArrayList<>();
		commandElements.add(slicerManager.getExecutable().toString());
		commandElements.addAll(List.of(slicerManager.formatParams(Path.of(jsonSettingsFile), Path.of(tempGcodeFilename))));

		int previousExtruder;
		int extruderNo;

		// Theoretically, this should work for all OSs
		// Build the meshes
		previousExtruder = -1;
		extruderNo = 0;

		for (int i = 0; i < createdMeshFiles.size(); i++) {
			if (previousExtruder != extrudersForMeshes.get(i) && numberOfNozzles > 1)
				extruderNo = extrudersForMeshes.get(i) > 0 ? 0 : 1; //TODO: WTF does this mean?! 'Extruder needs swapping... just because'

			commandElements.addAll(List.of(slicerManager.formatMeshParams(extruderNo, Path.of(createdMeshFiles.get(i)))));
			previousExtruder = extrudersForMeshes.get(i);
		}

		ProcessBuilder slicerProcessBuilder = new ProcessBuilder(commandElements);
		if (setWorkingDirectoryPreference.getValue().booleanValue())
			slicerProcessBuilder.directory(printJobDirectory.toFile());

		LOGGER.info("Slicer command is " + slicerProcessBuilder.command());

		Process slicerProcess = null;

		if (isCancelled()) {
			LOGGER.debug("Slice cancelled");
			return false;
		}

		try {
			slicerProcess = slicerProcessBuilder.start();
			// any error message?
			SlicerOutputGobbler errorGobbler = new SlicerOutputGobbler(progressReceiver, slicerProcess.getErrorStream(), "ERROR",
					slicer);

			// any output?
			SlicerOutputGobbler outputGobbler = new SlicerOutputGobbler(progressReceiver, slicerProcess.getInputStream(),
					"OUTPUT", slicer);

			// kick them off
			errorGobbler.start();
			outputGobbler.start();

			int exitStatus = slicerProcess.waitFor();

			if (isCancelled()) {
				LOGGER.debug("Slice cancelled");
				return false;
			}

			switch (exitStatus) {
				case 0:
					LOGGER.debug("Slicer terminated successfully ");
					succeeded = true;
					break;
				default:
					LOGGER.error("Failure when invoking slicer with command line: " + String.join(
							" ", slicerProcessBuilder.command()));
					LOGGER.error("Slicer terminated with exit code " + exitStatus);
					break;
			}
		}
		catch (IOException ex) {
			LOGGER.error("Exception whilst running slicer: " + ex);
		}
		catch (InterruptedException ex) {
			LOGGER.warn("Interrupted whilst waiting for slicer to complete");
			if (slicerProcess != null) {
				slicerProcess.destroyForcibly();
			}
		}

		return succeeded;
	}

	@Override
	public void progressUpdateFromSlicer(String message, float workDone) {
		updateMessage(message);
		updateProgress(workDone, 100.0);
	}
}
