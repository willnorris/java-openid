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

package edu.internet2.middleware.openid.message.io;

import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.message.Message;

/**
 * Marshallers are used to marshall OpenID {@link Message} and {@link MessageExtensions}s into a map of parameters.
 * Marshallers <b>MUST</b> be thread-safe.
 * 
 * @param <MessageType> type of message this marshaller handles
 */
public interface Marshaller<MessageType extends Message> {

    /**
     * Marshall the object.
     * 
     * @param message object to marshall
     * @return the parameters
     * @throws MarshallingException thrown if an error occurs marshalling the OpenID Message into the Parameter Map
     */
    public ParameterMap marshall(MessageType message) throws MarshallingException;

}