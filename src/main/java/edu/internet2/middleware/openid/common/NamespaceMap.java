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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Maintains a mapping of aliases for namespace URIs.
 */
public class NamespaceMap {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(NamespaceMap.class);

    /** Namespace map. */
    private Map<String, String> namespaces;

    /** Counter to ensure generated namespaces are unique. */
    private int namespaceGeneratorCount;

    /** Alias prefix. */
    private String aliasPrefix;

    /** Constructor. */
    public NamespaceMap() {
        namespaces = new HashMap<String, String>();
        namespaceGeneratorCount = 0;
        aliasPrefix = "n";
    }

    /**
     * Get the alias prefix.
     * 
     * @return the alias prefix
     */
    public String getAliasPrefix() {
        return aliasPrefix;
    }

    /**
     * Set the alias prefix.
     * 
     * @param prefix the alias prefix
     */
    public void setAliasPrefix(String prefix) {
        aliasPrefix = prefix;
    }

    /**
     * Does a mapping exist for the specified namespace URI.
     * 
     * @param uri namespace URI to check for
     * @return whether a mapping exists for the namespace URI
     */
    public boolean containsURI(String uri) {
        return namespaces.containsKey(uri);
    }

    /**
     * Does a mapping exist for the specified namespace alias.
     * 
     * @param alias namespace prefix to check for
     * @return whether a mapping exists for the namespace URI
     */
    public boolean containsAlias(String alias) {
        return namespaces.containsValue(alias);
    }

    /**
     * Get the alias for the specified namespace URI.
     * 
     * @param uri namespace URI to get alias for
     * @return alias for the specified namespace URI
     */
    public String getAlias(String uri) {
        return namespaces.get(uri);
    }

    /**
     * Get the URI for the specified namespace alias.
     * 
     * @param alias namespace alias to get URI for
     * @return URI for the specified namespace alias
     */
    public String getURI(String alias) {
        for (Map.Entry<String, String> entry : namespaces.entrySet()) {
            if (entry.getValue().equals(alias)) {
                return entry.getKey();
            }
        }

        return null;
    }

    /**
     * Get all aliases registered in this namespace map.
     * 
     * @return set of all registered namespace aliases
     */
    public Set<String> getAliases() {
        return new HashSet(namespaces.values());
    }

    /**
     * Get all URIs registered in this namespace map.
     * 
     * @return set of all registered namespace URIs
     */
    public Set<String> getURIs() {
        return namespaces.keySet();
    }

    /**
     * Add the specified URI to this map. A namespace alias will be automatically generated for this URI if one does not
     * already exist.
     * 
     * @param uri namespace URI to add
     * @return alias for the added namespace URI
     */
    public String add(String uri) {
        if (containsURI(uri)) {
            return getAlias(uri);
        }

        return add(uri, generateNamespaceAlias());
    }

    /**
     * Register the namespace from the specified QName. If the QName includes a prefix, it will try to be registered for
     * the namespace URI. If an alias by that name already exists, a new alias will be automatically generated.
     * 
     * @param qname QName containing namespace URI to add
     * @return alias for the added namespace URI
     */
    public String add(QName qname) {
        return add(qname.getNamespaceURI(), qname.getPrefix());
    }

    /**
     * Register a namespace URI with the specified alias. If the alias is already registered to a different URI, a new
     * alias will be automatically generated.
     * 
     * @param uri namespace URI to add
     * @param desiredAlias alias to use for added URI, if it is not already registered
     * @return alias for the added namespace URI
     */
    public String add(String uri, String desiredAlias) {
        if (!containsURI(uri)) {
            String alias = desiredAlias;

            if (alias == null) {
                alias = XMLConstants.DEFAULT_NS_PREFIX;
            }

            while (containsAlias(alias)) {
                alias = generateNamespaceAlias();
            }

            namespaces.put(uri, alias);
        }

        return getAlias(uri);
    }

    /**
     * Unregister the specified namespace URI.
     * 
     * @param uri namespace to unregister
     */
    public void remove(String uri) {
        namespaces.remove(uri);
    }

    /**
     * Generate a unique namespace alias.
     * 
     * @return unique namespace alias
     */
    protected String generateNamespaceAlias() {
        return aliasPrefix + (namespaceGeneratorCount++);
    }

}