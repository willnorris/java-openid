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

package edu.internet2.middleware.openid.extensions.pape;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.internet2.middleware.openid.Configuration;
import edu.internet2.middleware.openid.extensions.BaseMessageExtensionTestCase;

/**
 * Test case for creating, marshalling, and unmarshalling {@link PolicyRequest}.
 */
public class PolicyResponseTest extends BaseMessageExtensionTestCase {

    /** Expected authentication time. */
    private Date expectedAuthTime;

    /** Expected authentication policies. */
    private List<String> expectedAuthPolicies;

    /** Expected assurance levels. */
    private Map<String, String> expectedAssuranceLevels;

    /**
     * Constructor.
     * 
     * @throws ParseException if date format is invalid
     */
    public PolicyResponseTest() throws ParseException {
        messageFile = "/data/edu/internet2/middleware/openid/extensions/pape/PolicyResponse.txt";

        expectedAuthTime = Configuration.getInternetDateFormat().parse("2005-05-15T17:11:51Z");
        expectedAuthPolicies = Arrays.asList(new String[] {
                "http://schemas.openid.net/pape/policies/2007/06/phishing-resistant",
                "http://schemas.openid.net/pape/policies/2007/06/multi-factor", });
        expectedAssuranceLevels = new LinkedHashMap<String, String>();
        expectedAssuranceLevels.put("http://csrc.nist.gov/publications/nistpubs/800-63/SP800-63V1_0_2.pdf", "1");
        expectedAssuranceLevels.put("http://www.jisa.or.jp/spec/auth_level.html", "2");
    }

    /** {@inheritDoc} */
    public void testMessageMarshall() {
        PolicyResponse response = (PolicyResponse) buildMessageExtension(PolicyResponse.class);

        response.setAuthenticationTime(expectedAuthTime);
        response.getAuthenticationPolicies().addAll(expectedAuthPolicies);
        response.getAssuranceLevels().putAll(expectedAssuranceLevels);

        logMessageParameters(expectedParameters);
        logMessageParameters(response);
        
        assertEquals(expectedParameters, response);
    }

    /** {@inheritDoc} */
    public void testMessageUnmarshall() {
        PolicyResponse response = (PolicyResponse) unmarshallMessageExtension(messageFile);

        Date authTime = response.getAuthenticationTime();
        assertEquals("PAPEResposne auth_time was " + authTime + ", expected " + expectedAuthTime, expectedAuthTime,
                authTime);

        List<String> authPolicies = response.getAuthenticationPolicies();
        assertEquals("PAPEResponse preferred_auth_policies was " + authPolicies + ", expected " + expectedAuthPolicies,
                expectedAuthPolicies, authPolicies);

        Map<String, String> assuranceLevels = response.getAssuranceLevels();
        assertEquals("PAPEResponse auth_levels was " + assuranceLevels + ", expected " + expectedAssuranceLevels,
                expectedAssuranceLevels, assuranceLevels);
    }

}