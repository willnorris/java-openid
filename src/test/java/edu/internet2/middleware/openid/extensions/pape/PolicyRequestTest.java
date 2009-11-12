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

import java.util.Arrays;
import java.util.List;

import edu.internet2.middleware.openid.extensions.BaseMessageExtensionTestCase;

/**
 * Test case for creating, marshalling, and unmarshalling {@link PolicyRequest}.
 */
public class PolicyRequestTest extends BaseMessageExtensionTestCase {

    /** Expected maximum authentication age. */
    private Integer expectedMaxAuthAge;

    /** Expected authentication policies. */
    private List<String> expectedAuthPolicies;

    /** Expected assurance level types. */
    private List<String> expectedAssuranceLevelTypes;

    /** Constructor. */
    public PolicyRequestTest() {
        messageFile = "/data/edu/internet2/middleware/openid/extensions/pape/PolicyRequest.txt";

        expectedMaxAuthAge = 3600;
        expectedAuthPolicies = Arrays.asList(new String[] {
                "http://schemas.openid.net/pape/policies/2007/06/phishing-resistant",
                "http://schemas.openid.net/pape/policies/2007/06/multi-factor", });
        expectedAssuranceLevelTypes = Arrays.asList(new String[] {
                "http://csrc.nist.gov/publications/nistpubs/800-63/SP800-63V1_0_2.pdf",
                "http://www.jisa.or.jp/spec/auth_level.html", });
    }

    /** {@inheritDoc} */
    public void testMessageMarshall() {
        PolicyRequest request = (PolicyRequest) buildMessageExtension(PolicyRequest.class);

        request.setMaxAuthenticationAge(expectedMaxAuthAge);
        request.getAuthenticationPolicies().addAll(expectedAuthPolicies);
        request.getAssuranceLevelTypes().addAll(expectedAssuranceLevelTypes);

        assertEquals(expectedParameters, request);
    }

    /** {@inheritDoc} */
    public void testMessageUnmarshall() {
        PolicyRequest request = (PolicyRequest) unmarshallMessageExtension(messageFile);

        Integer maxAge = request.getMaxAuthenticationAge();
        assertEquals("PAPERequest max_auth_age was " + maxAge + ", expected " + expectedMaxAuthAge, expectedMaxAuthAge,
                maxAge);

        List<String> authPolicies = request.getAuthenticationPolicies();
        assertEquals("PAPERequest preferred_auth_policies was " + authPolicies + ", expected " + expectedAuthPolicies,
                expectedAuthPolicies, authPolicies);

        List<String> assuranceLevelTypes = request.getAssuranceLevelTypes();
        assertEquals("PAPERequest preferred_auth_level_types was " + assuranceLevelTypes + ", expected "
                + expectedAssuranceLevelTypes, expectedAssuranceLevelTypes, assuranceLevelTypes);
    }

}