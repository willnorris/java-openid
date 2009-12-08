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
import edu.internet2.middleware.openid.Configuration;
import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;
import edu.internet2.middleware.openid.common.OpenIDConstants.SessionType;
import edu.internet2.middleware.openid.message.io.MarshallingException;
import edu.internet2.middleware.openid.message.io.MessageMarshaller;

/**
 * Test case for creating, marshalling, and unmarshalling {@link AssociationRequest}.
 */
public class AssociationErrorTest extends BaseMessageProviderTestCase {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(AssociationErrorTest.class);

    /** Expected mode. */
    private String expectedMode;

    /** Expected error. */
    private String expectedError;

    /** Expected error code. */
    private String expectedErrorCode;

    /** Expected association type. */
    private AssociationType expectedAssociationType;

    /** Expected session type. */
    private SessionType expectedSessionType;

    /** Constructor. */
    public AssociationErrorTest() {
        messageFile = "/data/edu/internet2/middleware/openid/message/AssociationError.txt";
    }

    /** {@inheritDoc} */
    public void setUp() throws Exception {
        super.setUp();

        expectedMode = OpenIDConstants.ASSOCIATION_ERROR_MODE;
        expectedError = "Unsupported association type";
        expectedErrorCode = AssociationError.ERROR_CODE;
        expectedAssociationType = AssociationType.HMAC_SHA256;
        expectedSessionType = SessionType.DH_SHA256;
    }

    /** {@inheritDoc} */
    public void testMessageMarshall() {
        QName qname = new QName(OpenIDConstants.OPENID_20_NS, expectedMode);
        AssociationError response = (AssociationError) buildMessage(qname);

        response.setError(expectedError);
        response.setAssociationType(expectedAssociationType);
        response.setSessionType(expectedSessionType);

        // test if maps are equal
        MessageMarshaller marshaller = Configuration.getMessageMarshallers().getMarshaller(qname);
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
        AssociationError response = (AssociationError) unmarshallMessage(messageFile);

        String error = response.getError();
        assertEquals("AssociationError error was " + error + ", expected " + expectedError, expectedError, error);

        String errorCode = response.getErrorCode();
        assertEquals("AssociationError error_code was " + errorCode + ", expected " + expectedErrorCode,
                expectedErrorCode, errorCode);

        AssociationType associationType = response.getAssociationType();
        assertEquals("AssociationError assoc_type was " + associationType + ", expected " + expectedAssociationType,
                expectedAssociationType, associationType);

        SessionType sessionType = response.getSessionType();
        assertEquals("AssociationError session_type was " + sessionType + ", expected " + expectedSessionType,
                expectedSessionType, sessionType);
    }

}