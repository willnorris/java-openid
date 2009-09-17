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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import edu.internet2.middleware.openid.common.OpenIDConstants.Parameter;
import edu.internet2.middleware.openid.message.ParameterMap;
import edu.internet2.middleware.openid.message.PositiveAssertion;

/**
 * Unmarshaller for {@link PositiveAssertion} messages.
 */
public class PositiveAssertionUnmarshaller extends AbstractMessageUnmarshaller<PositiveAssertion> {

    /** {@inheritDoc} */
    public void unmarshallParameters(PositiveAssertion response, ParameterMap parameters) {
        response.setAssociationHandle(parameters.get(Parameter.assoc_handle.QNAME));
        response.setClaimedId(parameters.get(Parameter.claimed_id.QNAME));
        response.setEndpoint(parameters.get(Parameter.op_endpoint.QNAME));
        response.setIdentity(parameters.get(Parameter.identity.QNAME));
        response.setInvalidateHandle(parameters.get(Parameter.invalidate_handle.QNAME));
        response.setResponseNonce(parameters.get(Parameter.response_nonce.QNAME));
        try {
            String returnTo = parameters.get(Parameter.return_to.QNAME);
            response.setReturnTo(new URL(returnTo));
        } catch (MalformedURLException e) {
            // TODO
        }
        response.setSignature(parameters.get(Parameter.sig.QNAME));
        response.getSignedFields().addAll(Arrays.asList(Parameter.signed.toString().split(",")));
    }

}