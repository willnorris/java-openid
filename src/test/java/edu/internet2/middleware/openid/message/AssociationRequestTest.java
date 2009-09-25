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

import java.math.BigInteger;
import java.security.PublicKey;

import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.xml.namespace.QName;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.BaseMessageProviderTestCase;
import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;
import edu.internet2.middleware.openid.common.OpenIDConstants.SessionType;
import edu.internet2.middleware.openid.security.AssociationUtils;

/**
 * Test case for creating, marshalling, and unmarshalling {@link AssociationRequest}.
 */
public class AssociationRequestTest extends BaseMessageProviderTestCase {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(AssociationRequestTest.class);

    /** Expected mode. */
    private String expectedMode;

    /** Expected association type. */
    private AssociationType expectedAssociationType;

    /** Expected session type. */
    private SessionType expectedSessionType;

    /** Expected DH Modulus. */
    private BigInteger expectedModulus;

    /** Expected DH Generator. */
    private BigInteger expectedGenerator;

    /** Expected DH consumer public key. */
    private DHPublicKey expectedConsumerPublic;

    /** Constructor. */
    public AssociationRequestTest() {
        messageFile = "/data/edu/internet2/middleware/openid/message/AssociationRequest.txt";
    }

    /** {@inheritDoc} */
    public void setUp() throws Exception {
        super.setUp();

        expectedMode = AssociationRequest.MODE;
        expectedAssociationType = AssociationType.HMAC_SHA256;
        expectedSessionType = SessionType.DH_SHA256;
        expectedModulus = OpenIDConstants.DEFAULT_DH_MODULUS;
        expectedGenerator = BigInteger.valueOf(2);
        DHParameterSpec dhParameters = new DHParameterSpec(expectedModulus, expectedGenerator);

        String consumerPublicKey = "MIIBJDCBmQYJKoZIhvcNAQMBMIGLAoGBANz5OguIOXLsDhmYmsWizjEOHTdxfo2Vcbt2I3MYZuYe9"
                + "1ouJ4mLBX+YkcLiemOcPym2CBRYHNOyyjmG0mg3BVd9RcLn5S3IHHoXGHblzqdLFEi/368Ygo79JRnxTkXjgmY0rxlJ5bU"
                + "1zIKaSDuKdiI+XUkKJX8Fvf8W8vsixYOrAgECAgICAAOBhQACgYEAo4nggMiy9KMoUd88/PzaN92+tloaeP6K66eTx0IR8"
                + "IPowmV8bPL1NBiAScSyZ4/eUENfUIZ+UiGRDJDzBlMOWx4N2hlpZRmAM7CZPCu6BjMACFzJBhM3dAPYiTmjjlTexGIKzhs"
                + "LhAENmWmOlHcaywlYCB91Lgb7gOutS9iN2sw=";
        expectedConsumerPublic = AssociationUtils.loadPublicKey(Base64.decodeBase64(consumerPublicKey.getBytes()),
                dhParameters);
    }

    /** {@inheritDoc} */
    public void testMessageMarshall() {
        QName qname = new QName(OpenIDConstants.OPENID_20_NS, expectedMode);
        AssociationRequest request = (AssociationRequest) buildMessage(qname);

        request.setAssociationType(expectedAssociationType);
        request.setSessionType(expectedSessionType);
        request.setDHParameters(new DHParameterSpec(expectedModulus, expectedGenerator));
        request.setDHConsumerPublic(expectedConsumerPublic);

        assertEquals(expectedParameters, request);
    }

    /** {@inheritDoc} */
    public void testMessageUnmarshall() {
        AssociationRequest request = (AssociationRequest) unmarshallMessage(messageFile);

        String mode = request.getMode();
        assertEquals("AssociationRequest mode was " + mode + ", expected " + expectedMode, mode, expectedMode);

        AssociationType associationType = request.getAssociationType();
        assertEquals("AssociationRequest assoc_type was " + associationType + ", expected " + expectedAssociationType,
                expectedAssociationType, associationType);

        SessionType sessionType = request.getSessionType();
        assertEquals("AssociationRequest session_type was " + sessionType + ", expected " + expectedSessionType,
                expectedSessionType, sessionType);

        BigInteger modulus = request.getDHParameters().getP();
        assertEquals("AssociationRequest dh_modulus was " + modulus + ", expected " + expectedModulus, expectedModulus,
                modulus);

        BigInteger generator = request.getDHParameters().getG();
        assertEquals("AssociationRequest dh_gen was " + generator + ", expected " + expectedGenerator,
                expectedGenerator, generator);

        PublicKey consumerPublic = request.getDHConsumerPublic();
        assertEquals("AssociationRequest dh_consumer_public was " + consumerPublic + ", expected "
                + expectedConsumerPublic, expectedConsumerPublic, consumerPublic);

    }

}