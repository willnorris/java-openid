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

package edu.internet2.middleware.openid.extensions;

import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.message.io.UnmarshallingException;

/**
 * Base class for message extension unmarshallers.
 * 
 * @param <MessageExtensionType> type of message this unmarshaller handles
 */
public abstract class AbstractMessageExtensionUnmarshaller<MessageExtensionType extends MessageExtension> implements
        MessageExtensionUnmarshaller<MessageExtensionType> {

    /** {@inheritDoc} */
    public MessageExtensionType unmarshall(ParameterMap parameters) throws UnmarshallingException {
        return null;
    }

    /**
     * Marshall message parameters into the parameter map.
     * 
     * @param message message to marshall
     * @param parameters parameter map to marshall message into
     */
    public abstract void marshall(MessageExtensionType message, ParameterMap parameters);

}