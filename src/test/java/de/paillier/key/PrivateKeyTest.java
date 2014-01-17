package de.paillier.key;

import static org.junit.Assert.*;

import java.math.BigInteger;

import javax.naming.InitialContext;

import org.junit.Before;
import org.junit.Test;

public class PrivateKeyTest {
	
	private PrivateKey privateKey;
	private BigInteger n = BigInteger.valueOf(10);
	private BigInteger g = BigInteger.valueOf(2);
	
	private BigInteger lambda = BigInteger.valueOf(15);

	@Before
	public void init() {
//		PublicKey mockedPublicKey = mock(PublicKey.class);
//		when(mockedPublicKey.getN()).thenReturn(n);
//		when(mockedPublicKey.getNSquare()).thenReturn(n.multiply(n));
//		when(mockedPublicKey.getG()).thenReturn(g);

//		privateKey = new PrivateKey(lambda, mockedPublicKey);
	}

	@Test
	public void testCalculationOfCachedValueX() {

		}

}
