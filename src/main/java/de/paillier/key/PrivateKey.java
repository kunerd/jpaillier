package de.paillier.key;

import java.math.BigInteger;

public class PrivateKey {
	
	private BigInteger lambda;
	private BigInteger preCalculatedDenominator;
	
	public PrivateKey(BigInteger lambda, BigInteger preCalculatedDenominator) {
		this.lambda = lambda;
		
		this.preCalculatedDenominator = preCalculatedDenominator;
	}
	
	public BigInteger getLambda() {
		return lambda;
	}
	
	public BigInteger getX() {
		return preCalculatedDenominator;
	}
}
