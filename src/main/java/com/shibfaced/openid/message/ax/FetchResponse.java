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

package com.shibfaced.openid.message.ax;

import java.net.URL;
import java.util.List;
import java.util.Map;

import com.shibfaced.openid.message.MessageExtension;

/**
 * Response to an Attribute Exchange fetch request.
 */
public interface FetchResponse extends MessageExtension {

    /**
     * Message mode for attribute exchange fetch responses.
     */
    public static final String MODE = "fetch_response";

    /**
     * AX attributes.
     * 
     * @return the attributes
     */
    public Map<String, List<String>> getAttributes();

    /**
     * The update URL from the AX fetch request, if the OpenID Provider intends to support attribute update.
     * 
     * @return the update URL
     */
    public URL getUpdateURL();

}