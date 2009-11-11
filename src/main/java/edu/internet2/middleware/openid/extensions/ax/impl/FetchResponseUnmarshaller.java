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
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.common.NamespaceMap;
import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchange;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchangeUnmarshaller;
import edu.internet2.middleware.openid.extensions.ax.FetchResponse;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchange.Parameter;
import edu.internet2.middleware.openid.util.DatatypeHelper;

/**
 * FetchRequestUnmarshaller.
 */
public class FetchResponseUnmarshaller implements AttributeExchangeUnmarshaller<FetchResponse> {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(FetchResponseUnmarshaller.class);

    /** {@inheritDoc} */
    public void unmarshall(FetchResponse response, ParameterMap parameters) {

        String updateURL = parameters.get(Parameter.update_url.QNAME);
        if (!DatatypeHelper.isEmpty(updateURL)) {
            try {
                response.setUpdateURL(new URL(updateURL));
            } catch (MalformedURLException e) {
                log.warn("Attribute Exchange update_url malformed: '{}'", updateURL);
            }
        }

        // get all the attribute types
        NamespaceMap types = new NamespaceMap();
        for (QName qname : parameters.keySet()) {
            String[] parts = qname.getLocalPart().split("\\.", 2);
            if (Parameter.type.toString().equals(parts[0])) {
                types.add(parameters.get(qname), parts[1]);
            }
        }

        // get the attribute values and add to response object
        for (String typeURI : types.getURIs()) {
            String alias = types.getAlias(typeURI);
            int count = 1;

            QName countQName = new QName(AttributeExchange.AX_10_NS, Parameter.count.toString() + "." + alias);
            String countString = parameters.get(countQName);
            if (countString != null) {
                count = Integer.parseInt(countString);
            }

            if (count == 0) {
                response.getAttributes().put(typeURI, Collections.EMPTY_LIST);
            } else {
                response.getAttributes().put(typeURI, new ArrayList<String>());

                if (count == 1) {
                    QName valueQName = new QName(AttributeExchange.AX_10_NS, Parameter.value.toString() + "." + alias);
                    response.getAttributes().get(typeURI).add(parameters.get(valueQName));
                } else {
                    for (int i = 0; i < count; i++) {
                        QName valueQName = new QName(AttributeExchange.AX_10_NS, Parameter.value.toString() + "."
                                + alias + "." + (i + 1));
                        response.getAttributes().get(typeURI).add(parameters.get(valueQName));
                    }
                }
            }
        }

    }
}