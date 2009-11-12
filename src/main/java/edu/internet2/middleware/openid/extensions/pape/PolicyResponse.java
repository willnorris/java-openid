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

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * PAPE Response message.
 */
public interface PolicyResponse extends PolicyMessage {

    /**
     * The date and time of when the user was most recently authenticated in accordance with the asserted authentication
     * policies.
     * 
     * @return date and time of most recent authentication
     */
    public Date getAuthenticationTime();

    /**
     * Set the authentication time.
     * 
     * @param authTime the authentication time
     */
    public void setAuthenticationTime(Date authTime);

    /**
     * List of URIs representing authentication policies that were satisfied when authenticating the user.
     * 
     * @return list of authentication policy URIs
     */
    public List<String> getAuthenticationPolicies();

    /**
     * Map of custom assurance levels that were satisfied when authenticating the user. Map keys are URIs representing
     * the custom authentication level, and values are the assurance level value.
     * 
     * @return map of satisfied assurance levels
     */
    public Map<String, String> getAssuranceLevels();

}