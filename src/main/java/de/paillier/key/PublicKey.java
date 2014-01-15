package de.paillier.key;

import java.math.BigInteger;

public class PublicKey {
	private int bits;
	private BigInteger n;
	private BigInteger nSquare;
	private BigInteger g;

	public PublicKey(BigInteger n, BigInteger g, int bits) {
		this.n = n;
		this.nSquare = n.multiply(n);
		this.bits = bits;
		this.g = g;
	}

	public int getBits() {
		return bits;
	}

	public BigInteger getN() {
		return n;
	}

	public BigInteger getNSquare() {
		return nSquare;
	}

	public BigInteger getG() {
		return g;
	}
}
