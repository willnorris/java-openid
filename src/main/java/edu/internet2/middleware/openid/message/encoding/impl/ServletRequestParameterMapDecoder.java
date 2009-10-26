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

import java.util.HashMap;
import java.util.Map;

import edu.internet2.middleware.openid.message.encoding.EncodingException;

/**
 * Message Codec implementation that decodes parameter maps retrieved from Servlet Requests. This implementation does
 * not support message encoding.
 */
public class ServletRequestParameterMapDecoder extends AbstractNamespaceAwareCodec<Map<String, String[]>> {

    /** Prefix attached to each parameter of the encoded string. */
    private static final String PARAMETER_PREFIX = "openid";

    /** Decoder single instance. */
    private static ServletRequestParameterMapDecoder singleton;

    /**
     * Get singleton instance.
     * 
     * @return singleton instance
     */
    public static ServletRequestParameterMapDecoder getInstance() {
        if (singleton == null) {
            singleton = new ServletRequestParameterMapDecoder();
        }

        return singleton;
    }

    /** {@inheritDoc} */
    public Map<String, String> decodeMessage(Map<String, String[]> encoded) {
        Map<String, String> parameters = new HashMap<String, String>();

        for (String parameter : encoded.keySet()) {
            if (parameter.startsWith(PARAMETER_PREFIX + ".")) {
                String key = parameter.substring(PARAMETER_PREFIX.length() + 1);
                String[] values = encoded.get(parameter);
                if (values.length > 0) {
                    parameters.put(key, values[0]);
                }
            }
        }

        return parameters;
    }

    /** {@inheritDoc} */
    public Map<String, String[]> encode(Map<String, String> parameters) {
        throw new UnsupportedOperationException("This class does not implement message encoding.");
    }

}