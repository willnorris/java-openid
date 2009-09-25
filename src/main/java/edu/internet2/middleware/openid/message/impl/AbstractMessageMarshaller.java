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

import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.common.OpenIDConstants.Parameter;
import edu.internet2.middleware.openid.message.Message;
import edu.internet2.middleware.openid.message.io.Marshaller;

/**
 * Base class for message marshallers.
 * 
 * @param <MessageType> type of OpenID Message to be marshalled
 */
public abstract class AbstractMessageMarshaller<MessageType extends Message> implements Marshaller<MessageType> {

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
            // do nothing
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

}