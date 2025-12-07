package celtech.roboxbase.comms.remote.types;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.scene.paint.Color;

/**
 *
 * @author ianhudson
 */
public class SerializableColourTest {

	private static final String jsonifiedClass = "{\"webColour\":\"0xf0f8ffff\"}";

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void serializesToJSON() throws Exception {
		final SerializableColour packet = new SerializableColour();

		packet.setWebColour(Color.ALICEBLUE.toString());

		String mappedValue = mapper.writeValueAsString(packet);
		assertEquals(mapper.readTree(jsonifiedClass), mapper.readTree(mappedValue));
	}

	@Test
	public void deserializesFromJSON() throws Exception {
		final SerializableColour packet = new SerializableColour();
		packet.setWebColour(Color.ALICEBLUE.toString());
		SerializableColour packetRec = mapper.readValue(jsonifiedClass, SerializableColour.class);
		assertEquals(packet, packetRec);
	}
}
