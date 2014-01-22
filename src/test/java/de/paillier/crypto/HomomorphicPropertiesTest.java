package de.paillier.crypto;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import de.paillier.key.KeyPair;
import de.paillier.key.KeyPairBuilder;
import de.paillier.key.PublicKey;

public class HomomorphicPropertiesTest {

	private JPaillier paillier;
	private KeyPair keypair;
	PublicKey publicKey;

	@Before
	public void init() {
		KeyPairBuilder keygen = new KeyPairBuilder();
		
		keypair = keygen.generateKeyPair();

		paillier = new JPaillier(keypair);
		
		publicKey = paillier.getPublicKey();
	}

	@Test
	public void testHomomorphicAddition() {
		BigInteger plainA = BigInteger.valueOf(102);
		BigInteger plainB = BigInteger.valueOf(203);

		BigInteger encryptedA = paillier.encrypt(plainA);
		BigInteger encryptedB = paillier.encrypt(plainB);

		BigInteger decryptedProduct = paillier.decryption(encryptedA.multiply(
				encryptedB).mod(publicKey.getnSquared()));
		BigInteger plainSum = plainA.add(plainB).mod(publicKey.getN());

		assertEquals(decryptedProduct, plainSum);
	}

	@Test
	public void testHomomorphicConstantMultiplication() {
		BigInteger plainA = BigInteger.valueOf(14);
		BigInteger plainB = BigInteger.valueOf(203);

		BigInteger encryptedA = paillier.encrypt(plainA);

		BigInteger decryptedPow = paillier.decryption(encryptedA.modPow(plainB,
				publicKey.getnSquared()));
		BigInteger plainSum = plainA.multiply(plainB).mod(publicKey.getN());

		assertEquals(decryptedPow, plainSum);
	}

	@Test
	public void testHomomorphicMultiplication() {
		BigInteger plainA = BigInteger.valueOf(23);
		BigInteger plainB = BigInteger.valueOf(234);

		BigInteger encryptedA = paillier.encrypt(plainA);
		BigInteger decryptedPowA = paillier.decryption(encryptedA.modPow(
				plainB, publicKey.getnSquared()));
		BigInteger plainSumA = plainA.multiply(plainB).mod(publicKey.getN());

		assertEquals(decryptedPowA, plainSumA);

		BigInteger encryptedB = paillier.encrypt(plainB);
		BigInteger decryptedPowB = paillier.decryption(encryptedB.modPow(
				plainA, publicKey.getnSquared()));
		BigInteger plainSumB = plainA.multiply(plainB).mod(publicKey.getN());

		assertEquals(decryptedPowB, plainSumB);

		assertEquals(decryptedPowA, decryptedPowB);
	}

	@Test
	public void testHomomorphicMultiplicationPowG() {
		BigInteger plainA = BigInteger.valueOf(230);
		BigInteger plainB = BigInteger.valueOf(100);

		BigInteger g = publicKey.getG();

		BigInteger encryptedA = paillier.encrypt(plainA);
		BigInteger decryptedPow = paillier.decryption(encryptedA.multiply(g.modPow(
				plainB, publicKey.getnSquared()).mod(publicKey.getnSquared())));
		
		BigInteger plainSumA = plainA.add(plainB).mod(publicKey.getN());

		assertEquals(decryptedPow, plainSumA);
	}

}
