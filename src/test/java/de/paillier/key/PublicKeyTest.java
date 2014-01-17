package de.paillier.key;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

public class PublicKeyTest {

	private BigInteger n = BigInteger.valueOf(10);
	private BigInteger g = BigInteger.valueOf(2);
	private int bits = 256;
	
	private PublicKey publicKey;
	
	@Before
	public void init() {
		publicKey = new PublicKey(n, g, bits);
	}
	
	@Test
	public void testCalculationOfNSquared() {
		BigInteger expected = n.multiply(n);
		
		assertEquals(expected, publicKey.getNSquare());
	}

}
