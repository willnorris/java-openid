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

package edu.internet2.middleware.openid.message.encoding;

import java.util.Map;

import edu.internet2.middleware.openid.message.ParameterMap;

/**
 * Encodes and decodes a map of OpenID message parameters into a transport specific format.
 * 
 * @param <EncodedType> object type the codec produces
 */
public interface MessageCodec<EncodedType> {

    /**
     * Decode the message.
     * 
     * @param encoded encoded string
     * @return map of parameters
     * @throws EncodingException if unable to decode message
     */
    public ParameterMap decode(EncodedType encoded) throws EncodingException;

    /**
     * Encode the message.
     * 
     * @param parameters parameters to encode
     * @return encoded format
     * @throws EncodingException if unable to encode message
     */
    public EncodedType encode(ParameterMap parameters) throws EncodingException;

    /**
     * Encode a simple key-value map of parameters.
     * 
     * @param parameters map of parameters
     * @return encoded message
     * @throws EncodingException if unable to encode message
     */
    public EncodedType encode(Map<String, String> parameters) throws EncodingException;

}