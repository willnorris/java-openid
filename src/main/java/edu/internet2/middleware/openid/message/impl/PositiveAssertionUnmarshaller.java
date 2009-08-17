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

import java.util.Arrays;
import java.util.Map;

import edu.internet2.middleware.openid.message.PositiveAssertion;
import edu.internet2.middleware.openid.message.Unmarshaller;
import edu.internet2.middleware.openid.message.Message.Parameter;

/**
 * PositiveAssertionUnmarshaller.
 */
public class PositiveAssertionUnmarshaller implements Unmarshaller<PositiveAssertion> {

    /** {@inheritDoc} */
    public PositiveAssertion unmarshall(Map<String, String> parameters) {
        PositiveAssertionImpl response = new PositiveAssertionImpl();

        response.setAssociationHandle(parameters.get(Parameter.assoc_handle.toString()));
        response.setClaimedId(parameters.get(Parameter.claimed_id.toString()));
        response.setEndpoint(parameters.get(Parameter.op_endpoint.toString()));
        response.setIdentity(parameters.get(Parameter.identity.toString()));
        response.setInvalidateHandle(parameters.get(Parameter.invalidate_handle.toString()));
        response.setResponseNonce(parameters.get(Parameter.response_nonce.toString()));
        response.setReturnTo(parameters.get(Parameter.return_to.toString()));
        response.setSignature(parameters.get(Parameter.sig.toString()));
        response.getSignedFields().addAll(Arrays.asList(Parameter.signed.toString().split(",")));

        return response;
    }

}