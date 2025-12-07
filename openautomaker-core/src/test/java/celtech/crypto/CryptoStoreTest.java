package celtech.crypto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openautomaker.base.crypto.CryptoFileStore;
import org.openautomaker.environment.preference.application.HomePathPreference;
import org.openautomaker.test_library.GuiceExtension;

import jakarta.inject.Inject;

@ExtendWith(GuiceExtension.class)
public class CryptoStoreTest {

	private static final Logger LOGGER = LogManager.getLogger();

	@Inject
	HomePathPreference homePathPreference;

	/**
	 * Test of decrypt method, of class CryptoFileStore.
	 */
	@Test
	public void testEncryptDecryptIntegrity() throws Exception {
		LOGGER.info("Encrypt<->Decrypt integrity");

		Path cryptoStoreFilePath = homePathPreference.getUserValue().resolve("CryptoStoreTest.testEncryptDecryptIntegrity.dat");

		CryptoFileStore instance = new CryptoFileStore(cryptoStoreFilePath, "crazy pass phrase");

		String stringToEncrypt = "hello world!";

		String encryptedString = instance.encrypt(stringToEncrypt);
		String secondAttemptAtencryptedString = instance.encrypt(stringToEncrypt);
		String decryptedString = instance.decrypt(encryptedString);

		assertEquals(encryptedString, secondAttemptAtencryptedString);
		assertEquals(stringToEncrypt, decryptedString);
	}

	/**
	 * Test that two different filenames cause two different encryptions to take place
	 */
	@Test
	public void testEncryptDecryptUniqueness() throws Exception {
		LOGGER.info("Encrypt uniqueness");

		Path homePath = homePathPreference.getUserValue();

		CryptoFileStore firstCryptoStore = new CryptoFileStore(homePath.resolve("CryptoStoreTest.testEncryptDecryptUniqueness.0.dat"), "crazy pass phrase");
		CryptoFileStore secondCryptoStore = new CryptoFileStore(homePath.resolve("CryptoStoreTest.testEncryptDecryptUniqueness.0.dat"), "another crazy pass phrase");

		String stringToEncrypt = "hello world!";

		String firstEncryption = firstCryptoStore.encrypt(stringToEncrypt);
		String secondEncryption = secondCryptoStore.encrypt(stringToEncrypt);
		String firstDecryptedString = firstCryptoStore.decrypt(firstEncryption);

		LOGGER.info("Expect BadPaddingException");
		String secondDecryptedString = "bad string";
		secondDecryptedString = secondCryptoStore.decrypt(firstEncryption);

		assertNotSame(firstEncryption, secondEncryption);
		assertEquals(stringToEncrypt, firstDecryptedString);
		assertNotSame(stringToEncrypt, secondDecryptedString);
	}
}
