/*
 * Copyright [2009] [University Corporation for Advanced Internet Development, Inc.]
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

package edu.internet2.middleware.openid.message.encoding.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.message.ParameterMap;
import edu.internet2.middleware.openid.message.encoding.EncodingException;
import edu.internet2.middleware.openid.message.encoding.MessageCodec;

/**
 * Message encoder implementation which produces x-www-urlencoded strings. Message parameters in a URL encoded string
 * must be prefixed with the string "openid.". This codec will prefix all message parameters when encoding, and will
 * strip the prefix from all parameters when decoding. Any parameters that are not prefixed with "openid." are not part
 * of the OpenID message and will not be included in the decoded parameter map.
 */
public class URLFormCodec implements MessageCodec<String> {

    /** Logger. */
    private static final Logger LOG = LoggerFactory.getLogger(URLFormCodec.class);

    /** Prefix attached to each parameter of the encoded string. */
    private static final String PARAMETER_PREFIX = "openid.";

    /** Codec singleton instance. */
    private static URLFormCodec codec;

    /**
     * Get singleton instance.
     * 
     * @return singleton instance
     */
    public static URLFormCodec getInstance() {
        if (codec == null) {
            codec = new URLFormCodec();
        }

        return codec;
    }

    /** {@inheritDoc} */
    public ParameterMap decode(String encoded) throws EncodingException {
        ParameterMap parameters = new ParameterMap();
        String key;
        String value;

        for (String pair : encoded.split("&")) {
            String[] parts = pair.split("=", 2);
            if (parts.length == 2) {
                try {
                    key = URLDecoder.decode(parts[0], "UTF-8");
                    if (key.startsWith(PARAMETER_PREFIX)) {
                        key = key.substring(PARAMETER_PREFIX.length());
                        value = URLDecoder.decode(parts[1], "UTF-8");
                        parameters.put(key, value);
                    }
                } catch (UnsupportedEncodingException e) {
                    LOG.error("UTF-8 encoding is not supported, this VM is not Java compliant.");
                    throw new EncodingException("Unable to decode message, UTF-8 encoding is not supported");
                }
            }
        }

        return parameters;
    }

    /** {@inheritDoc} */
    public String encode(ParameterMap parameters) throws EncodingException {
        StringBuffer buffer = new StringBuffer();

        int numKeys = parameters.keySet().size();
        for (String key : parameters.keySet()) {
            try {
                buffer.append(URLEncoder.encode(PARAMETER_PREFIX + key, "UTF-8"));
                buffer.append("=");
                buffer.append(URLEncoder.encode(parameters.get(key), "UTF-8"));
                if (--numKeys > 0) {
                    buffer.append("&");
                }
            } catch (UnsupportedEncodingException e) {
                LOG.error("UTF-8 encoding is not supported, this VM is not Java compliant.");
                throw new EncodingException("Unable to encode message, UTF-8 encoding is not supported");
            }
        }

        return buffer.toString();
    }

}