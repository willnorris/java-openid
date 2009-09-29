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

package edu.internet2.middleware.openid.security;

import java.security.KeyPair;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.BaseTestCase;
import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;
import edu.internet2.middleware.openid.common.OpenIDConstants.SessionType;
import edu.internet2.middleware.openid.message.encoding.EncodingUtils;

/**
 * Association test.
 */
public class AssociationTest extends BaseTestCase {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(AssociationTest.class);

    /**
     * Test DH key exchange.
     * 
     * @throws Exception
     */
    public void testKeyExchange() {
        String algorithm = AssociationType.HMAC_SHA256.getAlgorithm();
        KeyPair alice = AssociationUtils.generateKeyPair();
        KeyPair bob = AssociationUtils.generateKeyPair();

        assertNotNull(alice);
        assertNotNull(bob);
        assertFalse("Alice and Bob generated the same key pair", alice.equals(bob));

        SecretKey aliceShared = AssociationUtils.generateSharedSecret(alice.getPrivate(), bob.getPublic(), algorithm);
        SecretKey bobShared = AssociationUtils.generateSharedSecret(bob.getPrivate(), alice.getPublic(), algorithm);

        assertNotNull(aliceShared);
        assertNotNull(bobShared);
        assertEquals("Alice and Bob calculated different shared secrets", aliceShared, bobShared);

    }

    /**
     * Test encryption of the MAC key.
     */
    public void testMacKeyEncryption() {
        AssociationType associationType = AssociationType.HMAC_SHA256;
        SessionType sessionType = SessionType.DH_SHA256;

        KeyPair keyPair = AssociationUtils.generateKeyPair();
        SecretKey sharedSecret = AssociationUtils.generateSharedSecret(keyPair.getPrivate(), keyPair.getPublic(),
                sessionType.getAlgorithm());

        SecretKey macKey = AssociationUtils
                .generateMacKey(associationType.getAlgorithm(), associationType.getKeySize());
        assertNotNull(macKey);
        log.info("mac key: {}", EncodingUtils.encodeSecretKey(macKey));
        assertEquals("Key is not the expected size.", associationType.getKeySize(), macKey.getEncoded().length * 8);

        SecretKey encryptedKey = AssociationUtils.encryptMacKey(macKey, sharedSecret);
        assertNotNull(encryptedKey);
        log.info("encrypted key: {}", EncodingUtils.encodeSecretKey(encryptedKey));
        assertFalse("Encrypted key didn't change.", macKey.equals(encryptedKey));

        SecretKey decryptedKey = AssociationUtils.decryptMacKey(encryptedKey, sharedSecret);
        assertNotNull(decryptedKey);
        log.info("derypted key: {}", EncodingUtils.encodeSecretKey(decryptedKey));
        assertEquals("Decrypted key doesn't match the original mac key", macKey, decryptedKey);
    }

}