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

package com.shibfaced.openid.message.encoding;

import java.util.Map;

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
     */
    public Map<String, String> decode(EncodedType encoded);

    /**
     * Encode the message.
     * 
     * @param parameters parameters to encode
     * @return encoded format
     */
    public EncodedType encode(Map<String, String> parameters);

}