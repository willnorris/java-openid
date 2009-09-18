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

import javax.crypto.SecretKey;
import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.BaseMessageProviderTestCase;
import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;
import edu.internet2.middleware.openid.common.OpenIDConstants.SessionType;

/**
 * Test case for creating, marshalling, and unmarshalling {@link AssociationRequest}.
 */
public class AssociationResponseTest extends BaseMessageProviderTestCase {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(AssociationResponseTest.class);

    /** Expected association type. */
    private AssociationType expectedAssociationType;

    /** Expected session type. */
    private SessionType expectedSessionType;

    /** Expected DH modulus. */
    private BigInteger expectedModulus;

    /** Expected DH generator. */
    private BigInteger expectedGenerator;

    /** Expected DH server public. */
    private PublicKey expectedServerPublic;

    /** Expected encrypted MAC key. */
    private SecretKey expectedEncryptedMacKey;

    /** Shared secret used to decrypt the MAC key. */
    private SecretKey sharedSecret;

    /** Constructor. */
    public AssociationResponseTest() {
        messageFile = "/data/edu/internet2/middleware/openid/message/AssociationRequest.txt";
    }

    /** {@inheritDoc} */
    public void setUp() throws Exception {
        super.setUp();

        expectedAssociationType = AssociationType.HMAC_SHA256;
        expectedSessionType = SessionType.DH_SHA256;
        expectedModulus = OpenIDConstants.DEFAULT_DH_MODULUS;
        expectedGenerator = BigInteger.valueOf(2);
        expectedServerPublic = null;
    }

    /** {@inheritDoc} */
    public void testMessageMarshall() {
        log.info("testing message marshalling");
        QName qname = new QName(OpenIDConstants.OPENID_20_NS, AssociationRequest.MODE);
        AssociationRequest request = (AssociationRequest) buildMessage(qname);

        request.setAssociationType(expectedAssociationType);
        request.setSessionType(expectedSessionType);
        request.setDHModulus(expectedModulus);
        request.setDHGen(expectedGenerator);
        // request.setDHConsumerPublic(expectedServerPublic);

        log.info("expected Parameters: {}", expectedParameters.size());
        for (QName key : expectedParameters.keySet()) {
            log.info("{} = {}", key, expectedParameters.get(key));
        }

        assertEquals(expectedParameters, request);
    }

    /** {@inheritDoc} */
    public void testMessageUnmarshall() {
        // log.info("default modulus = {}", Base64.encode(OpenIDConstants.DEFAULT_DH_MODULUS.toByteArray()));
        log.info("testing message unmarshalling");
        AssociationRequest request = (AssociationRequest) unmarshallMessage(messageFile);

        String mode = request.getMode();
        assertEquals("AssociationRequest mode was " + mode + ", expected " + AssociationRequest.MODE, mode,
                AssociationRequest.MODE);

        AssociationType associationType = request.getAssociationType();
        assertEquals("AssociationRequest assoc_type was " + associationType + ", expected " + expectedAssociationType,
                expectedAssociationType, associationType);

        SessionType sessionType = request.getSessionType();
        assertEquals("AssociationRequest session_type was " + sessionType + ", expected " + expectedSessionType,
                expectedSessionType, sessionType);

        BigInteger modulus = request.getDHModulus();
        assertEquals("AssociationRequest dh_modulus was " + modulus + ", expected " + expectedModulus, expectedModulus,
                modulus);

        BigInteger generator = request.getDHGen();
        assertEquals("AssociationRequest dh_gen was " + generator + ", expected " + expectedGenerator,
                expectedGenerator, generator);

        PublicKey consumerPublic = request.getDHConsumerPublic();
        assertEquals("AssociationRequest dh_consumer_public was " + consumerPublic + ", expected "
                + expectedServerPublic, expectedServerPublic, consumerPublic);

    }
}