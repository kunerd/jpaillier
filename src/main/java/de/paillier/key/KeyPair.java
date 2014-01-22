package de.paillier.key;


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
}
