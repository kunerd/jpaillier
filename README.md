jPaillier 
=========
[![Build Status](https://travis-ci.org/kunerd/jpaillier.png?branch=master)](https://travis-ci.org/kunerd/jpaillier)

A Java implementation of Paillier cryptosystem.

WARNING: This library is in an early state and therefor NOT production ready

usage
-----

create a key pair:
```
    KeyPairBuilder keygen = new KeyPairBuilder();
    keyPair = keygen.generateKeyPair();
```
encryption:
```
    BigInteger ciphertext = JPaillier.encrypt(plainData, keyPair.getPublicKey());
```

decrypt a ciphertext:
```
    BigInteger decryptedData = JPaillier.decrypt(ciphertext, keyPair);
```


### Sources
 * [Public-Key Cryptosystems Based on Composite
Degree Residuosity Classes](http://www.cs.tau.ac.il/~fiat/crypt07/papers/Pai99pai.pdf)
 * [Wikipedia article](https://en.wikipedia.org/wiki/Paillier_cryptosystem)
