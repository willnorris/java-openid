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

package edu.internet2.middleware.openid.message.validation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.message.Message;

/**
 * Factory for OpenID message validators. Validators are registered using a {@link Message} sub-class that the validator
 * is for.
 */
public class MessageValidatorFactory extends ConcurrentHashMap<Class<? extends Message>, Set<MessageValidator>> {

    /** Serial Version UID. */
    private static final long serialVersionUID = 5068949542963714940L;

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(MessageValidatorFactory.class);

    /**
     * Get the message validators for the given class.
     * 
     * @param clazz class to get message validators for
     * @return message validators for the given class
     */
    public Set<MessageValidator> getValidators(Class<? extends Message> clazz) {
        if (clazz == null || !containsKey(clazz)) {
            return Collections.EMPTY_SET;
        }

        return get(clazz);
    }

    /**
     * Register a message validator.
     * 
     * @param clazz QName consisting of message namespace and mode value
     * @param validator unmarshaller to register.
     * @param <T> Message type
     */
    public <T extends Message> void registerValidator(Class<? extends T> clazz, MessageValidator<T> validator) {
        log.debug("Registering validator {} for OpenID message class {}", validator.getClass().getName(), clazz);

        if (clazz != null) {
            if (!containsKey(clazz)) {
                put(clazz, new HashSet<MessageValidator>());
            }
            get(clazz).add(validator);
        }
    }

}