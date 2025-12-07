package celtech.roboxbase.comms.remote.rx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.openautomaker.base.MaterialType;

import com.fasterxml.jackson.databind.ObjectMapper;

import celtech.roboxbase.comms.rx.ReelEEPROM1DataResponse;
import celtech.roboxbase.comms.rx.RoboxRxPacket;

/**
 *
 * @author ianhudson
 */
public class ReelEEPROM1DataResponseTest {


	private static final String jsonifiedClass = "{\"@class\":\"celtech.roboxbase.comms.rx.ReelEEPROM1DataResponse\",\"packetType\":\"REEL_1_EEPROM_DATA\",\"messagePayload\":null,\"sequenceNumber\":44,\"includeSequenceNumber\":false,\"includeCharsOfDataInOutput\":false,\"filamentID\":null,\"firstLayerNozzleTemperature\":0,\"nozzleTemperature\":0,\"firstLayerBedTemperature\":0,\"bedTemperature\":48,\"ambientTemperature\":0,\"filamentDiameter\":0.0,\"filamentMultiplier\":0.0,\"feedRateMultiplier\":0.0,\"remainingFilament\":0.0,\"displayColourString\":null,\"friendlyName\":null,\"reelNumber\":0,\"materialType\":\"PTG\"}";

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void serializesToJSON() throws Exception {
		final ReelEEPROM1DataResponse packet = getTestPacket();

		String mappedValue = mapper.writeValueAsString(packet);

		assertEquals(mapper.readTree(jsonifiedClass), mapper.readTree(mappedValue));
	}

	@Test
	public void deserializesFromJSON() throws Exception {
		final ReelEEPROM1DataResponse packet = getTestPacket();

		try {
			RoboxRxPacket packetRec = mapper.readValue(jsonifiedClass, RoboxRxPacket.class);
			assertEquals(packet, packetRec);
		}
		catch (Exception e) {
			System.out.println(e.getCause().getMessage());
			fail();
		}
	}

	private ReelEEPROM1DataResponse getTestPacket() {
		ReelEEPROM1DataResponse packet = new ReelEEPROM1DataResponse();

		packet.setSequenceNumber(44);
		packet.setBedTemperature(48);
		packet.setMaterialType(MaterialType.PTG);

		return packet;
	}
}
