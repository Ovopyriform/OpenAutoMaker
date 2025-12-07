package org.openautomaker.base.comms.print_server;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class PrintServerConnectionSerializer extends StdSerializer<PrintServerConnection> {

	private static final long serialVersionUID = -4787559966639004765L;

	public PrintServerConnectionSerializer() {
		this(PrintServerConnection.class);
	}

	public PrintServerConnectionSerializer(Class<PrintServerConnection> t) {
		super(t);
	}

	@Override
	public void serialize(
			PrintServerConnection server, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {

		jgen.writeStartObject();
		jgen.writeStringField("address", server.getServerAddress());
		jgen.writeStringField("name", server.getName());
		jgen.writeStringField("rootUUID", server.getRootUUID());
		jgen.writeStringField("pin", server.getPin());
		jgen.writeBooleanField("wasAutomaticallyAdded", server.isDiscoveredConnection());
		jgen.writeObjectField("cameraTag", server.cameraTagProperty().get());
		jgen.writeObjectField("version", server.getVersion());
		jgen.writeEndObject();
	}
}
