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

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.BaseTestCase;

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
        String algorithm = "HmacSHA256";
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

        Mac mac = AssociationUtils.generateMac(aliceShared, algorithm);
        assertNotNull(AssociationUtils.caclulateSignature("foo", mac));
    }

}