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

package edu.internet2.middleware.openid.extensions.sreg.impl;

import java.net.URL;
import java.util.EnumSet;

import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistration;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistrationRequest;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistration.Field;

/**
 * Implementation of {@link SimpleRegistrationRequest}.
 */
public class SimpleRegistrationRequestImpl implements SimpleRegistrationRequest {

    /**
     * Optional Fields.
     */
    private EnumSet<Field> optionalFields;

    /**
     * Required Fields.
     */
    private EnumSet<Field> requiredFields;

    /**
     * Policy URL.
     */
    private URL policyURL;

    /**
     * Default constructor.
     */
    public SimpleRegistrationRequestImpl() {
        optionalFields = EnumSet.noneOf(Field.class);
        requiredFields = EnumSet.noneOf(Field.class);
    }

    /** {@inheritDoc} */
    public String getNamespace() {
        // TODO handle SREG_10_NS
        return SimpleRegistration.SREG_11_NS;
    }

    /** {@inheritDoc} */
    public EnumSet<Field> getOptionalFields() {
        return optionalFields;
    }

    /** {@inheritDoc} */
    public EnumSet<Field> getRequiredFields() {
        return requiredFields;
    }

    /** {@inheritDoc} */
    public URL getPolicyURL() {
        return policyURL;
    }

    /** {@inheritDoc} */
    public void setPolicyURL(URL newPolicyURL) {
        policyURL = newPolicyURL;
    }

}