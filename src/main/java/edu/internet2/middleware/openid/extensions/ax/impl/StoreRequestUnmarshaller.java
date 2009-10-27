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
import edu.internet2.middleware.openid.extensions.ax.AttributeExchangeUnmarshaller;
import edu.internet2.middleware.openid.extensions.ax.StoreRequest;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchange.Parameter;

/**
 * StoreRequestUnmarshaller.
 */
public class StoreRequestUnmarshaller implements AttributeExchangeUnmarshaller<StoreRequest> {

    /** {@inheritDoc} */
    public void unmarshall(StoreRequest request, ParameterMap parameters) {

        // get all the attribute types
        NamespaceMap types = new NamespaceMap();
        for (QName qname : parameters.keySet()) {
            String[] parts = qname.getLocalPart().split("\\.", 2);
            if (Parameter.type.toString().equals(parts[0])) {
                types.add(parameters.get(qname), parts[1]);
            }
        }

        // get the attribute values and add to message
        for (String typeURI : types.getURIs()) {
            String alias = types.getAlias(typeURI);
            List<String> values = new ArrayList<String>();
            int count = 1;

            QName countQName = new QName(AttributeExchange.AX_10_NS, Parameter.count.toString() + "." + alias);
            String countString = parameters.get(countQName);
            if (countString != null) {
                count = Integer.parseInt(countString);
            }

            if (count == 1) {
                QName valueQName = new QName(AttributeExchange.AX_10_NS, Parameter.value.toString() + "." + alias);
                values.add(parameters.get(valueQName));
            } else {
                for (int i = 0; i < count; i++) {
                    QName valueQName = new QName(AttributeExchange.AX_10_NS, Parameter.value.toString() + "." + alias
                            + "." + (i + 1));
                    values.add(parameters.get(valueQName));
                }
            }

            request.getAttributes().put(typeURI, values);
        }

    }

}