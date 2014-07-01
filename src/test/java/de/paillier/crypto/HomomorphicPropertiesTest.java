package de.paillier.crypto;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import de.paillier.key.KeyPair;
import de.paillier.key.KeyPairBuilder;
import de.paillier.key.PublicKey;

public class HomomorphicPropertiesTest {

	private KeyPair keypair;
	private PublicKey publicKey;

	@Before
	public void init() {
		KeyPairBuilder keygen = new KeyPairBuilder();
		this.keypair = keygen.generateKeyPair();
		this.publicKey = keypair.getPublicKey();
	}

	@Test
	public void testHomomorphicAddition() {
		BigInteger plainA = BigInteger.valueOf(102);
		BigInteger plainB = BigInteger.valueOf(203);

		BigInteger encryptedA = JPaillier.encrypt(plainA, publicKey);
		BigInteger encryptedB = JPaillier.encrypt(plainB, publicKey);

		BigInteger decryptedProduct = JPaillier.decrypt(encryptedA.multiply(
				encryptedB).mod(publicKey.getnSquared()), keypair);
		BigInteger plainSum = plainA.add(plainB).mod(publicKey.getN());

		assertEquals(decryptedProduct, plainSum);
	}

	@Test
	public void testHomomorphicConstantMultiplication() {
		BigInteger plainA = BigInteger.valueOf(14);
		BigInteger plainB = BigInteger.valueOf(203);

		BigInteger encryptedA = JPaillier.encrypt(plainA, publicKey);

		BigInteger decryptedPow = JPaillier.decrypt(encryptedA.modPow(plainB,
				publicKey.getnSquared()), keypair);
		BigInteger plainSum = plainA.multiply(plainB).mod(publicKey.getN());

		assertEquals(decryptedPow, plainSum);
	}

	@Test
	public void testHomomorphicMultiplication() {
		BigInteger plainA = BigInteger.valueOf(23);
		BigInteger plainB = BigInteger.valueOf(234);

		BigInteger encryptedA = JPaillier.encrypt(plainA, publicKey);
		BigInteger decryptedPowA = JPaillier.decrypt(encryptedA.modPow(
				plainB, publicKey.getnSquared()), keypair);
		BigInteger plainSumA = plainA.multiply(plainB).mod(publicKey.getN());

		assertEquals(decryptedPowA, plainSumA);

		BigInteger encryptedB = JPaillier.encrypt(plainB, publicKey);
		BigInteger decryptedPowB = JPaillier.decrypt(encryptedB.modPow(
				plainA, publicKey.getnSquared()), keypair);
		BigInteger plainSumB = plainA.multiply(plainB).mod(publicKey.getN());

		assertEquals(decryptedPowB, plainSumB);

		assertEquals(decryptedPowA, decryptedPowB);
	}

	@Test
	public void testHomomorphicMultiplicationPowG() {
		BigInteger plainA = BigInteger.valueOf(230);
		BigInteger plainB = BigInteger.valueOf(100);

		BigInteger g = publicKey.getG();

		BigInteger encryptedA = JPaillier.encrypt(plainA, publicKey);
		BigInteger decryptedPow = JPaillier.decrypt(encryptedA.multiply(g.modPow(
				plainB, publicKey.getnSquared()).mod(publicKey.getnSquared())), keypair);
		
		BigInteger plainSumA = plainA.add(plainB).mod(publicKey.getN());

		assertEquals(decryptedPow, plainSumA);
	}

}
