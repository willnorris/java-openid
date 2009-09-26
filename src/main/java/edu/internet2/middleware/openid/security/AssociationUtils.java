/*
 * Copyright [2009] [University Corporation for Advanced Internet Development, Inc.]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.internet2.middleware.openid.security;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPrivateKeySpec;
import javax.crypto.spec.DHPublicKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.common.OpenIDConstants;

/**
 * Utility methods used to establish an OpenID Association, such as implementing Diffie-Hellman key exchange.
 */
public final class AssociationUtils {

    /** Diffie-Hellman algorithm. */
    public static final String DH_ALGORITHM = "DH";

    /** Logger. */
    private static final Logger log = LoggerFactory.getLogger(AssociationUtils.class);

    /** Constructor. */
    private AssociationUtils() {
    }

    /**
     * Generate a Diffie-Hellman key pair using the default parameter spec.
     * 
     * @return generated key pair
     */
    public static KeyPair generateKeyPair() {
        return generateKeyPair(OpenIDConstants.DEFAULT_PARAMETER_SPEC);
    }

    /**
     * Generate a Diffie-Hellman key pair using the specified parameters.
     * 
     * @param parameters parameters to use in generating the key pair
     * @return generated key pair
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     */
    public static KeyPair generateKeyPair(DHParameterSpec parameters) {
        log.debug("generating new Diffi-Hellman key pair.");

        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(DH_ALGORITHM);
            keyGen.initialize(parameters);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            log.error("Unable to generate Diffie-Hellman key pair - " + e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            log.error("Unable to generate Diffie-Hellman key pair - " + e.getMessage());
        }

        return null;
    }

    /**
     * Generate a shared secret key.
     * 
     * @param privateKey private key used to calculate shared secret
     * @param publicKey public key used to calculate shared secret
     * @param algorithm algorithm to use in generated key
     * @return generated secret key
     */
    public static SecretKey generateSharedSecret(PrivateKey privateKey, PublicKey publicKey, String algorithm) {
        log.debug("generating new shared secret");

        try {
            KeyAgreement keyAgreement = KeyAgreement.getInstance(DH_ALGORITHM);
            keyAgreement.init(privateKey);
            keyAgreement.doPhase(publicKey, true);
            byte[] secretKey = keyAgreement.generateSecret();
            return new SecretKeySpec(secretKey, algorithm);
        } catch (NoSuchAlgorithmException e) {
            log.error("Unable to generate Diffie-Hellman shared secret - " + e.getMessage());
        } catch (InvalidKeyException e) {
            log.error("Unable to generate Diffie-Hellman shared secret - " + e.getMessage());
        }

        return null;
    }

    /**
     * Generate a new MAC Key with the specified algorithm.
     * 
     * @param algorithm algorithm to use for generated key
     * @param keySize size of key to generate
     * @return generated MAC Key
     */
    public static SecretKey generateMacKey(String algorithm, int keySize) {
        log.debug("generating new MAC key of size {} with algorithm: {}", keySize, algorithm);

        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(algorithm);
            keyGen.init(keySize);
            return keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            log.error("Unable to generate mac key - " + e.getMessage());
        }

        return null;
    }

    /**
     * Encrypt the MAC key using the provided shared secret.
     * 
     * @param macKey MAC key to encrypt
     * @param sharedSecret shared secret used to encrypt MAC key
     * @return encrypted MAC Key
     */
    public static SecretKey encryptMacKey(Key macKey, SecretKey sharedSecret) {
        log.debug("encrypting MAC key");
        return macKeyEncryption(macKey, sharedSecret);
    }

    /**
     * Decrypt the MAC key using the provided shared secret. This is identical to encryptMacKey(), and is included
     * merely for logical function naming. Both functions can be used interchangeably.
     * 
     * @param macKey MAC key to decrypt
     * @param sharedSecret shared secret used to decrypt MAC key
     * @return decrypted MAC key
     */
    public static SecretKey decryptMacKey(Key macKey, SecretKey sharedSecret) {
        log.debug("decrypting MAC key");
        return macKeyEncryption(macKey, sharedSecret);
    }

    /**
     * Encrypt the MAC key using the provided shared secret.
     * 
     * @param macKey MAC key to encrypt
     * @param sharedSecret shared secret used to encrypt MAC key
     * @return encrypted MAC Key
     */
    private static SecretKey macKeyEncryption(Key macKey, SecretKey sharedSecret) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(sharedSecret.getAlgorithm());
            byte[] digestedSecret = messageDigest.digest(sharedSecret.getEncoded());

            byte[] encrypted = xor(macKey.getEncoded(), digestedSecret);
            return new SecretKeySpec(encrypted, macKey.getAlgorithm());
        } catch (NoSuchAlgorithmException e) {
            log.error("unable to encrypt MAC key: {}", e.getMessage());
        }

        return null;
    }

    /**
     * Load a public key from a byte array.
     * 
     * @param bytes public key bytes
     * @param parameters parameter spec used to load the key
     * @return public key
     * @throws NoSuchAlgorithmException if unknown algorithm is request
     * @throws InvalidKeySpecException if key spec is invalid
     */
    public static DHPublicKey loadPublicKey(byte[] bytes, DHParameterSpec parameters) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        DHPublicKeySpec keySpec = new DHPublicKeySpec(new BigInteger(bytes), parameters.getP(), parameters.getG());
        KeyFactory keyFactory = KeyFactory.getInstance("DH");
        return (DHPublicKey) keyFactory.generatePublic(keySpec);
    }

    /**
     * Load a private key from a byte array.
     * 
     * @param bytes private key bytes
     * @param parameters parameter spec used to load the key
     * @return public key
     * @throws NoSuchAlgorithmException if unknown algorithm is request
     * @throws InvalidKeySpecException if key spec is invalid
     */
    public static DHPrivateKey loadPrivateKey(byte[] bytes, DHParameterSpec parameters)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        DHPrivateKeySpec keySpec = new DHPrivateKeySpec(new BigInteger(bytes), parameters.getP(), parameters.getG());
        KeyFactory keyFactory = KeyFactory.getInstance("DH");
        return (DHPrivateKey) keyFactory.generatePrivate(keySpec);
    }

    /**
     * Calculate an XOR on two byte arrays.
     * 
     * @param a first byte array
     * @param b second byte array
     * @return results of XOR
     */
    public static byte[] xor(byte[] a, byte[] b) {
        BigInteger x = new BigInteger(a);
        BigInteger y = new BigInteger(b);

        return x.xor(y).toByteArray();
    }

}