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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.message.NamespaceMap;
import edu.internet2.middleware.openid.message.ParameterMap;
import edu.internet2.middleware.openid.util.StringUtils;

/**
 * Encoding Utilities.
 */
public final class EncodingUtils {

    /** Logger. */
    private static final Logger log = LoggerFactory.getLogger(EncodingUtils.class);

    /** Constructor. */
    private EncodingUtils() {
    }

    /**
     * Flatten the parameter map. Each parameter QName is converted into it's dotted namespace qualified form, and
     * namespace declarations are added to the resulting parameter map. The resulting map is suitable for passing to
     * {@link MessageCodec#encode(Map<String, String>)}.
     * 
     * @param parameterMap parameter map to flatten
     * @return parameter map with flattened parameter names and namespace declarations
     */
    public static Map<String, String> flattenParameterNames(ParameterMap parameterMap) {
        Map<String, String> parameters = new LinkedHashMap<String, String>();
        NamespaceMap namespaces = parameterMap.getNamespaces();

        // add namespaces to key-value parameter map
        for (String ns : namespaces.getURIs()) {
            String key = OpenIDConstants.MESSAGE_NAMESPACE_PREFIX;
            String alias = namespaces.getAlias(ns);

            if (alias != XMLConstants.DEFAULT_NS_PREFIX) {
                key += "." + alias;
            }

            parameters.put(key, ns);
        }

        // add parameters to key-value parameter map
        for (QName qname : parameterMap.keySet()) {
            String key = encodeParameterName(qname, namespaces);
            parameters.put(key, parameterMap.get(qname));
        }

        return parameters;
    }

    /**
     * Build an appropriate {@link QName} for a namespaced parameter name.
     * 
     * @param parameter namespaced parameter name
     * @param namespaces map of registered namespaces
     * @return QName for the parameter
     */
    public static QName decodeParameterName(String parameter, NamespaceMap namespaces) {
        String[] parts = parameter.split("\\.", 2);

        // check if parameter name is for a namespace declaration
        if (OpenIDConstants.MESSAGE_NAMESPACE_PREFIX.equals(parts[0])) {
            if (parts.length == 2) {
                return new QName(namespaces.getURI(parts[1]), "", parts[1]);
            } else {
                return new QName(namespaces.getURI(XMLConstants.DEFAULT_NS_PREFIX), "");
            }
        }

        // otherwise, parameter name is for a message parameter
        if (parts.length == 2) {
            return new QName(namespaces.getURI(parts[0]), parts[1], parts[0]);
        } else {
            // default namespace
            return new QName(namespaces.getURI(XMLConstants.DEFAULT_NS_PREFIX), parts[0]);
        }
    }

    /**
     * Build an appropriate namespaced parameter name for a {@link QName}.
     * 
     * @param qname QName to convert into parameter name
     * @param namespaces map of registered namespaces
     * @return namespaced parameter name
     */
    public static String encodeParameterName(QName qname, NamespaceMap namespaces) {
        String parameter = qname.getLocalPart();
        String ns = namespaces.getAlias(qname.getNamespaceURI());

        if (parameter.isEmpty()) {
            // parameter name is for a namespace declaration
            parameter = "ns";

            if (ns != XMLConstants.DEFAULT_NS_PREFIX) {
                parameter += "." + ns;
            }
        } else {
            // parameter name is for a message parameter
            if (ns != XMLConstants.DEFAULT_NS_PREFIX) {
                parameter = ns + "." + parameter;
            }
        }

        return parameter;
    }

    /**
     * Encode the parameter QNames into a comma separated list of encoded parameter names.
     * 
     * @param signedFields list of parameter QNames
     * @param namespaces map of registered namespaces
     * @return comma separated list of encoded parameter names
     */
    public static String encodeSignedFields(List<QName> signedFields, NamespaceMap namespaces) {
        List<String> encodedFields = new ArrayList<String>();

        for (QName field : signedFields) {
            encodedFields.add(encodeParameterName(field, namespaces));
        }

        return StringUtils.join(encodedFields, ",");
    }

    public static List<QName> decodeSignedFields(String signedFields, NamespaceMap namespaces) {
        List<QName> decodedFields = new ArrayList<QName>();

        if (signedFields != null) {
            for (String field : signedFields.split(",")) {
                decodedFields.add(decodeParameterName(field, namespaces));
            }
        }

        return decodedFields;
    }
}