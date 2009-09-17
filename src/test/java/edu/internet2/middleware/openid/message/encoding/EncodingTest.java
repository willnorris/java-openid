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

package edu.internet2.middleware.openid.message.encoding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.BaseTestCase;
import edu.internet2.middleware.openid.message.ParameterMap;
import edu.internet2.middleware.openid.message.encoding.impl.KeyValueFormCodec;
import edu.internet2.middleware.openid.message.encoding.impl.URLFormCodec;

/**
 * Test message encoders.
 */
public class EncodingTest extends BaseTestCase {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(EncodingTest.class);

    /**
     * Test encoding and decoding using the URLFormCodec.
     * 
     * @throws EncodingException if unable to encode string
     */
    public void testUrlFormEncoding() throws EncodingException {
        String encoded = "openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0"
                + "&openid.mode=error&openid.error=This+is+an+example+message";

        ParameterMap parameters = URLFormCodec.getInstance().decode(encoded);

        assertEquals(2, parameters.size());
        assertEquals("error", parameters.get(buildQName("mode")));
        assertEquals("This is an example message", parameters.get(buildQName("error")));

        assertEquals(encoded, URLFormCodec.getInstance().encode(parameters));
    }

    /**
     * Test encoding and decoding using the KeyValueFormCodec.
     * 
     * @throws EncodingException if unable to encode string
     */
    public void testKeyValueEncoding() throws EncodingException {
        String encoded = "mode:error\nerror:This is an example message\n";

        ParameterMap parameters = KeyValueFormCodec.getInstance().decode(encoded);

        assertEquals(2, parameters.size());
        assertEquals("error", parameters.get(buildQName("mode")));
        assertEquals("This is an example message", parameters.get(buildQName("error")));

        assertEquals(encoded, KeyValueFormCodec.getInstance().encode(parameters));
    }

    /**
     * Test encoding invalid values using the KeyValueFormCodec.
     */
    public void testInvalidKeyValueEncoding() {

        try {
            ParameterMap parameters = new ParameterMap();
            parameters.put(buildQName("claimed:id"), "http://example.com/");
            KeyValueFormCodec.getInstance().encode(parameters);
            fail("KeyValueFormCodec failed to catch invalid parameter name containing a colon");
        } catch (EncodingException e) {
            // do nothing
        }

        try {
            ParameterMap parameters = new ParameterMap();
            parameters.put(buildQName("claimed\nid"), "http://example.com/");
            KeyValueFormCodec.getInstance().encode(parameters);
            fail("KeyValueFormCodec failed to catch invalid parameter name containing a newline");
        } catch (EncodingException e) {
            // do nothing
        }

        try {
            ParameterMap parameters = new ParameterMap();
            parameters.put(buildQName("claimed_id"), "http://example.\ncom/");
            KeyValueFormCodec.getInstance().encode(parameters);
            fail("KeyValueFormCodec failed to catch invalid parameter value containing a newline");
        } catch (EncodingException e) {
            // do nothing
        }
    }
}