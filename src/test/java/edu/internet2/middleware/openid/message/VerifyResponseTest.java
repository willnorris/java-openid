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
import edu.internet2.middleware.openid.common.OpenIDConstants.Parameter;

/**
 * Test case for creating, marshalling, and unmarshalling {@link AssociationRequest}.
 */
public class VerifyResponseTest extends BaseMessageProviderTestCase {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(VerifyResponseTest.class);

    /** Expected mode. */
    private String expectedMode;

    /** Expected valid. */
    private boolean expectedValid;

    /** Expected invalidate handle. */
    private String expectedInvalidateHandle;

    /** Constructor. */
    public VerifyResponseTest() {
        messageFile = "/data/edu/internet2/middleware/openid/message/VerifyResponse.txt";
    }

    /** {@inheritDoc} */
    public void setUp() throws Exception {
        super.setUp();

        expectedMode = OpenIDConstants.VERIFICATION_RESPONSE_MODE;
        expectedValid = true;
        expectedInvalidateHandle = "old-handle";
    }

    /** {@inheritDoc} */
    public void testMessageMarshall() {
        QName qname = new QName(OpenIDConstants.OPENID_20_NS, expectedMode);
        VerifyResponse response = (VerifyResponse) buildMessage(qname);

        response.setValid(expectedValid);
        response.setInvalidateHandle(expectedInvalidateHandle);

        // test if maps are equal
        Marshaller marshaller = Configuration.getMarshallers().get(qname);
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
        ParameterMap parameters = parseMessageFile(messageFile);
        parameters.put(Parameter.mode.QNAME, expectedMode);
        VerifyResponse response = (VerifyResponse) unmarshallMessage(parameters, expectedMode);

        boolean isValid = response.isValid();
        assertEquals("VerifyResponse is_valid was " + isValid + ", expected " + expectedValid, expectedValid, isValid);

        String invalidateHandle = response.getInvalidateHandle();
        assertEquals("VerifyResponse invalidate_handle was " + invalidateHandle + ", expected "
                + expectedInvalidateHandle, expectedInvalidateHandle, invalidateHandle);
    }

}