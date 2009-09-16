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

package edu.internet2.middleware.openid.message;

import java.util.HashMap;

import edu.internet2.middleware.openid.message.Message.Parameter;

/**
 * A map of parameters that exist in a OpenID message.
 */
public class ParameterMap extends HashMap<String, String> {

    /** Serial Version UID. */
    private static final long serialVersionUID = 2956813358977663257L;

    /** Namespace these parameters are in. */
    private String namespace;

    /** Preferred prefix for the namespace these parameters are in. */
    private String prefix;

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the specified key.
     * 
     * @param parameter key whose presence in this map is to be tested
     * @return <tt>true</tt> if this map contains a mapping for the specified key
     */
    public boolean containsKey(Parameter parameter) {
        return containsKey(parameter.toString());
    }

    /**
     * Returns the value to which the specified key is mapped, or {@code null} if this map contains no mapping for the
     * key.
     * 
     * @param parameter the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or {@code null} if this map contains no mapping for the
     *         key
     */
    public String get(Parameter parameter) {
        return get(parameter.toString());
    }

    /**
     * Associates the specified value with the specified key in this map.
     * 
     * @param parameter key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or <tt>null</tt> if there was no mapping for
     *         <tt>key</tt>.
     */
    public String put(Parameter parameter, String value) {
        return put(parameter.toString(), value);
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * 
     * @param parameter key whose mapping is to be removed from the map
     * @return the previous value associated with key, or <tt>null</tt> if there was no mapping for key.
     */
    public String remove(Parameter parameter) {
        return remove(parameter.toString());
    }

    /**
     * Get the namespace for the parameters in this map.
     * 
     * @return the namespace for the parameters in this map
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * Set the namespace for the parameters in this map.
     * 
     * @param newNamespace the namespace for the parameters in this map
     */
    public void setNamespace(String newNamespace) {
        namespace = newNamespace;
    }

    /**
     * Get the preferred namespace prefix for the parameters in this map.
     * 
     * @return the preferred namespace prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Set the preferred namespace prefix for the parameters in this map.
     * 
     * @param newPrefix the preferred namespace prefix
     */
    public void setPrefix(String newPrefix) {
        prefix = newPrefix;
    }

}