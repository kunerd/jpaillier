package de.paillier;

import java.math.BigInteger;
import java.util.Random;

public class KeyPairBuilder {
	private int bits = 1024;
	private int certainty = 64;
	private Random rng;
	
	public KeyPairBuilder setBits(int bits) {
		this.bits = bits;
		return this;
	}
	
	public KeyPairBuilder setCertainty(int certainty) {
		this.certainty = certainty;
		return this;
	}
	
	public KeyPairBuilder setRandomNumberGenerator(Random rng) {
		this.rng = rng;
		return this;
	}

	public KeyPair generateKeyPair() {
//		BigInteger p = new BigInteger(bits / 2, certainty, rng);
//		BigInteger q = new BigInteger(bits / 2, certainty, rng);
		BigInteger p = BigInteger.probablePrime(bits / 2, rng);
		BigInteger q = BigInteger.probablePrime(bits / 2, rng);
		
		BigInteger n = p.multiply(q);
		BigInteger nSquared = n.multiply(n);
		
		BigInteger pMinusOne = p.subtract(BigInteger.ONE);
		BigInteger qMinusOne = q.subtract(BigInteger.ONE);
		BigInteger lambda  = this.lcm(pMinusOne, qMinusOne);
		
		BigInteger g;
		BigInteger helper; 
		
		do {
			g = new BigInteger(bits, rng);
			helper = calculateL(g.modPow(lambda, nSquared), n);

		} while (!helper.gcd(n).equals(BigInteger.ONE));
		
		PublicKey publicKey = new PublicKey(n, nSquared, g, bits);
		PrivateKey privateKey = new PrivateKey(lambda, helper.modInverse(n));
		
		KeyPair result = new KeyPair(privateKey, publicKey);
		
		return result;
	}
	
	// TODO separate this somewhere
	private BigInteger calculateL(BigInteger u, BigInteger n) {
		BigInteger result = u.subtract(BigInteger.ONE);
		result = result.divide(n);
		return result;
	}
	
	// TODO add to own BigInteger extended class
	private BigInteger lcm(BigInteger a, BigInteger b)
	{
		BigInteger result;
		BigInteger gcd = a.gcd(b);
		
		result = a.abs().divide(gcd);
		result = result.multiply(b.abs());
		
		return result;
	}
}
