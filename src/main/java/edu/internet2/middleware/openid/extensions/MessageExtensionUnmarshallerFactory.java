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

package edu.internet2.middleware.openid.extensions;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory for OpenID message extension unmarshallers. Unmarshallers are registered using the namespace URI of the
 * extension.
 */
public class MessageExtensionUnmarshallerFactory {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(MessageExtensionUnmarshallerFactory.class);

    /** Unmarshallers. */
    private Map<String, MessageExtensionUnmarshaller> unmarshallers;

    /** Constructor. */
    public MessageExtensionUnmarshallerFactory() {
        unmarshallers = new ConcurrentHashMap<String, MessageExtensionUnmarshaller>();
    }

    /**
     * Get unmarshaller for the specified key.
     * 
     * @param key key to get unmarshaller for
     * @return unmarshaller for the specified key
     */
    public MessageExtensionUnmarshaller getUnmarshaller(String key) {
        if (key == null) {
            return null;
        }

        return unmarshallers.get(key);
    }

    /**
     * Get immutable map of all registered message unmarshallers.
     * 
     * @return all message unmarshallers
     */
    public Map<String, MessageExtensionUnmarshaller> getUnmarshallers() {
        return Collections.unmodifiableMap(unmarshallers);
    }

    /**
     * Register a message unmarshaller.
     * 
     * @param key namespace URI of the message extension
     * @param unmarshaller unmarshaller to register.
     */
    public void registerUnmarshaller(String key, MessageExtensionUnmarshaller unmarshaller) {
        log.debug("Registering marshaller, {}, for OpenID message mode {}", unmarshaller.getClass().getName(), key);
        unmarshallers.put(key, unmarshaller);
    }

    /**
     * Deregister a message unmarshaller.
     * 
     * @param key key of unmarshaller to deregister
     * @return the deregistered unmarshaller
     */
    public MessageExtensionUnmarshaller deregisterUnmarshaller(String key) {
        log.debug("Deregistering builder for object type {}", key);
        if (key != null) {
            return unmarshallers.remove(key);
        }

        return null;
    }

}