package org.openautomaker.base.comms.tx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import celtech.roboxbase.comms.tx.AbortPrint;
import celtech.roboxbase.comms.tx.PausePrint;
import celtech.roboxbase.comms.tx.RoboxTxPacket;

public class RoboxTxPacketTest {

	private final ObjectMapper mapper = new ObjectMapper();

	/**
	 * Test of getPacketType method, of class RoboxTxPacket.
	 */
	@Test
	public void testJSONifyAbortPrint() {
		int sequenceNumber = 333;
		String message = "Some data";

		RoboxTxPacket packet = new AbortPrint();
		packet.setIncludeSequenceNumber(true);
		packet.setSequenceNumber(sequenceNumber);
		packet.setIncludeCharsOfDataInOutput(true);
		packet.setMessagePayload(message);

		try {
			//            mapper.enableDefaultTyping();
			String jsonifiedString = mapper.writeValueAsString(packet);
			RoboxTxPacket dejson = mapper.readValue(jsonifiedString, RoboxTxPacket.class);
			assertEquals(packet, dejson);
		}
		catch (IOException ex) {
			fail("IO Exception whilst jsonifying");
		}
	}

	/**
	 * Test of getPacketType method, of class RoboxTxPacket.
	 */
	@Test
	public void testJSONifyPausePrint() {
		int sequenceNumber = 333;
		String message = "Some data";

		PausePrint packet = new PausePrint();
		packet.setIncludeSequenceNumber(true);
		packet.setSequenceNumber(sequenceNumber);
		packet.setIncludeCharsOfDataInOutput(true);
		packet.setMessagePayload(message);
		packet.setPause();

		try {
			String jsonifiedString = mapper.writeValueAsString(packet);
			RoboxTxPacket dejson = mapper.readValue(jsonifiedString, RoboxTxPacket.class);
			assertEquals(packet, dejson);
			assertTrue(dejson instanceof PausePrint);
			assertEquals("1", dejson.getMessagePayload());
		}
		catch (IOException ex) {
			fail("IO Exception whilst jsonifying");
		}
	}
}
