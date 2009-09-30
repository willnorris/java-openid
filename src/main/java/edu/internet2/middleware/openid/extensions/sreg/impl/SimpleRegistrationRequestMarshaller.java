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

import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.extensions.MessageExtensionMarshaller;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistrationRequest;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistration.Parameter;
import edu.internet2.middleware.openid.util.StringUtils;

/**
 * Marshaller for a simple registration request.
 */
public class SimpleRegistrationRequestMarshaller implements MessageExtensionMarshaller<SimpleRegistrationRequest> {

    /** {@inheritDoc} */
    public ParameterMap marshall(SimpleRegistrationRequest request) {
        ParameterMap parameters = new ParameterMap();

        // policy URL
        URL policyURL = request.getPolicyURL();
        if (policyURL != null) {
            parameters.put(Parameter.policy_url.QNAME, policyURL.toString());
        }

        // required parameters
        String requiredFields = StringUtils.join(request.getRequiredFields(), ",");
        if (requiredFields != null) {
            parameters.put(Parameter.required.QNAME, requiredFields);
        }

        // optional parameters
        String optionalFields = StringUtils.join(request.getOptionalFields(), ",");
        if (optionalFields != null) {
            parameters.put(Parameter.optional.QNAME, optionalFields);
        }

        return parameters;
    }

}