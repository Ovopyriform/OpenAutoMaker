package celtech.roboxbase.comms.remote.clear;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WifiStatusResponseTest {

	private static final ObjectMapper mapper = new ObjectMapper();
	private static final String jsonifiedClass = "{\"poweredOn\":true,\"associated\":true,\"ssid\":\"TestSSID\"}";

	@Test
	public void serializesToJSON() throws Exception {
		final WifiStatusResponse packet = getTestPacket();

		String mappedValue = mapper.writeValueAsString(packet);
		assertEquals(jsonifiedClass, mappedValue);
	}

	@Test
	public void deserializesFromJSON() throws Exception {
		final WifiStatusResponse packet = getTestPacket();

		try {
			WifiStatusResponse packetRec = mapper.readValue(jsonifiedClass, WifiStatusResponse.class);
			assertEquals(packet, packetRec);
		}
		catch (Exception e) {
			System.out.println(e.getCause().getMessage());
			fail();
		}
	}

	private WifiStatusResponse getTestPacket() {
		return new WifiStatusResponse(true, true, "TestSSID");
	}
}
