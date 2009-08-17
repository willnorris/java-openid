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

package com.shibfaced.openid.message.sreg.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.shibfaced.openid.message.Unmarshaller;
import com.shibfaced.openid.message.sreg.SimpleRegistrationRequest;
import com.shibfaced.openid.message.sreg.SimpleRegistration.Field;
import com.shibfaced.openid.message.sreg.SimpleRegistration.Parameter;
import com.shibfaced.openid.util.DatatypeHelper;

/**
 * Unmarshaller for a simple registration request.
 */
public class SimpleRegistrationRequestUnmarshaller implements Unmarshaller<SimpleRegistrationRequest> {

    /** {@inheritDoc} */
    public SimpleRegistrationRequest unmarshall(Map<String, String> parameters) {
        SimpleRegistrationRequestImpl request = new SimpleRegistrationRequestImpl();

        // policy URL
        try {
            URL policyURL = new URL(parameters.get(Parameter.policy_url.toString()));
            request.setPolicyURL(policyURL);
        } catch (MalformedURLException e) {
            // do nothing
        }

        // required fields
        String requiredFields = parameters.get(Parameter.required.toString());
        if (!DatatypeHelper.isEmpty(requiredFields)) {
            for (String fieldName : requiredFields.split(",")) {
                try {
                    request.getRequiredFields().add(Field.valueOf(fieldName));
                } catch (IllegalArgumentException e) {
                    // do nothing
                }
            }
        }

        // optional fields
        String optionalFields = parameters.get(Parameter.optional.toString());
        if (!DatatypeHelper.isEmpty(optionalFields)) {
            for (String fieldName : optionalFields.split(",")) {
                try {
                    request.getOptionalFields().add(Field.valueOf(fieldName));
                } catch (IllegalArgumentException e) {
                    // do nothing
                }
            }
        }

        return request;
    }
}