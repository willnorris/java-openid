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

package edu.internet2.middleware.openid.extensions.sreg;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.EnumSet;

import edu.internet2.middleware.openid.extensions.BaseMessageExtensionTestCase;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistration.Field;

/**
 * Test case for creating, marshalling, and unmarshalling {@link SimpleRegistrationRequest}.
 */
public class SimpleRegistrationRequestTest extends BaseMessageExtensionTestCase {

    /** Expected set of required attributes. */
    private EnumSet<Field> expectedRequired;

    /** Expected set of optional attributes. */
    private EnumSet<Field> expectedOptional;

    /** Expected policy URL. */
    private URL expectedPolicyURL;

    /**
     * Constructor.
     * 
     * @throws MalformedURLException
     */
    public SimpleRegistrationRequestTest() throws MalformedURLException {
        messageFile = "/data/edu/internet2/middleware/openid/extensions/sreg/SimpleRegistrationRequest.txt";

        expectedRequired = EnumSet.of(Field.fullname, Field.email);
        expectedOptional = EnumSet.of(Field.language, Field.timezone);
        expectedPolicyURL = new URL("http://example.com/sreg.policy");
    }

    /** {@inheritDoc} */
    public void testMessageMarshall() {
        SimpleRegistrationRequest request = (SimpleRegistrationRequest) buildMessageExtension(SimpleRegistrationRequest.class);

        request.getRequiredFields().addAll(expectedRequired);
        request.getOptionalFields().addAll(expectedOptional);
        request.setPolicyURL(expectedPolicyURL);

        assertEquals(expectedParameters, request);
    }

    /** {@inheritDoc} */
    public void testMessageUnmarshall() {
        SimpleRegistrationRequest request = (SimpleRegistrationRequest) unmarshallMessageExtension(messageFile);

        EnumSet<Field> required = request.getRequiredFields();
        assertEquals("SimpleRegistrationRequest required was " + required + ", expected " + expectedRequired,
                expectedRequired, required);

        EnumSet<Field> optional = request.getOptionalFields();
        assertEquals("SimpleRegistrationRequest optional was " + optional + ", expected " + expectedOptional,
                expectedOptional, optional);

        URL policyURL = request.getPolicyURL();
        assertEquals("SimpleRegistrationRequest policyURL was " + policyURL.toString() + ", expected "
                + expectedPolicyURL.toString(), expectedPolicyURL.toString(), policyURL.toString());
    }

}