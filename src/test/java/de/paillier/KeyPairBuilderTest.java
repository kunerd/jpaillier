package de.paillier;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import de.paillier.KeyPair;
import de.paillier.KeyPairBuilder;

public class KeyPairBuilderTest {
	
	private KeyPairBuilder keygen;
	KeyPair keypair;
	
	@Before
	public void init() {
		this.keygen = new KeyPairBuilder();
		this.keypair = keygen.generateKeyPair();
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
	
}
