package org.openautomaker.base.comms.print_server;

import java.io.IOException;
import java.net.InetAddress;

import org.openautomaker.base.inject.comms.PrintServerConnectionFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.vdurmont.semver4j.Semver;

import jakarta.inject.Inject;

public class PrintServerConnectionDeserializer extends StdDeserializer<PrintServerConnection> {

	private static final long serialVersionUID = 6111456077272339190L;

	private final PrintServerConnectionFactory printServerConnectionFactory;

	@Inject
	public PrintServerConnectionDeserializer(
			PrintServerConnectionFactory detectedServerFactory) {

		this(detectedServerFactory, PrintServerConnection.class);
	}

	public PrintServerConnectionDeserializer(
			PrintServerConnectionFactory printServerConnectionFactory,
			Class<? extends PrintServerConnection> detectedServer) {
		super(detectedServer);

		this.printServerConnectionFactory = printServerConnectionFactory;
	}

	@Override
	public PrintServerConnection deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
		ObjectCodec codec = jp.getCodec();
		JsonNode node = codec.readTree(jp);

		String addressText = node.get("address").asText();
		InetAddress address = InetAddress.getByName(addressText);

		PrintServerConnection server = printServerConnectionFactory.create(address);

		server.setServerAddress(addressText);
		server.setName(node.get("name").asText());
		JsonNode subNode = node.get("rootUUID");
		if (subNode != null)
			server.setRootUUID(subNode.asText());
		server.setVersion(new Semver(node.get("version").get("versionString").asText()));
		server.setPin(node.get("pin").asText());
		server.setDiscoveredConnection(node.get("wasAutomaticallyAdded").asBoolean());
		subNode = node.get("cameraTag");
		if (subNode != null) {
			server.setCameraTag(subNode.get("cameraProfileName").asText(),
					subNode.get("cameraName").asText());

		}
		return server;
	}
}
