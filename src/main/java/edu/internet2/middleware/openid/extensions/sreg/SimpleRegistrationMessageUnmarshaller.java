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

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.extensions.AbstractMessageExtensionUnmarshaller;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistration.Field;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistration.Parameter;
import edu.internet2.middleware.openid.message.io.UnmarshallingException;
import edu.internet2.middleware.openid.util.DatatypeHelper;

/**
 * Unmarshaller for {@link SimpleRegistrationMessage} message extensions.
 */
public class SimpleRegistrationMessageUnmarshaller extends
        AbstractMessageExtensionUnmarshaller<SimpleRegistrationMessage> {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(SimpleRegistrationMessageUnmarshaller.class);

    /** {@inheritDoc} */
    public SimpleRegistrationMessage unmarshall(ParameterMap parameters) throws UnmarshallingException {
        if (parameters.containsKey(Parameter.optional.QNAME) || parameters.containsKey(Parameter.required.QNAME)) {
            SimpleRegistrationRequest request = (SimpleRegistrationRequest) buildMessage(SimpleRegistrationRequest.class);
            unmarshall(request, parameters);
            return request;
        } else {
            SimpleRegistrationResponse response = (SimpleRegistrationResponse) buildMessage(SimpleRegistrationResponse.class);
            unmarshall(response, parameters);
            return response;
        }
    }

    /**
     * Unmarshall the request.
     * 
     * @param request object to unmarshall to
     * @param parameters parameter map to unmarshall from
     * @throws UnmarshallingException thrown if an error occurs unmarshalling the message extension from the map
     */
    public void unmarshall(SimpleRegistrationRequest request, ParameterMap parameters) throws UnmarshallingException {
        request.setPolicyURL(parameters.get(Parameter.policy_url.QNAME));

        String optional = parameters.get(Parameter.optional.QNAME);
        if (!DatatypeHelper.isEmpty(optional)) {
            for (String fieldName : optional.split(",")) {
                Field field = Field.valueOf(fieldName);
                if (field != null) {
                    request.getOptionalFields().add(field);
                }
            }
        }

        String required = parameters.get(Parameter.required.QNAME);
        if (!DatatypeHelper.isEmpty(required)) {
            for (String fieldName : required.split(",")) {
                Field field = Field.valueOf(fieldName);
                if (field != null) {
                    request.getRequiredFields().add(field);
                }
            }
        }
    }

    /**
     * Unmarshall the response.
     * 
     * @param response object to unmarshall to
     * @param parameters parameter map to unmarshall from
     * @throws UnmarshallingException thrown if an error occurs unmarshalling the message extension from the map
     */
    public void unmarshall(SimpleRegistrationResponse response, ParameterMap parameters) throws UnmarshallingException {
        for (QName qname : parameters.keySet()) {
            Field field = Field.valueOf(qname.getLocalPart());
            if (field != null) {
                response.getFields().put(field, parameters.get(qname));
            }
        }
    }

}