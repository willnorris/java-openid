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

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.message.ParameterMap;
import edu.internet2.middleware.openid.message.encoding.EncodingException;
import edu.internet2.middleware.openid.message.encoding.MessageCodec;

/**
 * Message encoder implementation which produces Key-Value Form encoded strings.
 * 
 * As of OpenID 2.0, protocol extensions can only be attached to OpenID authentication requests, which always use URL
 * form encoding. As a consequence, key value form encoding messages do not include any namespace declarations. This
 * codec therefore assumes that all paramters are in the OpenID 2.0 namespace. If and when a future version of OpenID
 * allows for protocol extensions on requests that use key value form encoding, this codec will need to be updated to
 * accommodate that.
 */
public class KeyValueFormCodec implements MessageCodec<String> {

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
        ParameterMap parameters = new ParameterMap();

        for (String line : encoded.split("\n")) {
            String[] parts = line.split(":", 2);
            if (parts.length == 2) {
                log.debug("Found encoded paramater {} : {}", parts[0], parts[1]);
                parameters.put(new QName(OpenIDConstants.OPENID_20_NS, parts[0]), parts[1]);
            }
        }

        return parameters;
    }

    /** {@inheritDoc} */
    public String encode(ParameterMap parameters) throws EncodingException {
        log.debug("Encoding ParameterMap containing {} entries", parameters.size());
        StringBuffer buffer = new StringBuffer();

        for (QName qname : parameters.keySet()) {
            String key = qname.getLocalPart();
            String value = parameters.get(qname);

            if (key.contains(":")) {
                log.warn("Message parameter cannot contain a colon ':': {}", key);
                throw new EncodingException("Message parameter cannot contain a colon ':': " + key);
            }

            if (key.contains("\n")) {
                log.warn("Message parameter name cannot contain a newline: {}", key);
                throw new EncodingException("Message parameter name cannot contain a newline: " + key);
            }

            if (value.contains("\n")) {
                log.warn("Message parameter name cannot contain a newline: {}", value);
                throw new EncodingException("Message parameter name cannot contain a newline: " + value);
            }

            buffer.append(key);
            buffer.append(":");
            buffer.append(value);
            buffer.append("\n");
        }

        return buffer.toString();
    }
}