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

package edu.internet2.middleware.openid.message.impl;

import java.util.Set;

import edu.internet2.middleware.openid.Configuration;
import edu.internet2.middleware.openid.message.MessageBuilder;
import edu.internet2.middleware.openid.message.validation.MessageValidator;
import edu.internet2.middleware.openid.message.validation.ValidatingMessage;

/**
 * Base class for builders of validating messages. Produced messages will have any configured {@link MessageValidator}s
 * attached to them.
 * 
 * @param <MessageType> the Message type that this builder produces
 */
public abstract class AbstractValidatingMessageBuilder<MessageType extends ValidatingMessage> implements
        MessageBuilder<MessageType> {

    /**
     * Creates a Message object and attaches any configured {@link MessageValidator}s.
     * 
     * @return the constructed Message
     */
    public MessageType buildObject() {
        MessageType message = buildMessage();

        for (MessageValidator validator : getValidators()) {
            message.registerValidator(validator);
        }

        return message;
    }

    /**
     * Get the set of validators that will be attached to all messages produced by this builder. The default behavior is
     * to attach all validators registered with the {@linkplain Configuration#getMessageValidators() global validator
     * factory} for the class returned by {@link #getType()}. Builders that extend this class can override this method
     * to control which validators are attached to messages.
     * 
     * @return validators attached to all produced messages
     */
    protected Set<MessageValidator> getValidators() {
        return Configuration.getMessageValidators().getValidators(getType());
    }

    /**
     * Build message object.
     * 
     * @return message
     */
    public abstract MessageType buildMessage();

    /**
     * Get the type of message this builder produces.
     * 
     * @return message type
     */
    public abstract Class<MessageType> getType();

}