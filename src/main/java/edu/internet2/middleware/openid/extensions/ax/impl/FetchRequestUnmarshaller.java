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

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchange;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchangeUnmarshaller;
import edu.internet2.middleware.openid.extensions.ax.FetchRequest;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchange.Parameter;
import edu.internet2.middleware.openid.util.DatatypeHelper;

/**
 * FetchRequestUnmarshaller.
 */
public class FetchRequestUnmarshaller implements AttributeExchangeUnmarshaller<FetchRequest> {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(FetchRequestUnmarshaller.class);

    /** {@inheritDoc} */
    public void unmarshall(FetchRequest request, ParameterMap parameters) {

        String required = parameters.get(Parameter.required.QNAME);
        if (!DatatypeHelper.isEmpty(required)) {
            String[] requiredAliases = required.split(",");
            for (String alias : requiredAliases) {
                String typeURI = unmarshallAttribute(request, parameters, alias);
                if (typeURI != null) {
                    request.getRequiredAttributes().add(typeURI);
                }
            }
        }

        String optional = parameters.get(Parameter.if_available.QNAME);
        if (!DatatypeHelper.isEmpty(optional)) {
            String[] optionalAliases = optional.split(",");
            for (String alias : optionalAliases) {
                String typeURI = unmarshallAttribute(request, parameters, alias);
                if (typeURI != null) {
                    request.getOptionalAttributes().add(typeURI);
                }
            }
        }

        String updateURL = parameters.get(Parameter.update_url.QNAME);
        if (!DatatypeHelper.isEmpty(updateURL)) {
            try {
                request.setUpdateURL(new URL(updateURL));
            } catch (MalformedURLException e) {
                log.warn("Attribute Exchange update_url malformed: '{}'", updateURL);
            }
        }
    }

    /**
     * Unmarshall the AX request attribute.
     * 
     * @param request AX fetch request
     * @param parameters parameter map
     * @param alias alias of the attribute to unmarshall
     * @return type URI for the attribute
     */
    protected String unmarshallAttribute(FetchRequest request, ParameterMap parameters, String alias) {
        QName typeQName = new QName(AttributeExchange.AX_10_NS, "type." + alias);

        if (!parameters.containsKey(typeQName)) {
            log.error("Attribute Exchange alias '{}' not assigned to a type URI", alias);
            return null;
        }

        String typeURI = parameters.get(typeQName);

        // add attribute count
        QName countQName = new QName(AttributeExchange.AX_10_NS, "count." + alias);
        if (parameters.containsKey(countQName)) {
            request.getAttributeCount().put(typeURI, Integer.parseInt(parameters.get(countQName)));
        }

        return typeURI;
    }

}