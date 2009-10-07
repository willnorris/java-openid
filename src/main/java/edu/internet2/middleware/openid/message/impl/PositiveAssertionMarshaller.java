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

import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.common.OpenIDConstants.Parameter;
import edu.internet2.middleware.openid.message.PositiveAssertion;
import edu.internet2.middleware.openid.message.encoding.EncodingUtils;

/**
 * Marshaller for {@link PositiveAssertion} messages.
 */
public class PositiveAssertionMarshaller extends AbstractMessageMarshaller<PositiveAssertion> {

    /** {@inheritDoc} */
    public void marshallParameters(PositiveAssertion response, ParameterMap parameters) {
        if (response.getAssociationHandle() != null) {
            parameters.put(Parameter.assoc_handle.QNAME, response.getAssociationHandle());
        }
        if (response.getClaimedId() != null) {
            parameters.put(Parameter.claimed_id.QNAME, response.getClaimedId());
        }
        if (response.getEndpoint() != null) {
            parameters.put(Parameter.op_endpoint.QNAME, response.getEndpoint());
        }
        if (response.getIdentity() != null) {
            parameters.put(Parameter.identity.QNAME, response.getIdentity());
        }
        if (response.getInvalidateHandle() != null) {
            parameters.put(Parameter.invalidate_handle.QNAME, response.getInvalidateHandle());
        }
        if (response.getResponseNonce() != null) {
            parameters.put(Parameter.response_nonce.QNAME, response.getResponseNonce());
        }
        if (response.getReturnTo() != null) {
            parameters.put(Parameter.return_to.QNAME, response.getReturnTo().toString());
        }
        if (response.getSignature() != null) {
            parameters.put(Parameter.sig.QNAME, response.getSignature());
        }

        // marshall extensions before signed fields
        marshallExtensions(response, parameters);

        if (!response.getSignedFields().isEmpty()) {
            String signedFields = EncodingUtils.encodeFieldList(response.getSignedFields(), parameters.getNamespaces());
            parameters.put(Parameter.signed.QNAME, signedFields);
        }
    }

}