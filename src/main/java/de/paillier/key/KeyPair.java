package de.paillier.key;

import java.math.BigInteger;
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
		BigInteger pMinusOne = p.subtract(BigInteger.ONE);
		BigInteger qMinusOne = q.subtract(BigInteger.ONE);
		
		BigInteger lambda  = this.lcm(pMinusOne, qMinusOne);
        
        this.privateKey = new PrivateKey(lambda, this.publicKey);
	}
	
	/* TODO add to own BigInteger extended class*/
	private BigInteger lcm(BigInteger a, BigInteger b)
	{
		BigInteger result;
		BigInteger gcd = a.gcd(b);
		
		result = a.abs().divide(gcd);
		result = result.multiply(b.abs());
		
		return result;
	}
	
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	
	public PublicKey getPublicKey() {
		return publicKey;
	}
}
