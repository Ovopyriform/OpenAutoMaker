package org.openautomaker.environment.preference;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collections;
import java.util.Map;
import java.util.prefs.Preferences;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openautomaker.base.comms.print_server.PrintServerConnection;
import org.openautomaker.base.comms.print_server.PrintServerConnectionDeserializer;
import org.openautomaker.base.comms.print_server.PrintServerConnectionSerializer;
import org.openautomaker.base.inject.comms.PrintServerConnectionFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.InetAddressSerializer;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ConnectedServersPreference extends APreference<Map<InetAddress, PrintServerConnection>> {

	private static final Logger LOGGER = LogManager.getLogger();

	private Map<InetAddress, PrintServerConnection> connectedServers = null;

	private final ObjectMapper objectMapper;

	@Inject
	protected ConnectedServersPreference(
			PrintServerConnectionFactory printServerFactory) {

		super();

		// Clear out the connected servers map if the preference changes
		addChangeListener((evt) -> {
			connectedServers = null;
		});

		// Create the object mapper.
		objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("PrinterServerServerConnectionSerializer", new Version(1, 0, 0, null, null, null));
		module.addSerializer(InetAddress.class, new InetAddressSerializer(true));
		module.addSerializer(PrintServerConnection.class, new PrintServerConnectionSerializer());
		module.addDeserializer(PrintServerConnection.class, new PrintServerConnectionDeserializer(printServerFactory));
		objectMapper.registerModule(module);
	}

	@Override
	public Map<InetAddress, PrintServerConnection> getValue() {
		if (connectedServers != null)
			return connectedServers;

		connectedServers = Map.of();

		String jsonStr = getNode().get(getKey(), null);
		if (jsonStr == null)
			return connectedServers;

		try {
			connectedServers = Collections.unmodifiableMap(
					objectMapper.readValue(jsonStr, new TypeReference<Map<InetAddress, PrintServerConnection>>() {
					}));

		}
		catch (IOException ex) {
			LOGGER.error("Unable to read Connected Servers.  Removing preference data");
			remove();
		}

		return connectedServers;
	}

	@Override
	public void setValue(Map<InetAddress, PrintServerConnection> value) {
		try {
			//This may work.  Need to check how it's going to serialise this map.
			getNode().put(getKey(), objectMapper.writeValueAsString(value));
		}
		catch (JsonProcessingException e) {
			LOGGER.error("Unable to write Connected Servers to preference");
		}
	}

	@Override
	protected Preferences getNode() {
		return getUserNode();
	}
}
