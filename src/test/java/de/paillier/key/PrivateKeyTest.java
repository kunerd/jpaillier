package de.paillier.key;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

public class PrivateKeyTest {

	private PrivateKey privateKey;
	private BigInteger n = BigInteger.valueOf(13);
	private BigInteger nSquared = n.multiply(n);
	private BigInteger g = BigInteger.valueOf(2);

	private BigInteger lambda = BigInteger.valueOf(15);

	@Before
	public void init() {
		PublicKey mockedPublicKey = mock(PublicKey.class);
		when(mockedPublicKey.getN()).thenReturn(n);
		when(mockedPublicKey.getNSquare()).thenReturn(n.multiply(n));
		when(mockedPublicKey.getG()).thenReturn(g);

		privateKey = new PrivateKey(lambda, mockedPublicKey);
	}

	@Test
	public void testCalculationOfCachedValueX() {
		BigInteger expected = g.modPow(lambda, nSquared);
		expected = expected.subtract(BigInteger.ONE);
		expected = expected.divide(n);
		expected = expected.modInverse(n);

		assertEquals(expected, privateKey.getX());
	}

}
