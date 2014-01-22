package de.paillier.crypto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import de.paillier.key.KeyPair;
import de.paillier.key.KeyPairBuilder;

public class JPaillierTest {
	
	private JPaillier paillier;
	
	@Before
	public void init() {
		KeyPairBuilder keygen = new KeyPairBuilder();
		KeyPair keypair = keygen.generateKeyPair();
		
		paillier = new JPaillier(keypair);
	}

	@Test
	public void testEncryption() {
		BigInteger plainData = BigInteger.valueOf(10);
		BigInteger encryptedData = paillier.encrypt(plainData);
		
		assertNotEquals(plainData, encryptedData);
	}

	@Test
	public void testDecyption() {
		BigInteger plainData = BigInteger.valueOf(10);
		
		BigInteger encryptedData = paillier.encrypt(plainData);
		BigInteger decryptedData = paillier.decryption(encryptedData);
		
		assertEquals(plainData, decryptedData);
	}
	
}
