package org.openautomaker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import celtech.roboxbase.comms.interapp.AbstractInterAppRequest;

public class InterAppRequestTest {
	static {
		// Set the libertySystems config file property to inidicate it is a test request.
		// The property is set in this static initializer because the configuration is loaded before the test is run.
		System.setProperty("libertySystems.configFile", "$test$");
	}

	private final Logger LOGGER = LogManager.getLogger();

	private static final ObjectMapper mapper = new ObjectMapper();
	private static final String jsonifiedClass = "{\"@class\":\"org.openautomaker.InterAppRequest\",\"command\":\"LOAD_MESH_INTO_LAYOUT_VIEW\",\"urlEncodedParameters\":[{\"type\":\"PROJECT_NAME\",\"urlEncodedParameter\":\"A project\"},{\"type\":\"MODEL_NAME\",\"urlEncodedParameter\":\"A model with spaces\"},{\"type\":\"MODEL_NAME\",\"urlEncodedParameter\":\"Another model with spaces\"}]}";

	public InterAppRequestTest() {
	}


	@Test
	public void serializesToJSON() throws Exception {
		final InterAppRequest packet = getTestPacket();

		String mappedValue = mapper.writeValueAsString(packet);
		assertEquals(jsonifiedClass, mappedValue);
	}

	@Test
	public void deserializesFromJSON() throws Exception {
		final InterAppRequest packet = getTestPacket();

		try {
			AbstractInterAppRequest packetRec = mapper.readValue(jsonifiedClass, AbstractInterAppRequest.class);
			assertEquals(packet, packetRec);
		}
		catch (Exception e) {
			LOGGER.error(e);
			fail();
		}
	}

	private InterAppRequest getTestPacket() {
		InterAppRequest packet = new InterAppRequest();

		packet.setCommand(InterAppRequestCommand.LOAD_MESH_INTO_LAYOUT_VIEW);
		packet.addSeparatedURLEncodedParameter(InterAppParameterType.PROJECT_NAME, "A project");
		packet.addSeparatedURLEncodedParameter(InterAppParameterType.MODEL_NAME, "A model with spaces");
		packet.addSeparatedURLEncodedParameter(InterAppParameterType.MODEL_NAME, "Another model with spaces");

		return packet;
	}

	@Test
	public void paramsInOut() {
		InterAppRequest packet = new InterAppRequest();
		packet.addSeparatedURLEncodedParameter(InterAppParameterType.MODEL_NAME, "fred&jim");
		assertEquals(2, packet.getUnencodedParameters().size());
		assertEquals(InterAppParameterType.MODEL_NAME, packet.getUnencodedParameters().get(0).getType());
		assertEquals("fred", packet.getUnencodedParameters().get(0).getUnencodedParameter());
		assertEquals(InterAppParameterType.MODEL_NAME, packet.getUnencodedParameters().get(1).getType());
		assertEquals("jim", packet.getUnencodedParameters().get(1).getUnencodedParameter());
	}
}
