package de.paillier.crypto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import de.paillier.key.KeyPair;
import de.paillier.key.KeyPairBuilder;

public class JPaillierTest {
	
	private KeyPair keyPair;
	
	@Before
	public void init() {
		KeyPairBuilder keygen = new KeyPairBuilder();
		this.keyPair = keygen.generateKeyPair();		
	}

	@Test
	public void testEncryption() {
		BigInteger plainData = BigInteger.valueOf(10);
		BigInteger encryptedData = JPaillier.encrypt(plainData, keyPair.getPublicKey());
		
		assertNotEquals(plainData, encryptedData);
	}

	@Test
	public void testDecyption() {
		BigInteger plainData = BigInteger.valueOf(10);
		
		BigInteger encryptedData = JPaillier.encrypt(plainData, keyPair.getPublicKey());
		BigInteger decryptedData = JPaillier.decrypt(encryptedData, keyPair);
		
		assertEquals(plainData, decryptedData);
	}
	
}
