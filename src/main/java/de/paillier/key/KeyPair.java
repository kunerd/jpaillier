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
		
		BigInteger pMinusOne = p.subtract(BigInteger.ONE);
		BigInteger qMinusOne = q.subtract(BigInteger.ONE);
		BigInteger lambda  = this.lcm(pMinusOne, qMinusOne);
		
		BigInteger g = selectG(bits, lambda, n);
		
		this.publicKey = new PublicKey(n, g,  bits);
        
        this.privateKey = new PrivateKey(lambda, this.publicKey);
	}
	
	private BigInteger selectG(int bits, BigInteger lambda, BigInteger n) {
		BigInteger g;
		do {
			g = new BigInteger(bits, new Random());
		} while (checkIfGIsGood(g, lambda, n));
		
		return g;
	}
	
	private boolean checkIfGIsGood(BigInteger g, BigInteger lambda, BigInteger n) {
		BigInteger temp = g.modPow(lambda, n.multiply(n));
		temp= temp.subtract(BigInteger.ONE);
		temp = temp.divide(n);
		
		return temp.gcd(n) == BigInteger.ONE;
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
