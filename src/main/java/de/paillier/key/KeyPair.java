package de.paillier.key;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class KeyPair {

	private PrivateKey privateKey;
	private PublicKey publicKey;
	
	public KeyPair(int bits, int certainty) {
		BigInteger p = new BigInteger(bits / 2, certainty, new Random());
		BigInteger q = new BigInteger(bits / 2, certainty, new Random());
		
		BigInteger n = p.multiply(q);
		
		// TODO calculate in Z*_{n^2} where gcd (L(g^lambda mod n^2), n) = 1
		BigInteger g = new BigInteger("2");
		
		this.publicKey = new PublicKey(n, g,  bits);

		/* compute the private key lambda = lcm(p-1,q-1) */
		BigInteger lambda;
		
		BigInteger pMinusOne = p.subtract(BigInteger.ONE);
		BigInteger qMinusOne = q.subtract(BigInteger.ONE);
		
        lambda = pMinusOne.multiply(qMinusOne);
        lambda = lambda.divide(pMinusOne.gcd(qMinusOne));
        
        this.privateKey = new PrivateKey(lambda, this.publicKey);
	}
}
