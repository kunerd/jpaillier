package de.henku.jpaillier;

import java.math.BigInteger;

/**
 * A class that holds a pair of associated public and private keys.
 */
public class KeyPair {

	private final PrivateKey privateKey;
	private final PublicKey publicKey;
	private final BigInteger upperBound;
	
	KeyPair(PrivateKey privateKey, PublicKey publicKey, BigInteger upperBound) {
		this.privateKey = privateKey;
		this.publicKey = publicKey;
		this.upperBound = upperBound;
	}
	
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	
	public PublicKey getPublicKey() {
		return publicKey;
	}
	
	/**
	 * Decrypts the given ciphertext.
	 * 
	 * @param c The ciphertext that should be decrypted.
	 * @return The corresponding plaintext. If an upper bound was given to {@link KeyPairBuilder},
	 * the result can also be negative. See {@link KeyPairBuilder#upperBound(BigInteger)} for details.
	 */
    public final BigInteger decrypt(BigInteger c) {
		
		BigInteger n = publicKey.getN();
		BigInteger nSquare = publicKey.getnSquared();
		BigInteger lambda = privateKey.getLambda();
		
        BigInteger u = privateKey.getPreCalculatedDenominator();

		BigInteger p = c.modPow(lambda, nSquare).subtract(BigInteger.ONE).divide(n).multiply(u).mod(n);

		if (upperBound != null && p.compareTo(upperBound) > 0) {
			p = p.subtract(n);
		}

		return p;
    }
}
