package org.openautomaker.base.device;

import java.io.File;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Singleton
public class USBDirectoryManager {

	private final ObservableList<File> mountedUSBDirectories;

	@Inject
	public USBDirectoryManager() {
		mountedUSBDirectories = FXCollections.observableArrayList();
	}

	//TODO: Should this be forced onto the GUI thread like the printer manager
	public synchronized void addAllUSBDirectories(File[] usbDirs) {
		mountedUSBDirectories.retainAll(usbDirs);

		for (File usbDir : usbDirs) {
			if (!mountedUSBDirectories.contains(usbDir))
				mountedUSBDirectories.add(usbDir);
		}
	}

}
