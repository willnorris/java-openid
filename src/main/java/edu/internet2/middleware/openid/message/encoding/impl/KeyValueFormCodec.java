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

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.message.encoding.EncodingException;
import edu.internet2.middleware.openid.message.encoding.MessageCodec;

/**
 * Message encoder implementation which produces Key-Value Form encoded strings.
 */
public class KeyValueFormCodec implements MessageCodec<String> {

    /** Logger. */
    private static final Logger LOG = LoggerFactory.getLogger(KeyValueFormCodec.class);

    /** Codec singleton instance. */
    private static KeyValueFormCodec codec;

    /**
     * Get singleton instance.
     * 
     * @return singleton instance
     */
    public static KeyValueFormCodec getInstance() {
        if (codec == null) {
            codec = new KeyValueFormCodec();
        }

        return codec;
    }

    /** {@inheritDoc} */
    public Map<String, String> decode(String encoded) throws EncodingException {
        Map<String, String> parameters = new HashMap<String, String>();

        for (String line : encoded.split("\n")) {
            String[] parts = line.split(":", 2);
            if (parts.length == 2) {
                parameters.put(parts[0], parts[1]);
            }
        }

        return parameters;
    }

    /** {@inheritDoc} */
    public String encode(Map<String, String> parameters) throws EncodingException {
        StringBuffer buffer = new StringBuffer();

        for (String key : parameters.keySet()) {
            if (key.contains(":")) {
                LOG.warn("Message parameter cannot contain a colon ':': {}", key);
                throw new EncodingException("Message parameter cannot contain a colon ':': " + key);
            }

            if (key.contains("\n")) {
                LOG.warn("Message parameter name cannot contain a newline: {}", key);
                throw new EncodingException("Message parameter name cannot contain a newline: " + key);
            }

            if (parameters.get(key).contains("\n")) {
                LOG.warn("Message parameter name cannot contain a newline: {}", parameters.get(key));
                throw new EncodingException("Message parameter name cannot contain a newline: " + parameters.get(key));
            }

            buffer.append(key);
            buffer.append(":");
            buffer.append(parameters.get(key));
            buffer.append("\n");
        }

        return buffer.toString();
    }
}