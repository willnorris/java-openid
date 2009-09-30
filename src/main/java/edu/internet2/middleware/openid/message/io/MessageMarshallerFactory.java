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

package edu.internet2.middleware.openid.message.io;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.message.AssociationError;
import edu.internet2.middleware.openid.message.AssociationResponse;
import edu.internet2.middleware.openid.message.Message;
import edu.internet2.middleware.openid.message.VerifyResponse;

/**
 * Factory for OpenID message marshallers. Marshallers are registered using a {@link QName} that is made up of a
 * namespace URI representing the OpenID message version, and a local part which is the value of the OpenID message
 * mode.
 */
public class MessageMarshallerFactory {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(MessageMarshallerFactory.class);

    /** Message marshallers. */
    private Map<QName, MessageMarshaller> messageMarshallers;

    /** Constructor. */
    public MessageMarshallerFactory() {
        messageMarshallers = new ConcurrentHashMap<QName, MessageMarshaller>();
    }

    /**
     * Get marshaller for the specified key.
     * 
     * @param key key to get marshaller for
     * @return marshaller for the specified key
     */
    public MessageMarshaller getMarshaller(QName key) {
        if (key == null) {
            return null;
        }

        return messageMarshallers.get(key);
    }

    /**
     * Get the marshaller for the specified message. The marshaller will first try to be determined using the message
     * namespace and mode value from the message. If there is no mode value (as in the case of association and
     * verification response messages), the message type will be identified by the message class.
     * 
     * @param message OpenID message to get marshaller for
     * @return message marshaller for the message
     * @throws MarshallingException if unable to determine marshaller for the message
     */
    public MessageMarshaller getMarshaller(Message message) throws MarshallingException {
        String mode = null;

        try {
            mode = message.getMode();
        } catch (UnsupportedOperationException e) {
            if (message instanceof AssociationResponse) {
                mode = OpenIDConstants.ASSOCIATION_RESPONSE_MODE;
            } else if (message instanceof AssociationError) {
                mode = OpenIDConstants.ASSOCIATION_ERROR_MODE;
            } else if (message instanceof VerifyResponse) {
                mode = OpenIDConstants.VERIFICATION_RESPONSE_MODE;
            }
        }

        if (mode == null) {
            throw new MarshallingException("Unable to determine mode for message: " + message);
        }

        return getMarshaller(new QName(message.getNamespace(), mode));
    }

    /**
     * Get immutable map of all registered message marshallers.
     * 
     * @return all message marshallers
     */
    public Map<QName, MessageMarshaller> getMarshallers() {
        return Collections.unmodifiableMap(messageMarshallers);
    }

    /**
     * Register a message marshallers.
     * 
     * @param key QName consisting of message namespace and mode value
     * @param marshaller marshaller to register.
     */
    public void registerMarshaller(QName key, MessageMarshaller marshaller) {
        log.debug("Registering marshaller, {}, for OpenID message mode {}", marshaller.getClass().getName(), key);
        messageMarshallers.put(key, marshaller);
    }

    /**
     * Deregister a message marshaller.
     * 
     * @param key key of marshaller to deregeister
     * @return the deregistered marshaller
     */
    public MessageMarshaller deregisterMarshaller(QName key) {
        log.debug("Deregistering marshaller for object type {}", key);
        if (key != null) {
            return messageMarshallers.remove(key);
        }

        return null;
    }

}