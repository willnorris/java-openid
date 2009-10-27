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

package edu.internet2.middleware.openid.extensions.sreg;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.extensions.MessageExtensionMarshaller;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistration.Field;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistration.Parameter;
import edu.internet2.middleware.openid.message.io.MarshallingException;
import edu.internet2.middleware.openid.util.DatatypeHelper;

/**
 * Marshaller for {@link SimpleRegistrationMessage} message extensions.
 */
public class SimpleRegistrationMessageMarshaller implements MessageExtensionMarshaller<SimpleRegistrationMessage> {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(SimpleRegistrationMessageMarshaller.class);

    /** {@inheritDoc} */
    public ParameterMap marshall(SimpleRegistrationMessage message) throws MarshallingException {
        ParameterMap parameters = new ParameterMap();

        if (message instanceof SimpleRegistrationRequest) {
            marshall((SimpleRegistrationRequest) message, parameters);
        } else if (message instanceof SimpleRegistrationResponse) {
            marshall((SimpleRegistrationResponse) message, parameters);
        }

        return parameters;
    }

    /**
     * Marshall the request.
     * 
     * @param request request to marshall
     * @param parameters the parameter map to marshall into
     * @throws MarshallingException thrown if an error occurs marshalling the message extension into the Parameter Map
     */
    protected void marshall(SimpleRegistrationRequest request, ParameterMap parameters) throws MarshallingException {
        log.debug("marshalling simple registration request");

        // policy URL
        URL policyURL = request.getPolicyURL();
        if (policyURL != null) {
            parameters.put(Parameter.policy_url.QNAME, policyURL.toString());
        }

        // required parameters
        String requiredFields = DatatypeHelper.listToStringValue(request.getRequiredFields(), ",");
        if (requiredFields != null) {
            parameters.put(Parameter.required.QNAME, requiredFields);
        }

        // optional parameters
        String optionalFields = DatatypeHelper.listToStringValue(request.getOptionalFields(), ",");
        if (optionalFields != null) {
            parameters.put(Parameter.optional.QNAME, optionalFields);
        }
    }

    /**
     * Marshall the response.
     * 
     * @param response response to marshall
     * @param parameters the parameter map to marshall into
     * @throws MarshallingException thrown if an error occurs marshalling the message extension into the Parameter Map
     */
    protected void marshall(SimpleRegistrationResponse response, ParameterMap parameters) throws MarshallingException {
        log.debug("marshalling simple registration response");

        for (Field field : response.getFields().keySet()) {
            log.debug("sreg field: {} = {}", field.QNAME, response.getFields().get(field));
            parameters.put(field.QNAME, response.getFields().get(field));
        }
    }

}