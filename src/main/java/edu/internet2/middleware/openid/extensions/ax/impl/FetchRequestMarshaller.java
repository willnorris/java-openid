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

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import edu.internet2.middleware.openid.common.NamespaceMap;
import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchange;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchangeMarshaller;
import edu.internet2.middleware.openid.extensions.ax.FetchRequest;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchange.Parameter;
import edu.internet2.middleware.openid.util.StringUtils;

/**
 * Marshaller of {@link FetchRequest} messages.
 */
public class FetchRequestMarshaller implements AttributeExchangeMarshaller<FetchRequest> {

    /** {@inheritDoc} */
    public void marshall(FetchRequest request, ParameterMap parameters) {

        NamespaceMap types = new NamespaceMap();
        types.setAliasPrefix(AttributeExchange.ALIAS_PREFIX);

        // update URL
        String policyURL = request.getUpdateURL();
        if (policyURL != null) {
            parameters.put(Parameter.update_url.QNAME, policyURL);
        }

        // required attributes
        List<String> requiredAliases = new ArrayList<String>();
        for (String type : request.getRequiredAttributes()) {
            String alias = marshallAttribute(request, parameters, types, type);
            requiredAliases.add(alias);
        }

        if (!requiredAliases.isEmpty()) {
            parameters.put(Parameter.required.QNAME, StringUtils.join(requiredAliases, ","));
        }

        // optional attributes
        List<String> optionalAliases = new ArrayList<String>();
        for (String type : request.getOptionalAttributes()) {
            String alias = marshallAttribute(request, parameters, types, type);
            optionalAliases.add(alias);
        }

        if (!optionalAliases.isEmpty()) {
            parameters.put(Parameter.if_available.QNAME, StringUtils.join(optionalAliases, ","));
        }
    }

    /**
     * Marshall the AX request attribute.
     * 
     * @param request AX fetch request
     * @param parameters parameter map
     * @param types map of AX attributes types and aliases
     * @param typeURI type URI of attribute to marshall
     * @return alias for the attribute
     */
    protected String marshallAttribute(FetchRequest request, ParameterMap parameters, NamespaceMap types, String typeURI) {
        String alias = types.add(typeURI);
        QName aliasQName = new QName(AttributeExchange.AX_10_NS, Parameter.type.toString() + "." + alias);
        parameters.put(aliasQName, typeURI);

        Integer valueCount = request.getAttributeCount().get(typeURI);

        if (valueCount != null) {
            QName countQName = new QName(AttributeExchange.AX_10_NS, Parameter.count.toString() + "." + alias);
            if (valueCount == -1) {
                parameters.put(countQName, AttributeExchange.COUNT_UNLIMITED);
            } else if (valueCount > 1) {
                parameters.put(countQName, valueCount.toString());
            }
        }

        return alias;
    }

}