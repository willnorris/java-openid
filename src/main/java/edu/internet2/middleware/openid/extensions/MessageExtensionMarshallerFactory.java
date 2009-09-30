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
 * Factory for OpenID message extension marshallers. Marshallers are registered using the namespace URI of the
 * extension.
 */
public class MessageExtensionMarshallerFactory {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(MessageExtensionMarshallerFactory.class);

    /** Message marshallers. */
    private Map<String, MessageExtensionMarshaller> messageMarshallers;

    /** Constructor. */
    public MessageExtensionMarshallerFactory() {
        messageMarshallers = new ConcurrentHashMap<String, MessageExtensionMarshaller>();
    }

    /**
     * Get marshaller for the specified key.
     * 
     * @param key key to get marshaller for
     * @return marshaller for the specified key
     */
    public MessageExtensionMarshaller getMarshaller(String key) {
        if (key == null) {
            return null;
        }

        return messageMarshallers.get(key);
    }

    /**
     * Get immutable map of all registered message marshallers.
     * 
     * @return all message marshallers
     */
    public Map<String, MessageExtensionMarshaller> getMarshallers() {
        return Collections.unmodifiableMap(messageMarshallers);
    }

    /**
     * Register a message marshaller.
     * 
     * @param key namespace URI of the message extension
     * @param marshaller marshaller to register.
     */
    public void registerMarshaller(String key, MessageExtensionMarshaller marshaller) {
        log.debug("Registering marshaller, {}, for OpenID message mode {}", marshaller.getClass().getName(), key);
        messageMarshallers.put(key, marshaller);
    }

    /**
     * Deregister a message marshaller.
     * 
     * @param key key of marshaller to deregeister
     * @return the deregistered marshaller
     */
    public MessageExtensionMarshaller deregisterMarshaller(String key) {
        log.debug("Deregistering builder for object type {}", key);
        if (key != null) {
            return messageMarshallers.remove(key);
        }

        return null;
    }

}