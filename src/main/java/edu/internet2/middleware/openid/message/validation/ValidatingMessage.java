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

import java.util.Set;

import edu.internet2.middleware.openid.message.Message;

/**
 * A functional interface for Messages that offer the ability to evaluate validation rules.
 */
public interface ValidatingMessage extends Message {

    /**
     * Get the set of validators for the message.
     * 
     * @return validators for the message
     */
    public Set<MessageValidator> getValidators();

    /**
     * Register a validator for the message.
     * 
     * @param validator validator to register
     */
    public void registerValidator(MessageValidator validator);

    /**
     * Deregister a validator for the message.
     * 
     * @param validator validator to deregister
     */
    public void deregisterValidator(MessageValidator validator);

    /**
     * Validate the message against all registered validators.
     * 
     * @throws ValidationException if message is not valid
     */
    public void validate() throws ValidationException;

}