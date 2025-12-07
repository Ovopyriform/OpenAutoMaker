package celtech.roboxbase.comms.remote.rx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import celtech.roboxbase.comms.rx.AckResponse;
import celtech.roboxbase.comms.rx.FirmwareError;
import celtech.roboxbase.comms.rx.RoboxRxPacket;

public class AckResponseTest {

	private static final String jsonifiedClass = "{\"@class\":\"celtech.roboxbase.comms.rx.AckResponse\",\"packetType\":\"ACK_WITH_ERRORS\",\"messagePayload\":null,\"sequenceNumber\":44,\"includeSequenceNumber\":false,\"includeCharsOfDataInOutput\":false,\"firmwareErrors\":[\"USB_RX\"]}";

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void serializesToJSON() throws Exception {
		final AckResponse packet = getTestPacket();

		String mappedValue = mapper.writeValueAsString(packet);
		assertEquals(mapper.readTree(jsonifiedClass), mapper.readTree(mappedValue));
	}

	@Test
	public void deserializesFromJSON() throws Exception {
		final AckResponse packet = getTestPacket();

		try {
			RoboxRxPacket packetRec = mapper.readValue(jsonifiedClass, RoboxRxPacket.class);
			assertEquals(packet, packetRec);
		}
		catch (Exception e) {
			System.out.println(e.getCause().getMessage());
			fail();
		}
	}

	private AckResponse getTestPacket() {
		AckResponse packet = new AckResponse();

		packet.setSequenceNumber(44);
		List<FirmwareError> firmwareErrors = new ArrayList<>();
		firmwareErrors.add(FirmwareError.USB_RX);
		packet.setFirmwareErrors(firmwareErrors);

		return packet;
	}
}
