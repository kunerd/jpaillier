package de.paillier.key;

import java.math.BigInteger;

public class PrivateKey {
	
	private BigInteger lambda;
	private BigInteger x;
	
	public PrivateKey(BigInteger lambda, PublicKey publicKey) {
		this.lambda = lambda;
		
		this.x = publicKey.getG().modPow(lambda, publicKey.getNSquare());
		this.x = this.x.subtract(BigInteger.ONE);
		this.x = this.x.divide(publicKey.getN());
		this.x = this.x.modInverse(publicKey.getN());
	}
	
	public BigInteger getLambda() {
		return lambda;
	}
	
	public BigInteger getX() {
		return x;
	}
}
