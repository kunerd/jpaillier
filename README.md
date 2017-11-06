jPaillier
=========
[![Build Status](https://travis-ci.org/kunerd/jpaillier.png?branch=master)](https://travis-ci.org/kunerd/jpaillier)

A Java implementation of the Paillier cryptosystem.

This library was developed as part of the research project CoPPDA (Corporate Privacy Preserving Data Analysis, http://www.enterprise-application-development.org/projects/coppda.html). To see it in action have a look at [ppid3](https://github.com/kunerd/ppid3) where it is used to implement a privacy preserving ID3 decision tree generation algorithm.

**WARNING: This library was developed for research only and is by no means implemented in a cryptographically secure way.**

Usage
-----

create a key pair:
```
    KeyPairBuilder keygen = new KeyPairBuilder();
    keyPair = keygen.generateKeyPair();
```
encryption:
```
	PublicKey publicKey = keyPair.getPublicKey();
    BigInteger ciphertext = publicKey.encrypt(plainData);
```

decrypt a ciphertext:
```
    BigInteger decryptedData = keyPair.decrypt(ciphertext);
```

Homomorphic Addition
--------------------
An interesting feature of the Paillier cryptosystem is its homomophic properties. The following example demonstrates how this library can be used to compute the homomorphic addition of two plaintext input values.
```
	BigInteger plainA = BigInteger.valueOf(102);
	BigInteger plainB = BigInteger.valueOf(203);

	BigInteger encryptedA = publicKey.encrypt(plainA);
	BigInteger encryptedB = publicKey.encrypt(plainB);
	
	BigInteger encryptedProduct = encryptedA.multiply(encryptedB).mod(publicKey.getnSquared());

	BigInteger additionResult = keypair.decrypt(encryptedProduct);
		
	// additionResult = 102 + 203 = 305
```
See the references for details. More examples can be found in the file: [HomomorphicPropertiesTest](https://github.com/kunerd/jpaillier/blob/master/src/test/java/de/paillier/HomomorphicPropertiesTest.java).

## References
 * [Public-Key Cryptosystems Based on Composite
Degree Residuosity Classes](http://www.cs.tau.ac.il/~fiat/crypt07/papers/Pai99pai.pdf)
 * [Wikipedia article](https://en.wikipedia.org/wiki/Paillier_cryptosystem)
