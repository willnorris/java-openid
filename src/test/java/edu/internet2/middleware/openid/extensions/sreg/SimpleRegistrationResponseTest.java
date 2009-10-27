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

import edu.internet2.middleware.openid.extensions.BaseMessageExtensionTestCase;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistration.Field;

/**
 * Test case for creating, marshalling, and unmarshalling {@link SimpleRegistrationResponse}.
 */
public class SimpleRegistrationResponseTest extends BaseMessageExtensionTestCase {

    /** Expected nickname. */
    private String expectedNickname;

    /** Expected email. */
    private String expectedEmail;

    /** Expected fullname. */
    private String expectedFullName;

    /** Expected DOB. */
    private String expectedDOB;

    /** Expected gender. */
    private String expectedGender;

    /** Expected postcode. */
    private String expectedPostcode;

    /** Expected country. */
    private String expectedCountry;

    /** Expected language. */
    private String expectedLanguage;

    /** Expected timezone. */
    private String expectedTimezone;

    /**
     * Constructor.
     * 
     * @throws MalformedURLException
     */
    public SimpleRegistrationResponseTest() throws MalformedURLException {
        messageFile = "/data/edu/internet2/middleware/openid/extensions/sreg/SimpleRegistrationResponse.txt";

        expectedNickname = "gpburdell";
        expectedEmail = "gpburdell@gatech.edu";
        expectedFullName = "George P. Burdell";
        expectedDOB = "1927-01-01";
        expectedGender = "M";
        expectedPostcode = "30332";
        expectedCountry = "US";
        expectedLanguage = "en";
        expectedTimezone = "America/New_York";
    }

    /** {@inheritDoc} */
    public void testMessageMarshall() {
        SimpleRegistrationResponse response = (SimpleRegistrationResponse) buildMessageExtension(SimpleRegistrationResponse.class);

        response.getFields().put(Field.nickname, expectedNickname);
        response.getFields().put(Field.email, expectedEmail);
        response.getFields().put(Field.fullname, expectedFullName);
        response.getFields().put(Field.dob, expectedDOB);
        response.getFields().put(Field.gender, expectedGender);
        response.getFields().put(Field.postcode, expectedPostcode);
        response.getFields().put(Field.country, expectedCountry);
        response.getFields().put(Field.language, expectedLanguage);
        response.getFields().put(Field.timezone, expectedTimezone);

        assertEquals(expectedParameters, response);
    }

    /** {@inheritDoc} */
    public void testMessageUnmarshall() {
        SimpleRegistrationResponse response = (SimpleRegistrationResponse) unmarshallMessageExtension(messageFile);

        String nickname = response.getFields().get(Field.nickname);
        assertEquals("SimpleRegistrationResponse nickname was " + nickname + ", expected " + expectedNickname,
                expectedNickname, nickname);

        String email = response.getFields().get(Field.email);
        assertEquals("SimpleRegistrationResponse email was " + email + ", expected " + expectedEmail, expectedEmail,
                email);

        String fullname = response.getFields().get(Field.fullname);
        assertEquals("SimpleRegistrationResponse fullname was " + fullname + ", expected " + expectedFullName,
                expectedFullName, fullname);

        String dob = response.getFields().get(Field.dob);
        assertEquals("SimpleRegistrationResponse dob was " + dob + ", expected " + expectedDOB, expectedDOB, dob);

        String gender = response.getFields().get(Field.gender);
        assertEquals("SimpleRegistrationResponse gender was " + gender + ", expected " + expectedGender,
                expectedGender, gender);

        String postcode = response.getFields().get(Field.postcode);
        assertEquals("SimpleRegistrationResponse postcode was " + postcode + ", expected " + expectedPostcode,
                expectedPostcode, postcode);

        String country = response.getFields().get(Field.country);
        assertEquals("SimpleRegistrationResponse country was " + country + ", expected " + expectedCountry,
                expectedCountry, country);

        String language = response.getFields().get(Field.language);
        assertEquals("SimpleRegistrationResponse language was " + language + ", expected " + expectedLanguage,
                expectedLanguage, language);

        String timezone = response.getFields().get(Field.timezone);
        assertEquals("SimpleRegistrationResponse timezone was " + timezone + ", expected " + expectedTimezone,
                expectedTimezone, timezone);

    }

}