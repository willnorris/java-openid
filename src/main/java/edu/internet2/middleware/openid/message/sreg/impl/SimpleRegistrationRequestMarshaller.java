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

package edu.internet2.middleware.openid.message.sreg.impl;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.opensaml.xml.util.DatatypeHelper;

import edu.internet2.middleware.openid.message.Marshaller;
import edu.internet2.middleware.openid.message.sreg.SimpleRegistrationRequest;
import edu.internet2.middleware.openid.message.sreg.SimpleRegistration.Parameter;
import edu.internet2.middleware.openid.util.StringUtils;

/**
 * Marshaller for a simple registration request.
 */
public class SimpleRegistrationRequestMarshaller  {

    /** {@inheritDoc} */
    public Map<String, String> marshall(SimpleRegistrationRequest request) {
        Map<String, String> parameters = new HashMap<String, String>();
        String fields;

        // policy URL
        URL policyURL = request.getPolicyURL();
        if (policyURL != null && !DatatypeHelper.isEmpty(policyURL.toString())) {
            parameters.put(Parameter.policy_url.toString(), policyURL.toString());
        }

        // required parameters
        fields = StringUtils.join(request.getRequiredFields(), ",");
        if (!DatatypeHelper.isEmpty(fields)) {
            parameters.put(Parameter.required.toString(), fields);
        }

        // optional parameters
        fields = StringUtils.join(request.getOptionalFields(), ",");
        if (!DatatypeHelper.isEmpty(fields)) {
            parameters.put(Parameter.optional.toString(), fields);
        }

        return parameters;
    }

}