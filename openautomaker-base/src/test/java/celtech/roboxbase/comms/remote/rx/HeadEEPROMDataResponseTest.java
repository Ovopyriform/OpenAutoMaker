package celtech.roboxbase.comms.remote.rx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import celtech.roboxbase.comms.rx.HeadEEPROMDataResponse;
import celtech.roboxbase.comms.rx.RoboxRxPacket;

/**
 *
 * @author ianhudson
 */
public class HeadEEPROMDataResponseTest {

	private static final String jsonifiedClass = "{\"@class\":\"celtech.roboxbase.comms.rx.HeadEEPROMDataResponse\",\"packetType\":\"HEAD_EEPROM_DATA\",\"messagePayload\":null,\"sequenceNumber\":44,\"includeSequenceNumber\":false,\"includeCharsOfDataInOutput\":false,\"headEEPROMData\":{\"headTypeCode\":null,\"uniqueID\":\"aaddbb\",\"weekNumber\":\"\",\"yearNumber\":\"\",\"serialNumber\":\"\",\"checksum\":\"\",\"maximumTemperature\":4.5,\"thermistorBeta\":0.0,\"thermistorTCal\":0.0,\"nozzle1XOffset\":0.0,\"nozzle1YOffset\":0.0,\"nozzle1ZOffset\":0.0,\"nozzle1BOffset\":0.0,\"filament0ID\":\"Hello\",\"filament1ID\":\"\",\"nozzle2XOffset\":0.0,\"nozzle2YOffset\":0.0,\"nozzle2ZOffset\":0.0,\"nozzle2BOffset\":0.0,\"lastFilamentTemperature0\":0.0,\"lastFilamentTemperature1\":0.0,\"headHours\":0.0,\"ponumber\":\"\"}}";

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void serializesToJSON() throws Exception {
		final HeadEEPROMDataResponse packet = getTestPacket();

		String mappedValue = mapper.writeValueAsString(packet);
		assertEquals(mapper.readTree(jsonifiedClass), mapper.readTree(mappedValue));
	}

	@Test
	public void deserializesFromJSON() throws Exception {
		final HeadEEPROMDataResponse packet = getTestPacket();

		try {
			RoboxRxPacket packetRec = mapper.readValue(jsonifiedClass, RoboxRxPacket.class);
			assertEquals(packet, packetRec);
		}
		catch (Exception e) {
			System.out.println(e.getCause().getMessage());
			fail();
		}
	}

	private HeadEEPROMDataResponse getTestPacket() {
		HeadEEPROMDataResponse packet = new HeadEEPROMDataResponse();

		packet.setSequenceNumber(44);
		packet.setFilament0ID("Hello");
		packet.setMaximumTemperature(4.5f);
		packet.setUniqueID("aaddbb");

		return packet;
	}
}
