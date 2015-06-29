package de.henku.jpaillier;

import java.math.BigInteger;
import java.util.Random;

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
	
	public final BigInteger encrypt(BigInteger m) {
		
		BigInteger r;
		do {
			r = new BigInteger(bits, new Random());
		} while (r.compareTo(n) >= 0);

		BigInteger result = g.modPow(m, nSquared);
		BigInteger x = r.modPow(n, nSquared);

		result = result.multiply(x);
		result = result.mod(nSquared);
		
		return result;
	}
}
