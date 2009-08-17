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

package com.shibfaced.openid.message.impl;

import java.util.HashMap;
import java.util.Map;

import com.shibfaced.openid.message.Marshaller;
import com.shibfaced.openid.message.Message;
import com.shibfaced.openid.message.Message.Parameter;

/**
 * AbstractMessageMarshaller.
 * 
 * @param <MessageType> type of OpenID Message to be marshalled
 */
public abstract class AbstractMessageMarshaller<MessageType extends Message> implements Marshaller<MessageType> {

    /** {@inheritDoc} */
    public Map<String, String> marshall(MessageType message) {
        Map<String, String> parameters = new HashMap<String, String>();

        parameters.put(Parameter.ns.toString(), message.getNamespace());

        try {
            parameters.put(Parameter.mode.toString(), message.getMode());
        } catch (UnsupportedOperationException e) {
            // do nothing
        }

        parameters.putAll(marshallMessage(message));

        return parameters;
    }

    /**
     * Perform message type specific marshalling.
     * 
     * @param message message to marshall
     * @return message type specific parameter map
     */
    protected abstract Map<String, String> marshallMessage(MessageType message);

}