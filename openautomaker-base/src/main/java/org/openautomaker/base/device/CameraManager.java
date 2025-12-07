package org.openautomaker.base.device;

import org.openautomaker.base.camera.CameraInfo;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Singleton
public class CameraManager {

	private final ObservableList<CameraInfo> connectedCameras;

	@Inject
	public CameraManager() {
		connectedCameras = FXCollections.observableArrayList();
	}

	public ObservableList<CameraInfo> getConnectedCameras() {
		return connectedCameras;
	}

	//TODO: This is in no way synchronised.  Probably should be the same pattern as PrinterManager or USBDirectory manager.  But which is correct?!??!
	public void cameraConnected(CameraInfo cameraInfo) {
		connectedCameras.add(cameraInfo);
	}

	public void cameraDisconnected(CameraInfo cameraInfo) {
		connectedCameras.remove(cameraInfo);
	}

}
