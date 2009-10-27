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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.Configuration;
import edu.internet2.middleware.openid.common.NamespaceMap;
import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.extensions.MessageExtensionMarshaller;
import edu.internet2.middleware.openid.extensions.pape.PAPE.Parameter;
import edu.internet2.middleware.openid.message.io.MarshallingException;
import edu.internet2.middleware.openid.util.StringUtils;

/**
 * Marshaller for {@link PAPEMessage} message extensions.
 */
public class PAPEMessageMarshaller implements MessageExtensionMarshaller<PAPEMessage> {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(PAPEMessageMarshaller.class);

    /** {@inheritDoc} */
    public ParameterMap marshall(PAPEMessage message) throws MarshallingException {
        ParameterMap parameters = new ParameterMap();

        if (message instanceof PAPERequest) {
            marshall((PAPERequest) message, parameters);
        } else if (message instanceof PAPEResponse) {
            marshall((PAPEResponse) message, parameters);
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
    public void marshall(PAPERequest request, ParameterMap parameters) throws MarshallingException {
        log.debug("marshalling pape request");

        Integer maxAge = request.getMaxAuthenticationAge();
        if (maxAge != null) {
            parameters.put(Parameter.max_auth_age.QNAME, maxAge.toString());
        }

        if (!request.getAuthenticationPolicies().isEmpty()) {
            String policies = StringUtils.join(request.getAuthenticationPolicies(), " ");
            parameters.put(Parameter.preferred_auth_policies.QNAME, policies);
        }

        if (!request.getAssuranceLevelTypes().isEmpty()) {
            NamespaceMap types = new NamespaceMap();
            types.setAliasPrefix(PAPE.ASSURANCE_ALIAS_PREFIX);

            List<String> typeAliases = new ArrayList<String>();

            for (String typeURI : request.getAssuranceLevelTypes()) {
                String alias = types.add(typeURI);
                typeAliases.add(alias);

                QName nsQName = new QName(PAPE.PAPE_10_NS, Parameter.auth_level.toString() + ".ns." + alias);
                parameters.put(nsQName, typeURI);
            }

            parameters.put(Parameter.preferred_auth_level_types.QNAME, StringUtils.join(typeAliases, " "));
        }
    }

    /**
     * Marshall the response.
     * 
     * @param response response to marshall
     * @param parameters the parameter map to marshall into
     * @throws MarshallingException thrown if an error occurs marshalling the message extension into the Parameter Map
     */
    public void marshall(PAPEResponse response, ParameterMap parameters) throws MarshallingException {
        log.debug("marshalling pape response");

        Date authTime = response.getAuthenticationTime();
        if (authTime != null) {
            parameters.put(Parameter.auth_time.QNAME, Configuration.getInternetDateFormat().format(authTime));
        }

        if (response.getAuthenticationPolicies().isEmpty()) {
            parameters.put(Parameter.auth_policies.QNAME, PAPE.PAPE_AUTH_POLICY_NONE);
        } else {
            String authPolicies = StringUtils.join(response.getAuthenticationPolicies(), " ");
            parameters.put(Parameter.auth_policies.QNAME, authPolicies);
        }

        if (!response.getAssuranceLevels().isEmpty()) {
            NamespaceMap types = new NamespaceMap();
            types.setAliasPrefix(PAPE.ASSURANCE_ALIAS_PREFIX);

            for (String typeURI : response.getAssuranceLevels().keySet()) {
                String alias = types.add(typeURI);

                QName nsQName = new QName(PAPE.PAPE_10_NS, Parameter.auth_level.toString() + ".ns." + alias);
                parameters.put(nsQName, typeURI);

                QName levelQName = new QName(PAPE.PAPE_10_NS, Parameter.auth_level.toString() + "." + alias);
                parameters.put(levelQName, response.getAssuranceLevels().get(typeURI));
            }
        }
    }

}