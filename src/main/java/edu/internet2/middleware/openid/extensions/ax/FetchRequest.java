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

import java.net.URL;
import java.util.List;
import java.util.Map;

import edu.internet2.middleware.openid.extensions.MessageExtension;

/**
 * Attribute Exchange request to receive attributes for a user.
 */
public interface FetchRequest extends MessageExtension {

    /**
     * Attribute Exchange mode for a fetch request.
     */
    public static final String MODE = "fetch_request";

    /**
     * Types for Attributes which are required by the relying party to offer certain functionality.
     * 
     * @return the required attributes
     */
    public List<String> getRequiredAttributes();

    /**
     * Types of optional attributes.
     * 
     * @return the optional attributes
     */
    public List<String> getOptionalAttributes();

    /**
     * Get the number of requested values for each attributes.
     * 
     * @return map of attribute counts
     */
    public Map<String, Integer> getAttributeCount();

    /**
     * URL to which the OpenID Provider may re-post the fetch response message at some later time.
     * 
     * @return the update URL
     */
    public URL getUpdateURL();

}