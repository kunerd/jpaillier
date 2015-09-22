package de.henku.jpaillier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import de.henku.jpaillier.KeyPair;
import de.henku.jpaillier.KeyPairBuilder;
import de.henku.jpaillier.PublicKey;

public class JPaillierTest {
	
	private KeyPair keyPair;
	PublicKey publicKey;
	
	@Before
	public void init() {
		KeyPairBuilder keygen = new KeyPairBuilder();
		keyPair = keygen.generateKeyPair();
		publicKey = keyPair.getPublicKey();
	}

	@Test
	public void testEncryption() {
		BigInteger plainData = BigInteger.valueOf(10);
		
		BigInteger encryptedData = publicKey.encrypt(plainData);
		
		assertNotEquals(plainData, encryptedData);
	}

	@Test
	public void testDecyption() {
		BigInteger plainData = BigInteger.valueOf(10);

		BigInteger encryptedData = publicKey.encrypt(plainData);
		BigInteger decryptedData = keyPair.decrypt(encryptedData);

		assertEquals(plainData, decryptedData);
	}
}
