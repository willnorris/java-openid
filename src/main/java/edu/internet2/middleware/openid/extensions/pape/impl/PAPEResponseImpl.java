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

package edu.internet2.middleware.openid.extensions.pape.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.internet2.middleware.openid.extensions.pape.PAPE;
import edu.internet2.middleware.openid.extensions.pape.PAPEResponse;

/**
 * Implementation of {@link PAPEResponse} messages.
 */
public class PAPEResponseImpl implements PAPEResponse {

    /** Assurance levels. */
    private Map<String, String> assuranceLevels;

    /** Authentication policies. */
    private List<String> authenticationPolicies;

    /** Authentication time. */
    private Date authenticationTime;

    /** Constructor. */
    public PAPEResponseImpl() {
        assuranceLevels = new LinkedHashMap<String, String>();
        authenticationPolicies = new ArrayList<String>();
    }

    /** {@inheritDoc} */
    public String getNamespace() {
        return PAPE.PAPE_10_NS;
    }

    /** {@inheritDoc} */
    public Map<String, String> getAssuranceLevels() {
        return assuranceLevels;
    }

    /** {@inheritDoc} */
    public List<String> getAuthenticationPolicies() {
        return authenticationPolicies;
    }

    /** {@inheritDoc} */
    public Date getAuthenticationTime() {
        return authenticationTime;
    }

    /** {@inheritDoc} */
    public void setAuthenticationTime(Date authTime) {
        authenticationTime = authTime;
    }

}