package celtech.roboxbase.comms.remote.rx;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;

import celtech.roboxbase.comms.rx.FirmwareResponse;
import celtech.roboxbase.comms.rx.RoboxRxPacketFactory;
import celtech.roboxbase.comms.rx.RxPacketTypeEnum;

/**
 *
 * @author Ian
 */
public class FirmwareResponseTest {

	/**
	 * Test of getFirmwareRevision method, of class FirmwareResponse.
	 */
	@Test
	public void testPalindrome() {
		System.out.println("testPalindrom");
		FirmwareResponse instance = (FirmwareResponse) RoboxRxPacketFactory.createPacket(RxPacketTypeEnum.FIRMWARE_RESPONSE);
		String inputString = "01234567";
		byte[] inputBytes = new byte[inputString.length() + 1];
		inputBytes[0] = RxPacketTypeEnum.FIRMWARE_RESPONSE.getCommandByte();
		byte[] stringBytes = inputString.getBytes(Charset.forName("US-ASCII"));
		for (int i = 0; i < stringBytes.length; i++) {
			inputBytes[i + 1] = stringBytes[i];
		}
		instance.populatePacket(inputBytes, 741f);

		assertEquals(inputString, instance.getFirmwareRevision());
		assertEquals(1234567f, instance.getFirmwareRevisionFloat(), 0.0);
		assertEquals(inputString, instance.getMessagePayload());

		byte[] outputData = instance.toByteArray();

		assertArrayEquals(inputBytes, outputData);

		System.out.println(instance.getMessagePayload());
	}

}
