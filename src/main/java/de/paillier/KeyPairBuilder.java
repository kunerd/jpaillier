package de.paillier;

import java.math.BigInteger;
import java.security.SecureRandom;

public class KeyPairBuilder {
	private int bits = 1024;
	private int certainty = 64;
	
	public void setBits(int bits) {
		this.bits = bits;
	}

	public KeyPair generateKeyPair() {
		SecureRandom sRandom = new SecureRandom();
		BigInteger p = new BigInteger(bits / 2, certainty, sRandom);
		BigInteger q = new BigInteger(bits / 2, certainty, sRandom);
		
		BigInteger n = p.multiply(q);
		BigInteger nSquared = n.multiply(n);
		
		BigInteger pMinusOne = p.subtract(BigInteger.ONE);
		BigInteger qMinusOne = q.subtract(BigInteger.ONE);
		BigInteger lambda  = this.lcm(pMinusOne, qMinusOne);
		
		BigInteger g = BigInteger.valueOf(2);
		BigInteger helper; 
		do {
			g = new BigInteger(bits, sRandom);
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
