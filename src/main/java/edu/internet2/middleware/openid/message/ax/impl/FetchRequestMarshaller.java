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

package edu.internet2.middleware.openid.message.ax.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.internet2.middleware.openid.message.ax.AttributeExchange;
import edu.internet2.middleware.openid.message.ax.FetchRequest;
import edu.internet2.middleware.openid.message.ax.AttributeExchange.Parameter;
import edu.internet2.middleware.openid.util.StringUtils;

/**
 * FetchRequestMarshaller.
 */
public class FetchRequestMarshaller {

    /** {@inheritDoc} */
    public Map<String, String> marshall(FetchRequest request) {
        Map<String, String> parameters = new HashMap<String, String>();
        int aliasCount = 0;
        Integer valueCount;
        String aliasName;

        // update URL
        URL policyURL = request.getUpdateURL();
        if (policyURL != null) {
            parameters.put(Parameter.update_url.toString(), policyURL.toString());
        }

        // required attributes
        List<String> requiredAliases = new ArrayList<String>();
        for (String type : request.getRequiredAttributes()) {
            aliasName = AttributeExchange.ALIAS_PREFIX + (++aliasCount);
            parameters.put(Parameter.type.toString() + "." + aliasName, type);
            requiredAliases.add(aliasName);

            valueCount = request.getAttributeCount().get(type);

            if (valueCount != null && valueCount > 1) {
                parameters.put(Parameter.count.toString() + "." + aliasName, valueCount + "");
            }
        }

        // optional attributes
        List<String> optionalAliases = new ArrayList<String>();
        for (String type : request.getOptionalAttributes()) {
            aliasName = AttributeExchange.ALIAS_PREFIX + (++aliasCount);
            parameters.put(Parameter.type.toString() + "." + aliasName, type);
            optionalAliases.add(aliasName);

            valueCount = request.getAttributeCount().get(type);

            if (valueCount != null && valueCount > 1) {
                parameters.put(Parameter.count.toString() + "." + aliasName, valueCount + "");
            }
        }

        parameters.put(Parameter.required.toString(), StringUtils.join(requiredAliases, ","));
        parameters.put(Parameter.if_available.toString(), StringUtils.join(optionalAliases, ","));

        return parameters;
    }

}