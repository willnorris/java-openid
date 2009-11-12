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

package edu.internet2.middleware.openid.extensions.ax.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.internet2.middleware.openid.extensions.ax.FetchRequest;

/**
 * Implementation of {@link FetchRequest} attribute exchange messages.
 */
public class FetchRequestImpl extends BaseAttributeExchangeMessage implements FetchRequest {

    /**
     * Optional attributes.
     */
    private List<String> optionalAttributes;

    /**
     * Required attributes.
     */
    private List<String> requiredAttributes;

    /**
     * Map of requested number of values for each attribute.
     */
    private Map<String, Integer> attributeCount;

    /**
     * Update URL.
     */
    private String updateURL;

    /**
     * Constructor.
     */
    public FetchRequestImpl() {
        optionalAttributes = new ArrayList<String>();
        requiredAttributes = new ArrayList<String>();
        attributeCount = new HashMap<String, Integer>();
    }

    /** {@inheritDoc} */
    public String getMode() {
        return FetchRequest.MODE;
    }

    /** {@inheritDoc} */
    public List<String> getOptionalAttributes() {
        return optionalAttributes;
    }

    /** {@inheritDoc} */
    public List<String> getRequiredAttributes() {
        return requiredAttributes;
    }

    /** {@inheritDoc} */
    public Map<String, Integer> getAttributeCount() {
        return attributeCount;
    }

    /** {@inheritDoc} */
    public String getUpdateURL() {
        return updateURL;
    }

    /** {@inheritDoc} */
    public void setUpdateURL(String newUpdateURL) {
        updateURL = newUpdateURL;
    }

}