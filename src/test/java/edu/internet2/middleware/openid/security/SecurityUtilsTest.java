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

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import edu.internet2.middleware.openid.BaseTestCase;
import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;
import edu.internet2.middleware.openid.security.impl.BasicAssociation;

/**
 * Security utilities tests.
 */
public class SecurityUtilsTest extends BaseTestCase {

    /**
     * Test signing of data with the MAC.
     */
    public void testSignatureCreation() throws SecurityException {
        String algorithm = AssociationType.HMAC_SHA256.getAlgorithm();
        String encodedMacKey = "qMOYPeeb8PhGk8mlPUV+qBPPlFzY6xHk1AhSiRCQVsk=";
        Key macKey = new SecretKeySpec(encodedMacKey.getBytes(), algorithm);

        BasicAssociation association = new BasicAssociation();
        association.setAssociationType(AssociationType.HMAC_SHA256);
        association.setMacKey(macKey);

        String signature = SecurityUtils.calculateSignature(association, "foo");
        assertEquals("gp8cxaax9Hynh5JqM/kQ2mLdOC36fwYTrOUvQ4fX8nE=", signature);
    }

}