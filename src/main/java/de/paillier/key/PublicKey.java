package de.paillier.key;

import java.math.BigInteger;

public class PublicKey {
	private int bits;
	private BigInteger n;
	private BigInteger nSquared;
	private BigInteger g;

	public PublicKey(BigInteger n, BigInteger nSquared, BigInteger g, int bits) {
		this.n = n;
		this.nSquared = nSquared;
		this.bits = bits;
		this.g = g;
	}

	public int getBits() {
		return bits;
	}

	public BigInteger getN() {
		return n;
	}
	
	public BigInteger getnSquared() {
		return nSquared;
	}

	public BigInteger getG() {
		return g;
	}
}
