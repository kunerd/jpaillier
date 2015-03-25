package de.paillier;

import java.math.BigInteger;


public class KeyPair {

	private PrivateKey privateKey;
	private PublicKey publicKey;
	
	public KeyPair(PrivateKey privateKey, PublicKey publicKey) {
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}
	
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	
	public PublicKey getPublicKey() {
		return publicKey;
	}
	
    public final BigInteger decrypt(BigInteger c) {
		
		BigInteger n = publicKey.getN();
		BigInteger nSquare = publicKey.getnSquared();
		BigInteger lambda = privateKey.getLambda();
		
        BigInteger u = privateKey.getPreCalculatedDenominator();
        return c.modPow(lambda, nSquare).subtract(BigInteger.ONE).divide(n).multiply(u).mod(n);
    }
}
