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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistrationRequest;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistration.Field;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistration.Parameter;

/**
 * Unmarshaller for a simple registration request.
 */
public class SimpleRegistrationRequestUnmarshaller {

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
        if (requiredFields != null) {
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
        if (optionalFields != null) {
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