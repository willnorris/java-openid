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
import java.util.List;

import edu.internet2.middleware.openid.extensions.pape.ProviderAuthenticationPolicy;
import edu.internet2.middleware.openid.extensions.pape.PolicyRequest;

/**
 * Implementation of {@list PAPERequest} message.
 */
public class PolicyRequestImpl implements PolicyRequest {

    /** Assurance level type URIs. */
    private List<String> assuranceLevelTypes;

    /** Authentication Policy URIs. */
    private List<String> authenticationPolicies;

    /** Maximum authentication age. */
    private Integer maxAuthenticationAge;

    /** Constructor. */
    public PolicyRequestImpl() {
        assuranceLevelTypes = new ArrayList<String>();
        authenticationPolicies = new ArrayList<String>();
    }

    /** {@inheritDoc} */
    public String getNamespace() {
        return ProviderAuthenticationPolicy.PAPE_10_NS;
    }

    /** {@inheritDoc} */
    public List<String> getAssuranceLevelTypes() {
        return assuranceLevelTypes;
    }

    /** {@inheritDoc} */
    public List<String> getAuthenticationPolicies() {
        return authenticationPolicies;
    }

    /** {@inheritDoc} */
    public Integer getMaxAuthenticationAge() {
        return maxAuthenticationAge;
    }

    /** {@inheritDoc} */
    public void setMaxAuthenticationAge(Integer maxAge) {
        maxAuthenticationAge = maxAge;
    }

}