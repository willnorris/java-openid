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

import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.message.encoding.EncodingException;

/**
 * Message encoder implementation which produces Key-Value Form encoded strings.
 */
public class KeyValueFormCodec extends AbstractNamespaceAwareCodec<String> {

    /** Codec singleton instance. */
    private static KeyValueFormCodec singleton;

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(KeyValueFormCodec.class);

    /**
     * Get singleton instance.
     * 
     * @return singleton instance
     */
    public static KeyValueFormCodec getInstance() {
        if (singleton == null) {
            singleton = new KeyValueFormCodec();
        }

        return singleton;
    }

    /** {@inheritDoc} */
    public ParameterMap decode(String encoded) throws EncodingException {
        log.debug("Decoding Key-Value form encoded string: {}", encoded);
        return super.decode(encoded);
    }

    /** {@inheritDoc} */
    public Map<String, String> decodeMessage(String encoded) throws EncodingException {
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
    public String encode(ParameterMap parameters) throws EncodingException {
        log.debug("Encoding ParameterMap containing {} entries", parameters.size());
        return super.encode(parameters);
    }

    /** {@inheritDoc} */
    public String encode(Map<String, String> parameters) throws EncodingException {
        StringBuffer buffer = new StringBuffer();

        for (String key : parameters.keySet()) {
            String value = parameters.get(key);
            log.debug("Encoding {}: {}", key, value);

            if (key.contains(":")) {
                log.warn("Message parameter cannot contain a colon ':': {}", key);
                throw new EncodingException("Message parameter cannot contain a colon ':': " + key);
            }

            if (key.contains("\n")) {
                log.warn("Message parameter name cannot contain a newline: {}", key);
                throw new EncodingException("Message parameter name cannot contain a newline: " + key);
            }

            if (value != null && value.contains("\n")) {
                log.warn("Message parameter value cannot contain a newline: {}", value);
                throw new EncodingException("Message parameter value cannot contain a newline: " + value);
            }

            buffer.append(key);
            buffer.append(":");
            buffer.append(value);
            buffer.append("\n");
        }

        return buffer.toString();
    }
}