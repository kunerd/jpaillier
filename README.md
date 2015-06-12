jPaillier
=========
[![Build Status](https://travis-ci.org/kunerd/jpaillier.png?branch=master)](https://travis-ci.org/kunerd/jpaillier)

A Java implementation of Paillier cryptosystem.

This library is developed as part of the research project CoPPDA (Corporate
  Privacy Preserving Data Analysis, http://www.enterprise-application-development.org/projects/coppda.html). There
  it is used for the implementation of different privacy preserving data minin
  methods.

WARNING: This library is developed for research, also it is in an early state
and therefor NOT production ready.

usage
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


##References
==========
 * [Public-Key Cryptosystems Based on Composite
Degree Residuosity Classes](http://www.cs.tau.ac.il/~fiat/crypt07/papers/Pai99pai.pdf)
 * [Wikipedia article](https://en.wikipedia.org/wiki/Paillier_cryptosystem)
