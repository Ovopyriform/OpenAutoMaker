package celtech.roboxbase.comms.remote.rx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import celtech.roboxbase.comms.remote.clear.WifiStatusResponse;

/**
 *
 * @author ianhudson
 */
public class WifiStatusResponseTest {

	private static final String jsonifiedClass = "{\"poweredOn\":true,\"associated\":false,\"ssid\":\"\"}";

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void serializesToJSON() throws Exception {
		final WifiStatusResponse packet = getTestPacket();

		String mappedValue = mapper.writeValueAsString(packet);

		assertEquals(mapper.readTree(jsonifiedClass), mapper.readTree(mappedValue));
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
		WifiStatusResponse packet = new WifiStatusResponse();

		packet.setPoweredOn(true);
		packet.setAssociated(false);
		packet.setSsid("");

		return packet;
	}
}
