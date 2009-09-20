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

/**
 * Test case for creating, marshalling, and unmarshalling {@link AssociationRequest}.
 */
public class ErrorResponseTest extends BaseMessageProviderTestCase {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(ErrorResponseTest.class);

    /** Expected mode. */
    private String expectedMode;

    /** Expected error. */
    private String expectedError;

    /** Expected contact. */
    private String expectedContact;

    /** Expected reference. */
    private String expectedReference;

    /** Constructor. */
    public ErrorResponseTest() {
        messageFile = "/data/edu/internet2/middleware/openid/message/ErrorResponse.txt";
    }

    /** {@inheritDoc} */
    public void setUp() throws Exception {
        super.setUp();

        expectedMode = ErrorResponse.MODE;
        expectedError = "Error Message";
        expectedContact = "George P. Burdell";
        expectedReference = "kb123";
    }

    /** {@inheritDoc} */
    public void testMessageMarshall() {
        QName qname = new QName(OpenIDConstants.OPENID_20_NS, expectedMode);
        ErrorResponse response = (ErrorResponse) buildMessage(qname);

        response.setError(expectedError);
        response.setContact(expectedContact);
        response.setReference(expectedReference);

        assertEquals(expectedParameters, response);
    }

    /** {@inheritDoc} */
    public void testMessageUnmarshall() {
        ErrorResponse response = (ErrorResponse) unmarshallMessage(messageFile);

        String mode = response.getMode();
        assertEquals("NegativeAssertion mode was " + mode + ", expected " + expectedMode, expectedMode, mode);

        String error = response.getError();
        assertEquals("ErrorResponse error was " + error + ", expected " + expectedError, expectedError, error);

        String contact = response.getContact();
        assertEquals("ErrorResponse contact was " + contact + ", expected " + expectedContact, expectedContact, contact);

        String reference = response.getReference();
        assertEquals("ErrorResponse reference was " + reference + ", expected " + expectedReference, expectedReference,
                reference);

    }

}