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

package edu.internet2.middleware.openid.message.impl;

import java.util.HashMap;
import java.util.Map;

import edu.internet2.middleware.openid.message.Marshaller;
import edu.internet2.middleware.openid.message.NegativeAssertion;
import edu.internet2.middleware.openid.message.Message.Parameter;

/**
 * NegativeAssertionMarshaller.
 */
public class NegativeAssertionMarshaller implements Marshaller<NegativeAssertion> {

    /** {@inheritDoc} */
    public Map<String, String> marshall(NegativeAssertion response) {
        Map<String, String> parameters = new HashMap<String, String>();

        parameters.put(Parameter.mode.toString(), response.getMode());

        return parameters;
    }

}