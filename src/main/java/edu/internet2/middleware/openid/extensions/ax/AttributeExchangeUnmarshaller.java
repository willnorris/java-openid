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
 * Unmarshaller for a specific Attribute Exchange message.
 * 
 * @param <MessageType> type of AX message this unmarshaller handles
 */
public interface AttributeExchangeUnmarshaller<MessageType extends AttributeExchangeMessage> {

    /**
     * Unmarshall the given parameter map into the message.
     * 
     * @param message message to unmarshall into
     * @param parameters parameter map to unmarshall
     */
    public void unmarshall(MessageType message, ParameterMap parameters);

}