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

package edu.internet2.middleware.openid.extensions.pape;

import java.text.ParseException;
import java.util.Arrays;

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.Configuration;
import edu.internet2.middleware.openid.common.NamespaceMap;
import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.extensions.AbstractMessageExtensionUnmarshaller;
import edu.internet2.middleware.openid.extensions.pape.PAPE.Parameter;
import edu.internet2.middleware.openid.message.io.UnmarshallingException;
import edu.internet2.middleware.openid.util.DatatypeHelper;

/**
 * Unmarshaller for {@link PAPEMessage} message extensions.
 */
public class PAPEMessageUnmarshaller extends AbstractMessageExtensionUnmarshaller<PAPEMessage> {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(PAPEMessageUnmarshaller.class);

    /** {@inheritDoc} */
    public PAPEMessage unmarshall(ParameterMap parameters) throws UnmarshallingException {
        // auth_policies is the only parameter guaranteed to be present in response messages
        if (parameters.containsKey(Parameter.auth_policies.QNAME)) {
            PAPEResponse response = (PAPEResponse) buildMessage(PAPEResponse.class);
            unmarshall(response, parameters);
            return response;
        } else {
            PAPERequest request = (PAPERequest) buildMessage(PAPERequest.class);
            unmarshall(request, parameters);
            return request;
        }
    }

    /**
     * Unmarshall the request.
     * 
     * @param request object to unmarshall to
     * @param parameters parameter map to unmarshall from
     * @throws UnmarshallingException thrown if an error occurs unmarshalling the message extension from the map
     */
    public void unmarshall(PAPERequest request, ParameterMap parameters) throws UnmarshallingException {
        log.debug("Unmarshalling OpenID PAPE request");

        String maxAuthAge = parameters.get(Parameter.max_auth_age.QNAME);
        if (!DatatypeHelper.isEmpty(maxAuthAge)) {
            request.setMaxAuthenticationAge(Integer.parseInt(maxAuthAge));
        }

        String authPolicies = parameters.get(Parameter.preferred_auth_policies.QNAME);
        if (!DatatypeHelper.isEmpty(authPolicies)) {
            String[] policies = authPolicies.trim().split(" ");
            request.getAuthenticationPolicies().addAll(Arrays.asList(policies));
        }

        String assuranceLevels = parameters.get(Parameter.preferred_auth_level_types.QNAME);
        if (!DatatypeHelper.isEmpty(assuranceLevels)) {
            String[] aliases = assuranceLevels.trim().split(" ");
            for (String alias : aliases) {
                QName nsQName = new QName(PAPE.PAPE_10_NS, Parameter.auth_level + ".ns." + alias);
                String typeURI = parameters.get(nsQName);

                if (typeURI == null) {
                    log.error("Unable to find PAPE auth_level namespace delaration for alias: {}", alias);
                    continue;
                }

                request.getAssuranceLevelTypes().add(typeURI);
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
    public void unmarshall(PAPEResponse response, ParameterMap parameters) throws UnmarshallingException {
        log.debug("Unmarshalling OpenID PAPE response");

        String authTime = parameters.get(Parameter.auth_time.QNAME);
        if (!DatatypeHelper.isEmpty(authTime)) {
            try {
                response.setAuthenticationTime(Configuration.getInternetDateFormat().parse(authTime));
            } catch (ParseException e) {
                log.error("PAPE auth_time has invalid format: {}", authTime);
            }
        }

        String authPolicies = parameters.get(Parameter.auth_policies.QNAME);
        if (!DatatypeHelper.isEmpty(authPolicies) && !authPolicies.equals(PAPE.PAPE_AUTH_POLICY_NONE)) {
            String[] policies = authPolicies.split(" ");
            response.getAuthenticationPolicies().addAll(Arrays.asList(policies));
        }

        for (QName qname : parameters.keySet()) {
            String[] parts = qname.getLocalPart().split("\\.", 3);
            if (parts.length == 3 && Parameter.auth_level.toString().equals(parts[0]) && "ns".equals(parts[1])) {
                String typeURI = parameters.get(qname);
                String alias = parts[2];

                QName levelQName = new QName(PAPE.PAPE_10_NS, Parameter.auth_level.toString() + "." + alias);
                String level = parameters.get(levelQName);

                if (level != null) {
                    response.getAssuranceLevels().put(typeURI, level);
                }
            }
        }

    }

}