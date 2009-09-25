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

package edu.internet2.middleware.openid.message.encoding.impl;

import java.util.Iterator;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.message.NamespaceMap;
import edu.internet2.middleware.openid.message.ParameterMap;
import edu.internet2.middleware.openid.message.encoding.EncodingException;
import edu.internet2.middleware.openid.message.encoding.EncodingUtils;
import edu.internet2.middleware.openid.message.encoding.MessageCodec;

/**
 * Abstract class for namespace-aware codecs.
 * 
 * @param <EncodedType> object type the codec produces
 */
public abstract class AbstractNamespaceAwareCodec<EncodedType> implements MessageCodec<EncodedType> {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(URLFormCodec.class);

    /** {@inheritDoc} */
    public ParameterMap decode(EncodedType encoded) throws EncodingException {
        Map<String, String> parameters = decodeMessage(encoded);

        ParameterMap parameterMap = new ParameterMap();
        NamespaceMap namespaces = parameterMap.getNamespaces();

        // build namespace map
        Iterator<String> keyIterator = parameters.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            String value = parameters.get(key);

            if (key.equals(OpenIDConstants.MESSAGE_NAMESPACE_PREFIX)) {
                // ns declaration (default namespace)
                log.debug("Registering default namespace: {}", value);
                namespaces.add(value, XMLConstants.DEFAULT_NS_PREFIX);
                keyIterator.remove();
            } else if (key.startsWith(OpenIDConstants.MESSAGE_NAMESPACE_PREFIX + ".")) {
                // ns.X declaration (extension namespace)
                key = key.substring(OpenIDConstants.MESSAGE_NAMESPACE_PREFIX.length() + 1);
                log.debug("Registering '{}' namespace: {}", key, value);
                namespaces.add(value, key);
                keyIterator.remove();
            }
        }

        // build ParameterMap
        for (String key : parameters.keySet()) {
            QName qname = EncodingUtils.decodeParameterName(key, namespaces);
            parameterMap.put(qname, parameters.get(key));
        }

        return parameterMap;
    }

    /**
     * Decode message into a simple key-value map of parameters.
     * 
     * @param encoded encoded message
     * @return map of parameters
     * @throws EncodingException if unable to decode the message
     */
    public abstract Map<String, String> decodeMessage(EncodedType encoded) throws EncodingException;

    /** {@inheritDoc} */
    public EncodedType encode(ParameterMap parameterMap) throws EncodingException {
        Map<String, String> parameters = EncodingUtils.flattenParameterNames(parameterMap);
        return encode(parameters);
    }

    /** {@inheritDoc} */
    public abstract EncodedType encode(Map<String, String> parameters) throws EncodingException;

}