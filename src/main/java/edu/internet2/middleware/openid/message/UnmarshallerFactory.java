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
import edu.internet2.middleware.openid.common.OpenIDConstants.Parameter;

/**
 * Factory for OpenID message unmarshallers. Unmarshallers are registered using a {@link QName} that is made up of a
 * namespace URI representing the OpenID message version, and a local part which is the value of the OpenID message
 * mode.
 */
public class UnmarshallerFactory {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(UnmarshallerFactory.class);

    /** Message unmarshallers. */
    private Map<QName, Unmarshaller> messageUnmarshallers;

    /** Constructor. */
    public UnmarshallerFactory() {
        messageUnmarshallers = new ConcurrentHashMap<QName, Unmarshaller>();
    }

    /**
     * Get unmarshaller for the specified key.
     * 
     * @param key key to get unmarshaller for
     * @return unmarshaller for the specified key
     */
    public Unmarshaller getUnmarshaller(QName key) {
        if (key == null) {
            return null;
        }

        return messageUnmarshallers.get(key);
    }

    /**
     * Get the marshaller for the specified parameter map. The marshaller will first try to be determined using the mode
     * value from the parameter map. If there is no mode value (as in the case of association and verification response
     * messages), the message type will be identified by other entries in the parameter map. This method assumes an
     * OpenID 2.0 message.
     * 
     * @param parameterMap parameter map of OpenID message to get unmarshaller for
     * @return message unmarshaller for the parameter map
     * @throws UnmarshallingException if unable to determine unmarshaller for the message
     */
    public Unmarshaller getUnmarshaller(ParameterMap parameterMap) throws UnmarshallingException {
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
        return getUnmarshaller(qname);
    }

    /**
     * Get immutable map of all registered message unmarshallers.
     * 
     * @return all message unmarshallers
     */
    public Map<QName, Unmarshaller> getUnmarshallers() {
        return Collections.unmodifiableMap(messageUnmarshallers);
    }

    /**
     * Register a message unmarshallers.
     * 
     * @param key QName consisting of message namespace and mode value
     * @param unmarshaller unmarshaller to register.
     */
    public void registerUnmarshaller(QName key, Unmarshaller unmarshaller) {
        log.debug("Registering unmmarshaller, {}, for OpenID message mode {}", unmarshaller.getClass().getName(), key);
        if (key != null) {
            messageUnmarshallers.put(key, unmarshaller);
        }
    }

    /**
     * Deregister a message unmarshaller.
     * 
     * @param key key of unmarshaller to deregeister
     * @return the deregistered unmarshaller
     */
    public Unmarshaller deregisterUnmarshaller(QName key) {
        log.debug("Deregistering unmmarshaller for object type {}", key);
        if (key != null) {
            return messageUnmarshallers.remove(key);
        }

        return null;
    }

}