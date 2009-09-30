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

package edu.internet2.middleware.openid.extensions;

import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.message.Message;
import edu.internet2.middleware.openid.message.io.UnmarshallingException;

/**
 * Unmarshallers are used to unmarshall a {@link ParameterMap} into an OpenID {@link Message} or
 * {@link MessageExtension}.
 * 
 * @param <MessageExtensionType> type of object this unmarshaller handles
 */
public interface MessageExtensionUnmarshaller<MessageExtensionType extends MessageExtension> {

    /**
     * Unmarshall the parameters.
     * 
     * @param parameters parameters
     * @return the OpenID message
     * @throws UnmarshallingException thrown if an error occurs unmarshalling the Parameter Map into the OpenID Message
     */
    public MessageExtensionType unmarshall(ParameterMap parameters) throws UnmarshallingException;

}