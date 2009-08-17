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

package edu.internet2.middleware.openid.message;

import java.util.Map;

/**
 * Unmarshallers are used to unmarshall a collection of {@link Parameter}s into an OpenID {@link Message} or
 * {@link MessageExtension}.
 * 
 * @param <ObjectType> type of object this unmarshaller handles
 */
public interface Unmarshaller<ObjectType> {

    /**
     * Unmarshall the parameters.
     * 
     * @param parameters parameters
     * @return the OpenID message
     */
    public ObjectType unmarshall(Map<String, String> parameters);
}