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
import java.security.PublicKey;

import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.BaseMessageProviderTestCase;
import edu.internet2.middleware.openid.Configuration;
import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;
import edu.internet2.middleware.openid.common.OpenIDConstants.SessionType;
import edu.internet2.middleware.openid.message.encoding.EncodingUtils;
import edu.internet2.middleware.openid.message.io.Marshaller;
import edu.internet2.middleware.openid.message.io.MarshallingException;

/**
 * Test case for creating, marshalling, and unmarshalling {@link AssociationRequest}.
 */
public class AssociationResponseTest extends BaseMessageProviderTestCase {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(AssociationResponseTest.class);

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

    /** Expected DH server public. */
    private DHPublicKey expectedServerPublic;

    /** Expected encrypted MAC key. */
    private SecretKey expectedEncryptedMacKey;

    /** Constructor. */
    public AssociationResponseTest() {
        messageFile = "/data/edu/internet2/middleware/openid/message/AssociationResponse.txt";
    }

    /** {@inheritDoc} */
    public void setUp() throws Exception {
        super.setUp();

        expectedMode = OpenIDConstants.ASSOCIATION_RESPONSE_MODE;
        expectedAssociationHandle = "foobar";
        expectedAssociationType = AssociationType.HMAC_SHA256;
        expectedSessionType = SessionType.DH_SHA256;
        expectedLifetime = 3600;

        String serverPublicKey = "MIIBIzCBmQYJKoZIhvcNAQMBMIGLAoGBANz5OguIOXLsDhmYmsWizjEOHTdxfo2Vcbt2I3MYZuYe9"
                + "1ouJ4mLBX+YkcLiemOcPym2CBRYHNOyyjmG0mg3BVd9RcLn5S3IHHoXGHblzqdLFEi/368Ygo79JRnxTkXjgmY0rxlJ5"
                + "bU1zIKaSDuKdiI+XUkKJX8Fvf8W8vsixYOrAgECAgICAAOBhAACgYAyF/jTzfAxpM62s22/OFZe3p/R0WpKPwIe1xeCV"
                + "Kw53Kx2LA/yZjtSGJ3LC00zsWnnehbGDDv2nSHHKc9GKxsCyjUu03+G9p280yR/YC+T4/wegDFY/+ueqd98NmEHQFIi+"
                + "mdFwVnmpVbwqA+Ek1uDfo+mUFeVUfbVpZjI0FeBbw==";
        expectedServerPublic = EncodingUtils.decodePublicKey(serverPublicKey, OpenIDConstants.DEFAULT_PARAMETER_SPEC);

        String encodedMacKey = "hVGXOx0j7OndhRlCsfa37y1CP4GKapFc1wdz3gK71q4fkr+aTo9mvMaxkjZIzJIzcyGgYqD1XLJSdbfr"
                + "dSh5uR9ejxqTDAZUW0FwGZWpvfu2BEadoiwq8R4bqtiWFfRgMVIK5YW2hydvJGblZtxjhJfClh0Wbb6TK2xpq3y5NhA=";
        expectedEncryptedMacKey = EncodingUtils.decodeSecretKey(encodedMacKey, expectedAssociationType.getAlgorithm());
    }

    /** {@inheritDoc} */
    public void testMessageMarshall() {
        QName qname = new QName(OpenIDConstants.OPENID_20_NS, expectedMode);
        AssociationResponse response = (AssociationResponse) buildMessage(qname);

        response.setAssociationHandle(expectedAssociationHandle);
        response.setAssociationType(expectedAssociationType);
        response.setSessionType(expectedSessionType);
        response.setLifetime(expectedLifetime);
        response.setDHServerPublic(expectedServerPublic);
        response.setMacKey(expectedEncryptedMacKey);

        // test if maps are equal
        Marshaller marshaller = Configuration.getMarshallers().getMarshaller(qname);
        if (marshaller == null) {
            fail("Unable to find message marshaller for mode: " + qname);
        }
        try {
            ParameterMap generatedParameters = marshaller.marshall(response);
            log.info("expected");
            logMessageParameters(expectedParameters);
            log.info("generated");
            logMessageParameters(generatedParameters);
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

        PublicKey serverKey = response.getDHServerPublic();
        assertEquals("AssociationResponse dh_server_public was " + serverKey + ", expected " + expectedServerPublic,
                expectedServerPublic, serverKey);

        Key macKey = response.getMacKey();
        assertEquals("AssociationResponse enc_mac_key was " + macKey + ", expected " + expectedEncryptedMacKey,
                expectedEncryptedMacKey, macKey);
    }
}