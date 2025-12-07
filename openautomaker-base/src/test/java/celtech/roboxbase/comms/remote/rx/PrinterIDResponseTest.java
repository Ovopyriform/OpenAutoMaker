package celtech.roboxbase.comms.remote.rx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import celtech.roboxbase.comms.rx.PrinterIDResponse;
import celtech.roboxbase.comms.rx.RoboxRxPacket;
import javafx.scene.paint.Color;

/**
 *
 * @author ianhudson
 */
public class PrinterIDResponseTest {


	private static final String jsonifiedClass = "{\"@class\":\"celtech.roboxbase.comms.rx.PrinterIDResponse\",\"packetType\":\"PRINTER_ID_RESPONSE\",\"messagePayload\":null,\"sequenceNumber\":44,\"includeSequenceNumber\":false,\"includeCharsOfDataInOutput\":false,\"model\":\"RBX01\",\"edition\":\"KS\",\"weekOfManufacture\":null,\"yearOfManufacture\":null,\"poNumber\":null,\"serialNumber\":null,\"checkByte\":null,\"electronicsVersion\":null,\"printerFriendlyName\":null,\"printerColour\":\"0xf0f8ffff\"}";
	private static String testColourString = Color.ALICEBLUE.toString();

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void serializesToJSON() throws Exception {
		final PrinterIDResponse packet = getTestPacket();

		String mappedValue = mapper.writeValueAsString(packet);

		assertEquals(mapper.readTree(jsonifiedClass), mapper.readTree(mappedValue));
	}

	@Test
	public void deserializesFromJSON() throws Exception {
		final PrinterIDResponse packet = getTestPacket();

		try {
			RoboxRxPacket packetRec = mapper.readValue(jsonifiedClass, RoboxRxPacket.class);
			assertEquals(packet, packetRec);
		}
		catch (Exception e) {
			System.out.println(e.getCause().getMessage());
			fail();
		}
	}

	private PrinterIDResponse getTestPacket() {
		PrinterIDResponse packet = new PrinterIDResponse();

		packet.setSequenceNumber(44);
		packet.setEdition("KS");
		packet.setModel("RBX01");
		packet.setPrinterColour(testColourString);

		return packet;
	}

}
