package celtech.roboxbase.comms.interapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InterAppResponseTest {

	private static final ObjectMapper mapper = new ObjectMapper();
	private static final String jsonifiedClass = "{\"@class\":\"celtech.roboxbase.comms.interapp.InterAppResponse\",\"responseStatus\":\"REJECTED_PRINTER_NOT_READY\"}";

	@Test
	public void serializesToJSON() throws Exception {
		final InterAppResponse packet = getTestPacket();

		String mappedValue = mapper.writeValueAsString(packet);
		assertEquals(jsonifiedClass, mappedValue);
	}

	@Test
	public void deserializesFromJSON() throws Exception {
		final InterAppResponse packet = getTestPacket();

		try {
			InterAppResponse packetRec = mapper.readValue(jsonifiedClass, InterAppResponse.class);
			assertEquals(packet, packetRec);
		}
		catch (Exception e) {
			System.out.println(e.getCause().getMessage());
			fail();
		}
	}

	private InterAppResponse getTestPacket() {
		InterAppResponse packet = new InterAppResponse();

		packet.setResponseStatus(InterAppResponseStatus.REJECTED_PRINTER_NOT_READY);

		return packet;
	}
}
