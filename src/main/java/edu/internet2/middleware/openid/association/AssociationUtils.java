/*
 * Copyright 2009 University Corporation for Advanced Internet Development, Inc.
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

package edu.internet2.middleware.openid.association;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.KeyAgreement;
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
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DH");
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
            KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
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
     * Generate a MAC for secret key.
     * 
     * @param secretKey sercret key to generate MAC for
     * @param algorithm algorithm to generate MAC for
     * @return generated MAC
     */
    public static Mac generateMac(SecretKey secretKey, String algorithm) {
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(secretKey);
            return mac;
        } catch (InvalidKeyException e) {
            log.error("Unable to generate Mac - " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            log.error("Unable to generate Mac - " + e.getMessage());
        }
        return null;
    }

    /**
     * Calculate signature for specified data using a Mac.
     * 
     * @param data data to calculate signature for
     * @param mac Mac used to calculate signature
     * @return calculated signature
     */
    public static String caclulateSignature(String data, Mac mac) {
        byte[] rawHmac = mac.doFinal(data.getBytes());
        return Base64.encodeBytes(rawHmac);
    }

}