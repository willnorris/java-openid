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

package com.shibfaced.openid.message.ax.impl;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opensaml.xml.util.DatatypeHelper;

import com.shibfaced.openid.message.Marshaller;
import com.shibfaced.openid.message.ax.AttributeExchange;
import com.shibfaced.openid.message.ax.FetchResponse;
import com.shibfaced.openid.message.ax.AttributeExchange.Parameter;

/**
 * FetchRequestMarshaller.
 */
public class FetchResponseMarshaller implements Marshaller<FetchResponse> {

    /** {@inheritDoc} */
    public Map<String, String> marshall(FetchResponse response) {
        Map<String, String> parameters = new HashMap<String, String>();
        int aliasCount = 0;
        int valueCount;
        String aliasName;

        // update URL
        URL policyURL = response.getUpdateURL();
        if (policyURL != null && !DatatypeHelper.isEmpty(policyURL.toString())) {
            parameters.put(Parameter.update_url.toString(), policyURL.toString());
        }

        // attributes
        Map<String, List<String>> attributes = response.getAttributes();
        for (String name : attributes.keySet()) {
            List<String> values = attributes.get(name);
            if (values.size() <= 0) {
                continue;
            }

            aliasName = AttributeExchange.ALIAS_PREFIX + (++aliasCount);
            valueCount = 0;

            // add type parameter
            parameters.put(Parameter.type.toString() + "." + aliasName, name);

            if (values.size() == 1) {
                parameters.put(Parameter.value.toString() + "." + aliasName, values.get(0));
            } else {
                parameters.put(Parameter.count.toString() + "." + aliasName, values.size() + "");
                for (String value : values) {
                    parameters.put(Parameter.value.toString() + "." + aliasName + "." + (++valueCount), value);
                }
            }

        }

        return parameters;
    }

}