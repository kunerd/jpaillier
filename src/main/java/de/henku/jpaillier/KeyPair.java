package de.henku.jpaillier;

import java.math.BigInteger;

/**
 * A class that holds a pair of associated public and private keys.
 */
public class KeyPair {

	private final PrivateKey privateKey;
	private final PublicKey publicKey;
	
	KeyPair(PrivateKey privateKey, PublicKey publicKey) {
		this.privateKey = privateKey;
		this.publicKey = publicKey;
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
	 * @return The corresponding plaintext.
	 */
    public final BigInteger decrypt(BigInteger c) {
		
		BigInteger n = publicKey.getN();
		BigInteger nSquare = publicKey.getnSquared();
		BigInteger lambda = privateKey.getLambda();
		
        BigInteger u = privateKey.getPreCalculatedDenominator();
        return c.modPow(lambda, nSquare).subtract(BigInteger.ONE).divide(n).multiply(u).mod(n);
    }
}
