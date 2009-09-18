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
public class NegativeInteractiveAssertionTest extends BaseMessageProviderTestCase {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(NegativeInteractiveAssertionTest.class);

    /** Expected mode. */
    private String expectedMode;

    /** Constructor. */
    public NegativeInteractiveAssertionTest() {
        messageFile = "/data/edu/internet2/middleware/openid/message/NegativeInteractiveAssertion.txt";
    }

    /** {@inheritDoc} */
    public void setUp() throws Exception {
        super.setUp();

        expectedMode = NegativeAssertion.MODE_INTERACTIVE;
    }

    /** {@inheritDoc} */
    public void testMessageMarshall() {
        QName qname = new QName(OpenIDConstants.OPENID_20_NS, expectedMode);
        NegativeAssertion response = (NegativeAssertion) buildMessage(qname);

        response.setMode(expectedMode);
        assertEquals(expectedParameters, response);
    }

    /** {@inheritDoc} */
    public void testMessageUnmarshall() {
        NegativeAssertion response = (NegativeAssertion) unmarshallMessage(messageFile);

        String mode = response.getMode();
        assertEquals("NegativeAssertion mode was " + mode + ", expected " + expectedMode, expectedMode, mode);
    }

}