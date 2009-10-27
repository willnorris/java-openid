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

import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import edu.internet2.middleware.openid.common.NamespaceMap;
import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchange;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchangeMarshaller;
import edu.internet2.middleware.openid.extensions.ax.FetchResponse;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchange.Parameter;

/**
 * FetchRequestMarshaller.
 */
public class FetchResponseMarshaller implements AttributeExchangeMarshaller<FetchResponse> {

    /** {@inheritDoc} */
    public void marshall(FetchResponse response, ParameterMap parameters) {

        NamespaceMap types = new NamespaceMap();
        types.setAliasPrefix(AttributeExchange.ALIAS_PREFIX);

        // update URL
        URL policyURL = response.getUpdateURL();
        if (policyURL != null) {
            parameters.put(Parameter.update_url.QNAME, policyURL.toString());
        }

        // attributes
        Map<String, List<String>> attributes = response.getAttributes();
        for (String typeURI : attributes.keySet()) {
            List<String> values = attributes.get(typeURI);
            String alias = types.add(typeURI);

            QName typeQName = new QName(AttributeExchange.AX_10_NS, Parameter.type.toString() + "." + alias);
            parameters.put(typeQName, typeURI);

            if (values.size() != 1) {
                QName countQName = new QName(AttributeExchange.AX_10_NS, Parameter.count.toString() + "." + alias);
                parameters.put(countQName, Integer.toString(values.size()));
            }

            if (values.isEmpty()) {
                continue;
            } else if (values.size() == 1) {
                QName valueQName = new QName(AttributeExchange.AX_10_NS, Parameter.value.toString() + "." + alias);
                parameters.put(valueQName, values.get(0));
            } else {
                for (int i = 0; i < values.size(); i++) {
                    QName valueQName = new QName(AttributeExchange.AX_10_NS, Parameter.value.toString() + "." + alias
                            + "." + (i + 1));
                    parameters.put(valueQName, values.get(i));
                }
            }
        }

    }

}