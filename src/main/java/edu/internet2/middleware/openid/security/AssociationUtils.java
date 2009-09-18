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
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.opensaml.xml.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.common.OpenIDConstants;

/**
 * Association Utilities.
 */
public class AssociationUtils {

    /** Default Diffie-Hellman Parameter Spec. */
    public static final DHParameterSpec DEFAULT_PARAMETER_SPEC = new DHParameterSpec(
            OpenIDConstants.DEFAULT_DH_MODULUS, OpenIDConstants.DEFAULT_DH_GEN);

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
        return generateKeyPair(AssociationUtils.DEFAULT_PARAMETER_SPEC);
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
     * @return generated MAC Key
     */
    public static SecretKey generateMacKey(String algorithm) {
        try {
            return KeyGenerator.getInstance(algorithm).generateKey();
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
        byte[] encrypted = xor(macKey.getEncoded(), sharedSecret.getEncoded());
        return new SecretKeySpec(encrypted, macKey.getAlgorithm());
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
        byte[] decrypted = xor(macKey.getEncoded(), sharedSecret.getEncoded());
        return new SecretKeySpec(decrypted, macKey.getAlgorithm());
    }

    /**
     * Calculate signature for specified data using an Association.
     * 
     * @param association association
     * @param data data to calculate signature for
     * @return calculated signature
     */
    public static String calculateSignature(Association association, String data) {
        try {
            Mac mac = Mac.getInstance(association.getMacKey().getAlgorithm());
            mac.init(association.getMacKey());

            byte[] rawHmac = mac.doFinal(data.getBytes());
            return Base64.encodeBytes(rawHmac, Base64.DONT_BREAK_LINES);
        } catch (InvalidKeyException e) {
            log.error("Unable to generate Mac - " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            log.error("Unable to generate Mac - " + e.getMessage());
        }

        return null;
    }

    /**
     * Load a public key from a byte array.
     * 
     * @param bytes public key bytes
     * @return public key
     * @throws NoSuchAlgorithmException if unknown algorithm is request
     * @throws InvalidKeySpecException if key spc is invalid
     */
    public static PublicKey loadPublicKey(byte[] bytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance("DH");
        return keyFactory.generatePublic(keySpec);
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