package de.paillier.key;

import java.io.ObjectInputStream.GetField;
import java.math.BigInteger;

public class PublicKey {
	private int bits;
	private BigInteger n;
	private BigInteger nSquare;
	private BigInteger nPlusOne;
	private BigInteger g;
	
	public PublicKey(BigInteger n, BigInteger g, int bits) {
		this.n = n;
		this.nSquare = n.multiply(n);
		this.nPlusOne = n.add(BigInteger.valueOf(1));
	}
	
	public BigInteger getN() {
		return n;
	}
	
	public BigInteger getNSquare() {
		return nSquare;
	}
	
	public BigInteger getNPlusOne() {
		return nPlusOne;
	}
}
