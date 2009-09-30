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
 * Factory for OpenID message extension builders. Builders are registered using the namespace URI of the extension.
 */
public class MessageExtensionBuilderFactory {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(MessageExtensionBuilderFactory.class);

    /** Message builders. */
    private Map<String, MessageExtensionBuilder> messageBuilders;

    /** Constructor. */
    public MessageExtensionBuilderFactory() {
        messageBuilders = new ConcurrentHashMap<String, MessageExtensionBuilder>();
    }

    /**
     * Get builder for the specified key.
     * 
     * @param key key to get builder for
     * @return builder for the specified key
     */
    public MessageExtensionBuilder getBuilder(String key) {
        if (key == null) {
            return null;
        }

        return messageBuilders.get(key);
    }

    /**
     * Get immutable map of all registered message builders.
     * 
     * @return all message builders
     */
    public Map<String, MessageExtensionBuilder> getBuilders() {
        return Collections.unmodifiableMap(messageBuilders);
    }

    /**
     * Register a message builder.
     * 
     * @param key QName consisting of message namespace and mode value
     * @param builder builder to register.
     */
    public void registerBuilder(String key, MessageExtensionBuilder builder) {
        log.debug("Registering builder {} for OpenID message extension {}", builder.getClass().getName(), key);
        messageBuilders.put(key, builder);
    }

    /**
     * Deregister a message builder.
     * 
     * @param key key of builder to deregeister
     * @return the deregistered builder
     */
    public MessageExtensionBuilder deregisterBuilder(String key) {
        log.debug("Deregistering builder for message extension {}", key);
        if (key != null) {
            return messageBuilders.remove(key);
        }

        return null;
    }

}