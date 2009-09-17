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

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.Configuration;
import edu.internet2.middleware.openid.message.Message;
import edu.internet2.middleware.openid.message.MessageBuilder;
import edu.internet2.middleware.openid.message.ParameterMap;
import edu.internet2.middleware.openid.message.Unmarshaller;
import edu.internet2.middleware.openid.message.UnmarshallingException;
import edu.internet2.middleware.openid.message.Message.Parameter;

/**
 * Base class for OpenID message unmarshallers.
 * 
 * @param <MessageType> type of OpenID Message to be unmarshalled
 */
public abstract class AbstractMessageUnmarshaller<MessageType extends Message> implements Unmarshaller<MessageType> {

    /** Logger. */
    private static final Logger LOG = LoggerFactory.getLogger(AbstractMessageUnmarshaller.class);

    /** Message builders. */
    private Map<String, MessageBuilder> messageBuilders;

    /** Constructor. */
    public AbstractMessageUnmarshaller() {
        messageBuilders = Configuration.getBuilders();
    }

    /** {@inheritDoc} */
    public MessageType unmarshall(ParameterMap parameters) throws UnmarshallingException {
        MessageType message = buildMessage(parameters);
        unmarshall(message, parameters);
        return message;
    }

    /**
     * Unmarshall parameter map into the message.
     * 
     * @param message Message to unmarshall parameters into
     * @param parameters parameter map to unmarshall
     */
    public void unmarshall(MessageType message, ParameterMap parameters) {
        unmarshallParameters(message, parameters);
    }

    /**
     * Unmarshall parameters into the message.
     * 
     * @param message Message to unmarshall parameters into
     * @param parameters parameter map to unmarshall
     */
    public abstract void unmarshallParameters(MessageType message, ParameterMap parameters);

    /**
     * Build an OpenID message object.
     * 
     * @param parameters parameter map used to build message
     * @return constructed OpenID message
     * @throws UnmarshallingException if unable to build the message
     */
    protected MessageType buildMessage(ParameterMap parameters) throws UnmarshallingException {
        String mode = parameters.get(Parameter.mode);
        MessageBuilder builder = messageBuilders.get(mode);

        if (builder == null) {
            LOG.error("Unable to find builder for mode: {}", mode);
            throw new UnmarshallingException("Unable to find builder for mode: " + mode);
        }

        return (MessageType) builder.buildObject();
    }
}