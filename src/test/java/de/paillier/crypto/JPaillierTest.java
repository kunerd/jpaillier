package de.paillier.crypto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import de.paillier.key.KeyPair;
import de.paillier.key.KeyPairBuilder;
import de.paillier.key.PublicKey;

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
	
	@Test
	public void testHomomorphicAddition() {
		BigInteger plainA = BigInteger.valueOf(14);
		BigInteger plainB = BigInteger.valueOf(203);
		
		BigInteger encryptedA = paillier.encrypt(plainA);
		BigInteger encryptedB = paillier.encrypt(plainB);
		
		PublicKey publicKey = this.paillier.getPublicKey();
		BigInteger decryptedProduct = paillier.decryption(encryptedA.multiply(encryptedB).mod(publicKey.getnSquared()));
		BigInteger plainSum = plainA.add(plainB).mod(publicKey.getN());
		
		assertEquals(decryptedProduct, plainSum);
	}
	
	@Test
	public void testHomomorphicMultiplication () {
		BigInteger plainA = BigInteger.valueOf(14);
		BigInteger plainB = BigInteger.valueOf(203);
		
		BigInteger encryptedA = paillier.encrypt(plainA);
		
		PublicKey publicKey = this.paillier.getPublicKey();
		BigInteger decryptedPow = paillier.decryption(encryptedA.modPow(plainB, publicKey.getnSquared()));
		BigInteger plainSum = plainA.multiply(plainB).mod(publicKey.getN());
		
		assertEquals(decryptedPow, plainSum);
	}
}
