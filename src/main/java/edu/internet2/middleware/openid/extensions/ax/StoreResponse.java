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

import edu.internet2.middleware.openid.message.MessageExtension;

/**
 * Response to an Attribute Exchange store request.
 */
public interface StoreResponse extends MessageExtension {

    /**
     * Attribute Exchange mode representing a successful response to a store request.
     */
    public static final String MODE_SUCCESS = "store_response_success";

    /**
     * Attribute Exchange mode representing a successful response to a store request.
     */
    public static final String MODE_FAILURE = "store_response_failure";

    /**
     * A human-readable message indicating why the store request failed.
     * 
     * @return the error message
     */
    public String getError();

}