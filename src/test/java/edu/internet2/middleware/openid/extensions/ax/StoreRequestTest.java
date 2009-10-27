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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.internet2.middleware.openid.extensions.BaseMessageExtensionTestCase;

/**
 * Test case for creating, marshalling, and unmarshalling {@link FetchRequest}.
 */
public class StoreRequestTest extends BaseMessageExtensionTestCase {

    /** Expected mode. */
    private String expectedMode;

    /** Expected attributes. */
    private Map<String, List<String>> expectedAttributes;

    /**
     * Constructor.
     * 
     * @throws MalformedURLException if URL is malformed
     */
    public StoreRequestTest() throws MalformedURLException {
        messageFile = "/data/edu/internet2/middleware/openid/extensions/ax/StoreRequest.txt";

        expectedMode = StoreRequest.MODE;
        expectedAttributes = new LinkedHashMap<String, List<String>>();
        expectedAttributes.put("http://example.com/schema/fullname", Arrays.asList(new String[] { "Bob Smith" }));
        expectedAttributes.put("http://example.com/schema/favourite_movie", Arrays.asList(new String[] { "Movie1",
                "Movie2", }));
    }

    /** {@inheritDoc} */
    public void testMessageMarshall() {
        StoreRequest request = (StoreRequest) buildMessageExtension(StoreRequest.class);

        request.getAttributes().putAll(expectedAttributes);

        assertEquals(expectedParameters, request);
    }

    /** {@inheritDoc} */
    public void testMessageUnmarshall() {
        StoreRequest request = (StoreRequest) unmarshallMessageExtension(messageFile);

        String mode = request.getMode();
        assertEquals("StoreRequest mode was " + mode + ", expected " + expectedMode, expectedMode, mode);

        Map<String, List<String>> attributes = request.getAttributes();
        assertEquals("StoreRequest attributes were " + attributes + ", expected " + expectedAttributes,
                expectedAttributes, attributes);
    }

}