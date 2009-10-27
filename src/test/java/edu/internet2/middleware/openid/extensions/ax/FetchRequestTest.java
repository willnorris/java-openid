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

package edu.internet2.middleware.openid.extensions.ax;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.internet2.middleware.openid.extensions.BaseMessageExtensionTestCase;

/**
 * Test case for creating, marshalling, and unmarshalling {@link FetchRequest}.
 */
public class FetchRequestTest extends BaseMessageExtensionTestCase {

    /** Expected mode. */
    private String expectedMode;

    /** Expected required attributes. */
    private List<String> expectedRequired;

    /** Expected optional attributes. */
    private List<String> expectedOptional;

    /** Expected attribute count. */
    private Map<String, Integer> expectedAttributeCount;

    /** Expected update URL. */
    private URL expectedUpdateURL;

    /**
     * Constructor.
     * 
     * @throws MalformedURLException if URL is malformed
     */
    public FetchRequestTest() throws MalformedURLException {
        messageFile = "/data/edu/internet2/middleware/openid/extensions/ax/FetchRequest.txt";

        expectedMode = FetchRequest.MODE;
        expectedRequired = Arrays.asList(new String[] { "http://example.com/schema/fullname",
                "http://example.com/schema/gender", });
        expectedOptional = Arrays.asList(new String[] { "http://example.com/schema/favourite_dog",
                "http://example.com/schema/favourite_movie", });
        expectedAttributeCount = new HashMap<String, Integer>();
        expectedAttributeCount.put("http://example.com/schema/favourite_movie", 3);
        expectedUpdateURL = new URL("http://idconsumer.com/update?transaction_id=a6b5c41");
    }

    /** {@inheritDoc} */
    public void testMessageMarshall() {
        FetchRequest request = (FetchRequest) buildMessageExtension(FetchRequest.class);

        request.getRequiredAttributes().addAll(expectedRequired);
        request.getOptionalAttributes().addAll(expectedOptional);
        request.getAttributeCount().putAll(expectedAttributeCount);
        request.setUpdateURL(expectedUpdateURL);

        assertEquals(expectedParameters, request);
    }

    /** {@inheritDoc} */
    public void testMessageUnmarshall() {
        FetchRequest request = (FetchRequest) unmarshallMessageExtension(messageFile);

        String mode = request.getMode();
        assertEquals("FetchRequest mode was " + mode + ", expected " + expectedMode, expectedMode, mode);

        List<String> required = request.getRequiredAttributes();
        assertEquals("FetchRequest required was " + required + ", expected " + expectedRequired, expectedRequired,
                required);

        List<String> optional = request.getOptionalAttributes();
        assertEquals("FetchRequest if_available was " + optional + ", expected " + expectedOptional, expectedOptional,
                optional);

        Map<String, Integer> attributeCount = request.getAttributeCount();
        assertEquals("FetchRequest attribute count was " + attributeCount + ", expected " + expectedAttributeCount,
                expectedAttributeCount, attributeCount);

        URL updateURL = request.getUpdateURL();
        assertEquals("SimpleRegistrationRequest policyURL was " + updateURL.toString() + ", expected "
                + expectedUpdateURL.toString(), expectedUpdateURL.toString(), updateURL.toString());
    }

}