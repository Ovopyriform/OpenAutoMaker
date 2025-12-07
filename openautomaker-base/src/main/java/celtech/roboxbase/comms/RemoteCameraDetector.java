package celtech.roboxbase.comms;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openautomaker.base.camera.CameraInfo;
import org.openautomaker.base.comms.print_server.PrintServerConnection;
import org.openautomaker.environment.preference.ConnectedServersPreference;

import jakarta.inject.Inject;

/**
 * This class currently does not extend DeviceDetector as the search for devices method wants to return a list of DetectedDevices, at this point though we have a list of CameraInfo objects which we want to return.
 * 
 * @author George Salter
 */
public class RemoteCameraDetector {

	private final ConnectedServersPreference connectedServersPreference;

	@Inject
	protected RemoteCameraDetector(
			ConnectedServersPreference connectedServersPreference) {

		super();

		this.connectedServersPreference = connectedServersPreference;
	}

	public List<CameraInfo> searchForDevices() {
		//Take a copy of the list in case it gets changed under our feet
		Map<InetAddress, PrintServerConnection> activeRoboxRoots = connectedServersPreference.getValue();
		List<CameraInfo> allConnectedCameras = new ArrayList<>();

		// Search the roots that have been registered in core memory
		activeRoboxRoots.values().stream()
				.filter(server -> (server.getServerStatus() == PrintServerConnection.ServerStatus.CONNECTED))
				.forEachOrdered((server) -> {
					List<CameraInfo> attachedCameras = server.listAttachedCameras();
					attachedCameras.forEach(camInfo -> camInfo.setServerIP(server.getServerIP()));
					allConnectedCameras.addAll(attachedCameras);
				});

		return allConnectedCameras;
	}

}
