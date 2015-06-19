package de.paillier;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(KeyPairBuilder.class)
public class KeyPairBuilderTest {
	
	private KeyPairBuilder keygen;
	private KeyPair keypair;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	
    BigInteger p = BigInteger.valueOf(5);
    BigInteger q = BigInteger.valueOf(7);
    BigInteger g1 = BigInteger.valueOf(35);
    BigInteger g2 = BigInteger.valueOf(36);
	
	@Before
	public void init() throws Exception {
		int bits = 128;
		Random rng = PowerMockito.mock(SecureRandom.class);

		this.keygen = new KeyPairBuilder().setBits(bits)
				.setRandomNumberGenerator(rng);
		
        PowerMockito.mockStatic(BigInteger.class);
        
        PowerMockito.when(BigInteger.probablePrime(bits / 2, rng)).thenReturn(p, q);
        
        PowerMockito.whenNew(BigInteger.class).withAnyArguments().thenReturn(g1, g2);
               		
		keypair = keygen.generateKeyPair();
		
		publicKey = keypair.getPublicKey();
		privateKey = keypair.getPrivateKey();
	}

	@Test
	public void testGenerateKeyPairReturnsKeyPair() {
		assertNotNull(keypair);
	}
	
	@Test
	public void testGeneratedKeyPairContainsPublicKey() {
		assertNotNull(keypair.getPublicKey());
	}
	
	@Test
	public void testGeneratedKeyPairContainsPrivateKey() {
		assertNotNull(keypair.getPrivateKey());
	}
	
	@Test 
	public void computationOfN() {
		BigInteger e = p.multiply(q);
		BigInteger a = publicKey.getN();
		
		assertEquals(e, a);
	}
	
//	@Test
//	public void computationOfLambda() {
//		BigInteger e = new BigInteger("12");
//		BigInteger a = privateKey.getLambda();
//		
//		assertEquals(e, a);
//	}
	
	@Test
	public void computationOfG() throws Exception {
        PowerMockito.verifyNew(BigInteger.class, Mockito.times(2)).withArguments(Mockito.eq(128), Mockito.any(Random.class));
	}
	
}
