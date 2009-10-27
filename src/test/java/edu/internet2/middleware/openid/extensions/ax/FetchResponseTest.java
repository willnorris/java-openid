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
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.internet2.middleware.openid.extensions.BaseMessageExtensionTestCase;

/**
 * Test case for creating, marshalling, and unmarshalling {@link FetchRequest}.
 */
public class FetchResponseTest extends BaseMessageExtensionTestCase {

    /** Expected mode. */
    private String expectedMode;

    /** Expected attributes. */
    private Map<String, List<String>> expectedAttributes;

    /** Expected update URL. */
    private URL expectedUpdateURL;

    /**
     * Constructor.
     * 
     * @throws MalformedURLException if URL is malformed
     */
    public FetchResponseTest() throws MalformedURLException {
        messageFile = "/data/edu/internet2/middleware/openid/extensions/ax/FetchResponse.txt";

        expectedMode = FetchResponse.MODE;
        expectedAttributes = new LinkedHashMap<String, List<String>>();
        expectedAttributes.put("http://example.com/schema/fullname", Arrays.asList(new String[] { "John Smith" }));
        expectedAttributes.put("http://example.com/schema/gender", Collections.EMPTY_LIST);
        expectedAttributes.put("http://example.com/schema/favourite_dog", Arrays.asList(new String[] { "Spot" }));
        expectedAttributes.put("http://example.com/schema/favourite_movie", Arrays.asList(new String[] { "Movie1",
                "Movie2", }));

        expectedUpdateURL = new URL("http://idconsumer.com/update?transaction_id=a6b5c41");
    }

    /** {@inheritDoc} */
    public void testMessageMarshall() {
        FetchResponse response = (FetchResponse) buildMessageExtension(FetchResponse.class);

        response.getAttributes().putAll(expectedAttributes);
        response.setUpdateURL(expectedUpdateURL);

        assertEquals(expectedParameters, response);
    }

    /** {@inheritDoc} */
    public void testMessageUnmarshall() {
        FetchResponse response = (FetchResponse) unmarshallMessageExtension(messageFile);

        String mode = response.getMode();
        assertEquals("FetchResponse mode was " + mode + ", expected " + expectedMode, expectedMode, mode);

        Map<String, List<String>> attributes = response.getAttributes();
        assertEquals("FetchResponse attributes were " + attributes + ", expected " + expectedAttributes,
                expectedAttributes, attributes);

        URL updateURL = response.getUpdateURL();
        assertEquals("FetchResposne updateURL was " + updateURL.toString() + ", expected "
                + expectedUpdateURL.toString(), expectedUpdateURL.toString(), updateURL.toString());
    }

}