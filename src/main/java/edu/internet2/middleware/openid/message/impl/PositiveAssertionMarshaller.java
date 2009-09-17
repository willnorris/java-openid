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

import edu.internet2.middleware.openid.message.ParameterMap;
import edu.internet2.middleware.openid.message.PositiveAssertion;
import edu.internet2.middleware.openid.message.Message.Parameter;
import edu.internet2.middleware.openid.util.StringUtils;

/**
 * Marshaller for {@link PositiveAssertion} messages.
 */
public class PositiveAssertionMarshaller extends AbstractMessageMarshaller<PositiveAssertion> {

    /** {@inheritDoc} */
    public void marshallParameters(PositiveAssertion response, ParameterMap parameters) {
        parameters.put(Parameter.assoc_handle, response.getAssociationHandle());
        parameters.put(Parameter.claimed_id, response.getClaimedId());
        parameters.put(Parameter.op_endpoint, response.getEndpoint());
        parameters.put(Parameter.identity, response.getIdentity());
        parameters.put(Parameter.invalidate_handle, response.getInvalidateHandle());
        parameters.put(Parameter.response_nonce, response.getResponseNonce());
        parameters.put(Parameter.return_to, response.getReturnTo().toString());
        parameters.put(Parameter.sig, response.getSignature());
        parameters.put(Parameter.signed, StringUtils.join(response.getSignedFields(), ","));
    }

}