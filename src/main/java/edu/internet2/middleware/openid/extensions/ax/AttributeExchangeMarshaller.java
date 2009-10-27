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

package edu.internet2.middleware.openid.extensions.ax;

import edu.internet2.middleware.openid.common.ParameterMap;

/**
 * Marshaller for a specific Attribute Exchange message.
 * 
 * @param <MessageType> type of AX message this marshaller handles
 */
public interface AttributeExchangeMarshaller<MessageType extends AttributeExchangeMessage> {

    /**
     * Marshall the given message into the parameter map.
     * 
     * @param message message to marshall
     * @param parameters parameter map to marshall message into
     */
    public void marshall(MessageType message, ParameterMap parameters);

}