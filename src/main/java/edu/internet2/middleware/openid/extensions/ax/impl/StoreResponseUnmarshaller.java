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

import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchangeUnmarshaller;
import edu.internet2.middleware.openid.extensions.ax.StoreResponse;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchange.Parameter;

/**
 * StoreResponseUnmarshaller.
 */
public class StoreResponseUnmarshaller implements AttributeExchangeUnmarshaller<StoreResponse> {

    /** {@inheritDoc} */
    public void unmarshall(StoreResponse response, ParameterMap parameters) {
        String mode = parameters.get(Parameter.mode.QNAME);
        if (StoreResponse.MODE_SUCCESS.equals(mode)) {
            response.setSuccess(true);
        } else if (StoreResponse.MODE_FAILURE.equals(mode)) {
            response.setSuccess(false);
            response.setError(parameters.get(Parameter.error.QNAME));
        }
    }

}