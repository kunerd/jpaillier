package de.paillier.key;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

public class KeyPairBuilderPrivateKeyTest {

	private KeyPairBuilder keygen;
	private KeyPair keypair;
	private PrivateKey privateKey;

	@Before
	public void init() {
		this.keygen = new KeyPairBuilder();
		this.keypair = keygen.generateKeyPair();
		this.privateKey = keypair.getPrivateKey();
	}
	
	@Test
	public void testPreCalculatedDenominator() {
		PublicKey publicKey = keypair.getPublicKey();
		
		BigInteger preCalculatedDenominator = privateKey.getPreCalculatedDenominator();
		
		BigInteger g = publicKey.getG();
		BigInteger n = publicKey.getN();
		BigInteger nSquared = publicKey.getnSquared();
		BigInteger lambda = privateKey.getLambda();
		
		BigInteger expected = g.modPow(lambda, nSquared);
		expected = expected.subtract(BigInteger.ONE);
		expected = expected.divide(n);
		expected = expected.modInverse(n);
		
		assertEquals(expected, preCalculatedDenominator);
	}

}
