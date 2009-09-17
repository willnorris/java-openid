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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.message.ParameterMap;
import edu.internet2.middleware.openid.message.encoding.EncodingException;
import edu.internet2.middleware.openid.message.encoding.MessageCodec;
import edu.internet2.middleware.openid.util.StringUtils;

/**
 * Message encoder implementation which produces x-www-urlencoded strings. Message parameters in a URL encoded string
 * must be prefixed with the string "openid.". This codec will prefix all message parameters when encoding, and will
 * strip the prefix from all parameters when decoding. Any parameters that are not prefixed with "openid." are not part
 * of the OpenID message and will not be included in the decoded parameter map.
 */
public class URLFormCodec implements MessageCodec<String> {

    /** Prefix attached to each parameter of the encoded string. */
    private static final String PARAMETER_PREFIX = "openid";

    /** Prefix attached to each namespace alias declarations. */
    private static final String NAMESPACE_PREFIX = "ns";

    /** Codec singleton instance. */
    private static URLFormCodec singleton;

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(URLFormCodec.class);

    /**
     * Get singleton instance.
     * 
     * @return singleton instance
     */
    public static URLFormCodec getInstance() {
        if (singleton == null) {
            singleton = new URLFormCodec();
        }

        return singleton;
    }

    /** {@inheritDoc} */
    public ParameterMap decode(String encoded) throws EncodingException {
        log.debug("Decoding URL Form encoded string: {}", encoded);

        Map<String, String> namespaces = new HashMap<String, String>();
        Map<String, String> parameters = new HashMap<String, String>();

        // first, register namespace alias definitions, and separate them from other parameters
        for (String pair : encoded.split("&")) {
            String[] parts = pair.split("=", 2);
            if (parts.length == 2) {
                try {
                    String key = URLDecoder.decode(parts[0], "UTF-8");
                    if (key.startsWith(PARAMETER_PREFIX + ".")) {
                        key = key.substring(PARAMETER_PREFIX.length() + 1);
                        String value = URLDecoder.decode(parts[1], "UTF-8");

                        if (key.equals(NAMESPACE_PREFIX)) {
                            // openid.ns declaration (default namespace)
                            log.debug("Registering default namespace: {}", value);
                            namespaces.put(null, value);
                        } else if (key.startsWith(NAMESPACE_PREFIX + ".")) {
                            // openid.ns.X declaration (extension namespace)
                            key = key.substring(NAMESPACE_PREFIX.length() + 1);
                            log.debug("Registering '{}' namespace: {}", key, value);
                            namespaces.put(key, value);
                        } else {
                            // normal parameter
                            log.debug("Found encoded parameter {} = {}", key, value);
                            parameters.put(key, value);
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    log.error("UTF-8 encoding is not supported, this VM is not Java compliant.");
                    throw new EncodingException("Unable to decode message, UTF-8 encoding is not supported");
                }
            }
        }

        ParameterMap parameterMap = new ParameterMap();

        // then go back through the parameters and build QNames for each using the registered namespaces
        for (String key : parameters.keySet()) {
            QName qname;

            String[] parts = key.split("\\.", 2);
            if (parts.length == 2) {
                qname = new QName(namespaces.get(parts[0]), parts[1], parts[0]);
            } else {
                // default namespace
                qname = new QName(namespaces.get(null), parts[0]);
            }

            log.debug("Decoded parameter {} = {}", qname, parameters.get(key));
            parameterMap.put(qname, parameters.get(key));
        }

        return parameterMap;
    }

    /** {@inheritDoc} */
    public String encode(ParameterMap parameters) throws EncodingException {
        log.debug("Encoding ParameterMap containing {} entries", parameters.size());
        List<String> encodedParameters = new ArrayList<String>();
        Map<String, String> namespaces = new HashMap<String, String>();

        // assign an alias to all namespaces present in the parameter map
        for (QName key : parameters.keySet()) {
            String ns = key.getNamespaceURI();

            if (!namespaces.containsKey(ns)) {
                String alias;

                if (OpenIDConstants.OPENID_20_NS.equals(ns)) {
                    alias = null;
                } else if (key.getPrefix() != null) {
                    alias = key.getPrefix();
                } else {
                    alias = "n" + namespaces.size();
                }

                log.debug("Registering namespace alias {} = {}", alias, ns);
                namespaces.put(ns, alias);
            }
        }

        // append namespace alias declarations
        for (String ns : namespaces.keySet()) {
            String key = NAMESPACE_PREFIX;
            String alias = namespaces.get(ns);
            if (alias != null) {
                key += "." + alias;
            }
            encodedParameters.add(encodeParameter(key, ns));
        }

        for (QName qname : parameters.keySet()) {
            String key = qname.getLocalPart();
            String ns = namespaces.get(qname.getNamespaceURI());
            if (ns != null) {
                key = ns + "." + key;
            }

            log.debug("Encoding parameter {} = {}", key, parameters.get(qname));
            encodedParameters.add(encodeParameter(key, parameters.get(qname)));
        }

        String encodedString = StringUtils.join(encodedParameters, "&");
        log.debug("Encoded string: {}", encodedString);
        return encodedString;
    }

    /**
     * Build an encoded key value pair for inclusion into a URL form encoded OpenID message string.
     * 
     * @param key key name of parameter to encode
     * @param value value of parameter to encode
     * @return encoded key value pair
     * @throws EncodingException if error occurs while encoding
     */
    private String encodeParameter(String key, String value) throws EncodingException {
        StringBuffer buffer = new StringBuffer();

        try {
            buffer.append(URLEncoder.encode(PARAMETER_PREFIX + "." + key, "UTF-8"));
            buffer.append("=");
            buffer.append(URLEncoder.encode(value, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("UTF-8 encoding is not supported, this VM is not Java compliant.");
            throw new EncodingException("Unable to encode message, UTF-8 encoding is not supported");
        }

        return buffer.toString();
    }
}