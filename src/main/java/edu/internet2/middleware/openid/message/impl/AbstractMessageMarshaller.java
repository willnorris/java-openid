/*
 * Copyright [2009] [University Corporation for Advanced Internet Development, Inc.]
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
import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.common.OpenIDConstants.Parameter;
import edu.internet2.middleware.openid.extensions.MessageExtension;
import edu.internet2.middleware.openid.extensions.MessageExtensionMarshaller;
import edu.internet2.middleware.openid.extensions.MessageExtensionMarshallerFactory;
import edu.internet2.middleware.openid.message.Message;
import edu.internet2.middleware.openid.message.io.MarshallingException;
import edu.internet2.middleware.openid.message.io.MessageMarshaller;

/**
 * Base class for message marshallers.
 * 
 * @param <MessageType> type of OpenID Message to be marshalled
 */
public abstract class AbstractMessageMarshaller<MessageType extends Message> implements MessageMarshaller<MessageType> {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(AbstractMessageMarshaller.class);

    /** Message extension marshallers. */
    private MessageExtensionMarshallerFactory extensionMarshallers;

    /** Constructor. */
    public AbstractMessageMarshaller() {
        extensionMarshallers = Configuration.getExtensionMarshallers();
    }

    /** {@inheritDoc} */
    public ParameterMap marshall(MessageType message) {
        ParameterMap parameters = new ParameterMap();
        marshall(message, parameters);
        return parameters;
    }

    /**
     * Marshall message into the parameter map.
     * 
     * @param message message to marshall
     * @param parameters parameter map to marshall message into
     */
    protected void marshall(MessageType message, ParameterMap parameters) {
        try {
            parameters.put(Parameter.mode.QNAME, message.getMode());
        } catch (UnsupportedOperationException e) {
            // do nothing... some message do not implement getMode()
        }

        marshallParameters(message, parameters);
    }

    /**
     * Marshall message parameters into the parameter map.
     * 
     * @param message message to marshall
     * @param parameters parameter map to marshall message into
     */
    protected abstract void marshallParameters(MessageType message, ParameterMap parameters);

    /**
     * Marshall any message extensions attached to this message.
     * 
     * @param message message to marshall extensions from
     * @param parameters parameter map to marshall extensions to
     */
    protected void marshallExtensions(MessageType message, ParameterMap parameters) {
        for (MessageExtension extension : message.getExtensions().values()) {
            log.debug("marshalling extension: {}", extension.getNamespace());

            String namespace = extension.getNamespace();
            MessageExtensionMarshaller extensionMarshaller = extensionMarshallers.getMarshaller(namespace);

            if (extensionMarshaller == null) {
                log.error("Unable to find marshaller for message extenion namespace: {}", namespace);
                continue;
            }

            try {
                ParameterMap extensionParameters = extensionMarshaller.marshall(extension);
                log.debug("putting extension parameters onto message parameter map");
                parameters.putAll(extensionParameters);
            } catch (MarshallingException e) {
                log.error("Error while marshalling message extension: {}", e.getMessage());
            }
        }
    }

}