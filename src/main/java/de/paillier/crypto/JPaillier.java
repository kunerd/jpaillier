package de.paillier.crypto;

import java.math.BigInteger;
import java.util.Random;

import de.paillier.key.KeyPair;
import de.paillier.key.PrivateKey;
import de.paillier.key.PublicKey;

public class JPaillier {

	private PublicKey publicKey;
	private PrivateKey privateKey;

	public JPaillier(KeyPair keypair) {
		this.publicKey = keypair.getPublicKey();
		this.privateKey = keypair.getPrivateKey();
	}

	public BigInteger encrypt(BigInteger m) {
		
		BigInteger g = publicKey.getG();
		BigInteger n = publicKey.getN();
		BigInteger nSquare = publicKey.getnSquared();
		
		BigInteger r;
		do {
			r = new BigInteger(publicKey.getBits(), new Random());
		} while (r.compareTo(n) >= 0);

		BigInteger result = g.modPow(m, nSquare);
		BigInteger x = r.modPow(n, nSquare);

		result = result.multiply(x);
		result = result.mod(nSquare);
		
		return result;
	}
	
    public BigInteger decryption(BigInteger c) {
		
		BigInteger n = publicKey.getN();
		BigInteger nSquare = publicKey.getnSquared();
		BigInteger lambda = privateKey.getLambda();
		
        BigInteger u = privateKey.getX();
        return c.modPow(lambda, nSquare).subtract(BigInteger.ONE).divide(n).multiply(u).mod(n);
    }
    
    public PublicKey getPublicKey() {
    	return publicKey;
	}

}
