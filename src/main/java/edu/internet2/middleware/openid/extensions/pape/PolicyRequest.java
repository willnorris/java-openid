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

package edu.internet2.middleware.openid.extensions.pape;

import java.util.List;

/**
 * PAPE Request message.
 */
public interface PolicyRequest extends PolicyMessage {

    /**
     * Maximum amount of time allowed to pass before user must be re-authenticated in a matter fitting the requested
     * policies.
     * 
     * @return maximum authentication age, in seconds
     */
    public Integer getMaxAuthenticationAge();

    /**
     * Set the maximum authentication age.
     * 
     * @param maxAge the maximum authentication age.
     */
    public void setMaxAuthenticationAge(Integer maxAge);

    /**
     * List of URIs representing authentication policies that should be satisfied when authenticating the user.
     * 
     * @return list of authentication policy URIs
     */
    public List<String> getAuthenticationPolicies();

    /**
     * List of URIs representing custom assurance levels requested.
     * 
     * @return list of custom assurance level URIs
     */
    public List<String> getAssuranceLevelTypes();

}