package celtech.roboxbase.comms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openautomaker.environment.preference.printer.PrinterDetectorPreference;

import jakarta.inject.Inject;

/**
 * Looks for devices on USB
 */
public class SerialDeviceDetector extends DeviceDetector {

	private static final Logger LOGGER = LogManager.getLogger();

	private static final String NOT_CONNECTED = "NOT_CONNECTED";

	//TODO: Does putting this here causes the process not to be closed down??
	private final ProcessBuilder processBuilder;

	@Inject
	protected SerialDeviceDetector(
			PrinterDetectorPreference printerDetectorPreference) {
		super();

		List<String> commandElements = new ArrayList<>();
		commandElements.add(printerDetectorPreference.getValue().toString());
		commandElements.addAll(List.of(printerDetectorPreference.getParams()));

		processBuilder = new ProcessBuilder(commandElements);

		if (LOGGER.isDebugEnabled())
			LOGGER.debug(MessageFormat.format("Device detector command: {0}", processBuilder.command()));
	}

	@Override
	public List<DetectedDevice> searchForDevices() {
		Process process = null;

		StringBuilder outputBuffer = new StringBuilder();
		try {
			process = processBuilder.start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				if (line.equalsIgnoreCase(NOT_CONNECTED) == false) {
					outputBuffer.append(line);
				}
			}
		}
		catch (IOException ex) {
			LOGGER.error("Error " + ex);
		}

		List<DetectedDevice> detectedPrinters = new ArrayList<>();

		if (outputBuffer.length() > 0) {
			for (String handle : outputBuffer.toString().split(" ")) {
				detectedPrinters.add(new DetectedDevice(DeviceConnectionType.SERIAL, handle));
			}
		}

		return detectedPrinters;
	}
}
