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

package edu.internet2.middleware.openid.message;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.common.OpenIDConstants.Parameter;
import edu.internet2.middleware.openid.message.io.UnmarshallingException;

/**
 * Factory for OpenID message builders. Builders are registered using a {@link QName} that is made up of a namespace URI
 * representing the OpenID message version, and a local part which is the value of the OpenID message mode.
 */
public class MessageBuilderFactory {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(MessageBuilderFactory.class);

    /** Message marshallers. */
    private Map<QName, MessageBuilder> messageBuilders;

    /** Constructor. */
    public MessageBuilderFactory() {
        messageBuilders = new ConcurrentHashMap<QName, MessageBuilder>();
    }

    /**
     * Get marshaller for the specified key.
     * 
     * @param key key to get marshaller for
     * @return marshaller for the specified key
     */
    public MessageBuilder getBuilder(QName key) {
        if (key == null) {
            return null;
        }

        return messageBuilders.get(key);
    }

    /**
     * Get the builder for the specified parameter map. The builder will first try to be determined using the mode value
     * from the parameter map. If there is no mode value (as in the case of association and verification response
     * messages), the message type will be identified by other entries in the parameter map. This method assumes an
     * OpenID 2.0 message.
     * 
     * @param parameterMap parameter map of OpenID message to get builder for
     * @return message builder for the parameter map
     * @throws UnmarshallingException if unable to determine builder for the message
     */
    public MessageBuilder getBuilder(ParameterMap parameterMap) throws UnmarshallingException {
        String mode = parameterMap.get(Parameter.mode.QNAME);
        if (mode == null) {
            if (parameterMap.containsKey(Parameter.is_valid.QNAME)) {
                mode = OpenIDConstants.VERIFICATION_RESPONSE_MODE;
            } else if (parameterMap.containsKey(Parameter.error_code.QNAME)) {
                mode = OpenIDConstants.ASSOCIATION_ERROR_MODE;
            } else if (parameterMap.containsKey(Parameter.assoc_handle.QNAME)) {
                mode = OpenIDConstants.ASSOCIATION_RESPONSE_MODE;
            }
        }

        if (mode == null) {
            throw new UnmarshallingException("Unable to determine mode for parameter map: " + parameterMap);
        }

        QName qname = new QName(OpenIDConstants.OPENID_20_NS, mode);
        return getBuilder(qname);
    }

    /**
     * Get immutable map of all registered message builders.
     * 
     * @return all message builders
     */
    public Map<QName, MessageBuilder> getBuilders() {
        return Collections.unmodifiableMap(messageBuilders);
    }

    /**
     * Register a message builder.
     * 
     * @param key QName consisting of message namespace and mode value
     * @param builder builder to register.
     */
    public void registerBuilder(QName key, MessageBuilder builder) {
        log.debug("Registering marshaller, {}, for OpenID message mode {}", builder.getClass().getName(), key);
        messageBuilders.put(key, builder);
    }

    /**
     * Deregister a message builder.
     * 
     * @param key key of builder to deregeister
     * @return the deregistered builder
     */
    public MessageBuilder deregisterBuilder(QName key) {
        log.debug("Deregistering builder for object type {}", key);
        if (key != null) {
            return messageBuilders.remove(key);
        }

        return null;
    }

}