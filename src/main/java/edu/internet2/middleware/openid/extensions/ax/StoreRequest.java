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

package edu.internet2.middleware.openid.extensions.ax;

import java.util.List;
import java.util.Map;

/**
 * Request for an OpenID Provider to store identity information for a user.
 */
public interface StoreRequest extends AttributeExchangeMessage {

    /**
     * Attribute Exchange mode for a store request.
     */
    public static final String MODE = "store_request";

    /**
     * The attributes to store.
     * 
     * @return the attributes
     */
    public Map<String, List<String>> getAttributes();

}