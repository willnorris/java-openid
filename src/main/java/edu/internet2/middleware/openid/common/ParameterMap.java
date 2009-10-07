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

package edu.internet2.middleware.openid.common;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A map of parameters that make up an OpenID message.
 * 
 * Parameters are stored as {@link QName}s that consist of the parameter's namespace and name. For parameters that are
 * part of the core OpenID specification, the namespace should correspond to the OpenID protocol version. For parameters
 * that are defined by an OpenID extension, the parameter namespace should correspond with the namespace of that
 * extension. A preferred prefix may be included as the prefix portion of the {@link QName}. If different parameters
 * declare different prefixes for the same namespace, only one will be used.
 */
public class ParameterMap extends LinkedHashMap<QName, String> {

    /** Serial Version UID. */
    private static final long serialVersionUID = 2956813358977663257L;

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(ParameterMap.class);

    /** Registered namespaces. */
    private NamespaceMap namespaces;

    /** Constructor. */
    public ParameterMap() {
        super();
        namespaces = new NamespaceMap();
    }

    /** {@inheritDoc} */
    public String put(QName key, String value) {
        log.debug("adding parameter to map {} = {}", key, value);
        namespaces.add(key);
        return super.put(key, value);
    }

    /** {@inheritDoc} */
    public void putAll(Map<? extends QName, ? extends String> m) {
        for (QName key : m.keySet()) {
            log.debug("adding parameter to map {} = {}", key, m.get(key));
            namespaces.add(key);
        }
        super.putAll(m);
    }

    /**
     * Get map of namespaces used in this ParameterMap.
     * 
     * @return namespace map
     */
    public NamespaceMap getNamespaces() {
        return namespaces;
    }

    /**
     * Get a submap containing only those parameters in the given namespace.
     * 
     * TODO: this should probably be using a sub-view pattern, rather than creating whole new map
     * 
     * @param namespace namespace URI of parameters to include in submap
     * @return parameter submap
     */
    public ParameterMap subMap(String namespace) {
        log.debug("getting parameter sub map for namespace: {}", namespace);

        ParameterMap map = new ParameterMap();

        if (namespace != null && namespaces.containsURI(namespace)) {
            map.getNamespaces().add(namespace, namespaces.getAlias(namespace));
            for (QName qname : this.keySet()) {
                if (namespace.equals(qname.getNamespaceURI())) {
                    map.put(qname, this.get(qname));
                }
            }
        }
        
        return map;
    }

}