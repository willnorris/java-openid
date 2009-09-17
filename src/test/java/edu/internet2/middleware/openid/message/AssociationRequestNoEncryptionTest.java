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
public class AssociationRequestNoEncryptionTest extends BaseMessageProviderTestCase {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(AssociationRequestNoEncryptionTest.class);

    /** Expected association type. */
    private AssociationType expectedAssociationType;

    /** Expected session type. */
    private SessionType expectedSessionType;

    /** Constructor. */
    public AssociationRequestNoEncryptionTest() {
        messageFile = "/data/edu/internet2/middleware/openid/message/AssociationRequestNoEncryption.txt";
    }

    /** {@inheritDoc} */
    public void setUp() throws Exception {
        super.setUp();

        expectedAssociationType = AssociationType.HMAC_SHA256;
        expectedSessionType = SessionType.no_encryption;
    }

    /** {@inheritDoc} */
    public void testMessageMarshall() {
        log.info("testing message marshalling");
        QName qname = new QName(OpenIDConstants.OPENID_20_NS, AssociationRequest.MODE);
        AssociationRequest request = (AssociationRequest) buildMessage(qname);

        request.setAssociationType(expectedAssociationType);
        request.setSessionType(expectedSessionType);

        assertEquals(expectedParameters, request);
    }

    /** {@inheritDoc} */
    public void testMessageUnmarshall() {
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

    }
}