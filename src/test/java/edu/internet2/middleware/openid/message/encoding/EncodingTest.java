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

import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.BaseTestCase;
import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;
import edu.internet2.middleware.openid.message.encoding.impl.KeyValueFormCodec;
import edu.internet2.middleware.openid.message.encoding.impl.URLFormCodec;

/**
 * Test message encoders.
 */
public class EncodingTest extends BaseTestCase {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(EncodingTest.class);

    /** {@inheritDoc} */
    public void setUp() {
        // do nothing
    }

    /**
     * Test encoding and decoding using the URLFormCodec.
     * 
     * @throws EncodingException if unable to encode string
     */
    public void testUrlFormEncoding() throws EncodingException {
        String encoded = "openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0"
                + "&openid.error=This+is+an+example+message" + "&openid.mode=error";

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
        String encoded = "ns:http://specs.openid.net/auth/2.0\nerror:This is an example message\nmode:error\n";

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

    /**
     * Test appending of OpenID message parameters to a URL.
     * 
     * @throws MalformedURLException if URL is malformed
     */
    public void testMessageAppending() throws MalformedURLException {
        URL url = new URL("http://example.com/path?foo=bar#frag");
        String message = "openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.error=Error+message";
        assertEquals("http://example.com/path?foo=bar&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&"
                + "openid.error=Error+message#frag", EncodingUtils.appendMessageParameters(url, message).toString());
    }

    /**
     * Test encoding and decoding of public, private, and secret keys.
     * 
     * @throws NoSuchAlgorithmException if request algorithm is unavailable
     * @throws InvalidKeySpecException if key spec is invalid
     */
    public void testKeyEncoding() throws NoSuchAlgorithmException, InvalidKeySpecException {
        AssociationType associationType = AssociationType.HMAC_SHA1;
        DHParameterSpec parameters = OpenIDConstants.DEFAULT_PARAMETER_SPEC;

        String encodedPublicKey = "AL8SSPKap+y4nAhDC5LrkRxuU/Fd6CtWnZ4xnIDnc9XfpbLH8i1ZONIld4VAZAxts+5Ij3mq1CYMGo"
                + "sC5BS1ooLdFj3yNGF2jkRS3WgNLgDMvlNnOfzjRbg3BcdAsJYlVuQz8FjlwQ8WYrzUPfyzcK7X7wLyVSS5nd7XCfKjIZGV";
        String encodedPrivateKey = "aPBA0T12u08cSahfgPhX0FMRd3DhU8N1y1lZSYapCmQEN7jac7HrsbqEHiKoyw/ndQz3myJ+jASJ/"
                + "6Ve267hazLFbeDvY34p6uwkW/xypVS8cG9WWbhsLJrtDjyOfURf7l+OyFcu+C+71jAfA5txnpKV+olMsQqqHnfygnhxrQQ=";
        String encodedMacKey = "6zvrrVkA4crhXE+VBNk0V1TfC/Q=";

        DHPrivateKey privateKey = EncodingUtils.decodePrivateKey(encodedPrivateKey, parameters);
        assertEquals(encodedPrivateKey, EncodingUtils.encodePrivateKey(privateKey));

        DHPublicKey publicKey = EncodingUtils.decodePublicKey(encodedPublicKey, parameters);
        assertEquals(encodedPublicKey, EncodingUtils.encodePublicKey(publicKey));

        SecretKey macKey = EncodingUtils.decodeSecretKey(encodedMacKey, associationType.getAlgorithm());
        assertEquals(encodedMacKey, EncodingUtils.encodeSecretKey(macKey));
    }

}