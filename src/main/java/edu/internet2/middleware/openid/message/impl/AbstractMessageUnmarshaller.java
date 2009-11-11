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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.Configuration;
import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.extensions.MessageExtension;
import edu.internet2.middleware.openid.extensions.MessageExtensionUnmarshaller;
import edu.internet2.middleware.openid.extensions.MessageExtensionUnmarshallerFactory;
import edu.internet2.middleware.openid.message.Message;
import edu.internet2.middleware.openid.message.MessageBuilder;
import edu.internet2.middleware.openid.message.MessageBuilderFactory;
import edu.internet2.middleware.openid.message.io.MessageUnmarshaller;
import edu.internet2.middleware.openid.message.io.UnmarshallingException;

/**
 * Base class for OpenID message unmarshallers.
 * 
 * @param <MessageType> type of OpenID Message to be unmarshalled
 */
public abstract class AbstractMessageUnmarshaller<MessageType extends Message> implements
        MessageUnmarshaller<MessageType> {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(AbstractMessageUnmarshaller.class);

    /** Message builders. */
    private MessageBuilderFactory messageBuilders;

    /** Message extension unmarshallers. */
    private MessageExtensionUnmarshallerFactory extensionUnmarshallers;

    /** Constructor. */
    public AbstractMessageUnmarshaller() {
        messageBuilders = Configuration.getMessageBuilders();
        extensionUnmarshallers = Configuration.getExtensionUnmarshallers();
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
     * @throws UnmarshallingException thrown if an error occurs unmarshalling the Parameter Map into the OpenID Message
     */
    public void unmarshall(MessageType message, ParameterMap parameters) throws UnmarshallingException {
        unmarshallParameters(message, parameters);
        unmarshallExtensions(message, parameters);
    }

    /**
     * Unmarshall parameters into the message.
     * 
     * @param message Message to unmarshall parameters into
     * @param parameters parameter map to unmarshall
     * @throws UnmarshallingException thrown if an error occurs unmarshalling the Parameter Map into the OpenID Message
     */
    public abstract void unmarshallParameters(MessageType message, ParameterMap parameters)
            throws UnmarshallingException;

    /**
     * Unmarshall any message extensions attached to this message.
     * 
     * @param message message to unmarshall extensions to
     * @param parameters parameter map to unmarshall extensions from
     */
    protected void unmarshallExtensions(MessageType message, ParameterMap parameters) {
        for (String namespace : parameters.getNamespaces().getURIs()) {
            if (!OpenIDConstants.OPENID_20_NS.equals(namespace)) {
                MessageExtensionUnmarshaller unmarshaller = extensionUnmarshallers.getUnmarshaller(namespace);

                if (unmarshaller == null) {
                    log.error("Unable to find unmarshaller for message extension namespace: {}", namespace);
                    continue;
                }

                try {
                    log.info("unmarshalling message extension: {}", namespace);
                    ParameterMap extensionParameters = parameters.subMap(namespace);
                    MessageExtension extension = unmarshaller.unmarshall(extensionParameters);
                    message.getExtensions().put(namespace, extension);
                } catch (UnmarshallingException e) {
                    log.error("Error while unmarshalling message extension: {}", e.getMessage());
                }
            }
        }
    }

    /**
     * Build an OpenID message object.
     * 
     * @param parameters parameter map used to build message
     * @return constructed OpenID message
     * @throws UnmarshallingException if unable to build the message
     */
    protected MessageType buildMessage(ParameterMap parameters) throws UnmarshallingException {
        MessageBuilder builder = messageBuilders.getBuilder(parameters);

        if (builder == null) {
            log.error("Unable to find builder for parameter map: {}", parameters);
            throw new UnmarshallingException("Unable to find builder for parameter map: " + parameters);
        }

        return (MessageType) builder.buildObject();
    }

}