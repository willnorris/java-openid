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

package edu.internet2.middleware.openid.message;

import java.security.Key;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.namespace.QName;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.BaseMessageProviderTestCase;
import edu.internet2.middleware.openid.Configuration;
import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;
import edu.internet2.middleware.openid.common.OpenIDConstants.SessionType;
import edu.internet2.middleware.openid.message.io.Marshaller;
import edu.internet2.middleware.openid.message.io.MarshallingException;

/**
 * Test case for creating, marshalling, and unmarshalling {@link AssociationRequest}.
 */
public class AssociationResponseNoEncryptionTest extends BaseMessageProviderTestCase {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(AssociationResponseNoEncryptionTest.class);

    /** Expected mode. */
    private String expectedMode;

    /** Expected Association handle. */
    private String expectedAssociationHandle;

    /** Expected association type. */
    private AssociationType expectedAssociationType;

    /** Expected session type. */
    private SessionType expectedSessionType;

    /** Expected lifetime. */
    private Integer expectedLifetime;

    /** Expected MAC key. */
    private SecretKey expectedMacKey;

    /** Constructor. */
    public AssociationResponseNoEncryptionTest() {
        messageFile = "/data/edu/internet2/middleware/openid/message/AssociationResponseNoEncryption.txt";
    }

    /** {@inheritDoc} */
    public void setUp() throws Exception {
        super.setUp();

        expectedMode = OpenIDConstants.ASSOCIATION_RESPONSE_MODE;
        expectedAssociationHandle = "foobar";
        expectedAssociationType = AssociationType.HMAC_SHA256;
        expectedSessionType = SessionType.no_encryption;
        expectedLifetime = 3600;

        String encodedMacKey = "hee0W816z4fMtFK4X3Y7IZPEmRo9eORfWC9QoA/d0hU=";
        expectedMacKey = new SecretKeySpec(Base64.decodeBase64(encodedMacKey.getBytes()), expectedAssociationType
                .getAlgorithm());
    }

    /** {@inheritDoc} */
    public void testMessageMarshall() {
        QName qname = new QName(OpenIDConstants.OPENID_20_NS, expectedMode);
        AssociationResponse response = (AssociationResponse) buildMessage(qname);

        response.setAssociationHandle(expectedAssociationHandle);
        response.setAssociationType(expectedAssociationType);
        response.setSessionType(expectedSessionType);
        response.setLifetime(expectedLifetime);
        response.setMacKey(expectedMacKey);

        // test if maps are equal
        Marshaller marshaller = Configuration.getMarshallers().getMarshaller(qname);
        if (marshaller == null) {
            fail("Unable to find message marshaller for mode: " + qname);
        }
        try {
            ParameterMap generatedParameters = marshaller.marshall(response);
            assertTrue(expectedParameters.equals(generatedParameters));
        } catch (MarshallingException e) {
            fail("Unable to marshall message");
        }
    }

    /** {@inheritDoc} */
    public void testMessageUnmarshall() {
        AssociationResponse response = (AssociationResponse) unmarshallMessage(messageFile);

        String associationHandle = response.getAssociationHandle();
        assertEquals("AssociationResponse assoc_handle was " + associationHandle + ", expected "
                + expectedAssociationHandle, expectedAssociationHandle, associationHandle);

        AssociationType associationType = response.getAssociationType();
        assertEquals("AssociationResponse assoc_type was " + associationType + ", expected " + expectedAssociationType,
                expectedAssociationType, associationType);

        SessionType sessionType = response.getSessionType();
        assertEquals("AssociationResponse session_type was " + sessionType + ", expected " + expectedSessionType,
                expectedSessionType, sessionType);

        Integer lifetime = response.getLifetime();
        assertEquals("AssociationResponse expires_in was " + lifetime + ", expected " + expectedLifetime,
                expectedLifetime, lifetime);

        Key macKey = response.getMacKey();
        assertEquals("AssociationResponse mac_key was " + macKey + ", expected " + expectedMacKey, expectedMacKey,
                macKey);
    }

}