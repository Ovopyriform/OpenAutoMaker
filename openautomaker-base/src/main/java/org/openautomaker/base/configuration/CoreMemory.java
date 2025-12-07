package org.openautomaker.base.configuration;

import java.net.InetAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.openautomaker.base.comms.print_server.PrintServerConnection;
import org.openautomaker.environment.preference.ConnectedServersPreference;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 *
 * @author Ian
 */
@Singleton
public class CoreMemory {
	//private static final Logger LOGGER = LogManager.getLogger();

	private final ConnectedServersPreference connectedServersPreference;

	private static CoreMemory instance = null;

	@Inject
	protected CoreMemory(
			ConnectedServersPreference connectedServersPreference) {

		this.connectedServersPreference = connectedServersPreference;

		instance = this;
	}

	@Deprecated
	public static CoreMemory getInstance() {
		return instance;
	}

	public Map<InetAddress, PrintServerConnection> getActiveRoboxRoots() {
		return connectedServersPreference.getValue();
	}

	public void clearActiveRoboxRoots() {
		connectedServersPreference.remove();
	}

	public void activateRoboxRoot(PrintServerConnection server) {
		Map<InetAddress, PrintServerConnection> serverList = connectedServersPreference.getValue();
		if (serverList.containsValue(server))
			return;

		//Create a modifiable list
		serverList = new ConcurrentHashMap<>(serverList);
		serverList.get(server.getAddress());
		connectedServersPreference.setValue(serverList);
	}

	public void deactivateRoboxRoot(PrintServerConnection server) {
		Map<InetAddress, PrintServerConnection> serverList = connectedServersPreference.getValue();
		if (!serverList.containsValue(server))
			return;

		//Create a modifiable list
		serverList = new ConcurrentHashMap<>(serverList);
		serverList.remove(server.getAddress());
		connectedServersPreference.setValue(serverList);
	}

	public void updateRoboxRoot(PrintServerConnection server) {
		Map<InetAddress, PrintServerConnection> serverList = connectedServersPreference.getValue();
		if (!serverList.containsValue(server))
			return;

		//Create a modifiable list
		serverList = new ConcurrentHashMap<>(serverList);
		serverList.remove(server.getAddress());
		serverList.put(server.getAddress(), server);
		connectedServersPreference.setValue(serverList);
	}
}
