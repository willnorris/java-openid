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
import edu.internet2.middleware.openid.message.VerifyRequest;
import edu.internet2.middleware.openid.message.encoding.EncodingUtils;

/**
 * Marshaller for {@link VerifyRequest} messages.
 */
public class VerifyRequestMarshaller extends AbstractMessageMarshaller<VerifyRequest> {

    /** {@inheritDoc} */
    public void marshallParameters(VerifyRequest request, ParameterMap parameters) {
        if (request.getAssociationHandle() != null) {
            parameters.put(Parameter.assoc_handle.QNAME, request.getAssociationHandle());
        }
        if (request.getClaimedId() != null) {
            parameters.put(Parameter.claimed_id.QNAME, request.getClaimedId());
        }
        if (request.getEndpoint() != null) {
            parameters.put(Parameter.op_endpoint.QNAME, request.getEndpoint());
        }
        if (request.getIdentity() != null) {
            parameters.put(Parameter.identity.QNAME, request.getIdentity());
        }
        if (request.getInvalidateHandle() != null) {
            parameters.put(Parameter.invalidate_handle.QNAME, request.getInvalidateHandle());
        }
        if (request.getResponseNonce() != null) {
            parameters.put(Parameter.response_nonce.QNAME, request.getResponseNonce());
        }
        if (request.getReturnTo() != null) {
            parameters.put(Parameter.return_to.QNAME, request.getReturnTo());
        }
        if (request.getSignature() != null) {
            parameters.put(Parameter.sig.QNAME, request.getSignature());
        }

        // marshall extensions before signed fields
        marshallExtensions(request, parameters);

        if (!request.getSignedFields().isEmpty()) {
            String signedFields = EncodingUtils.encodeFieldList(request.getSignedFields(), parameters.getNamespaces());
            parameters.put(Parameter.signed.QNAME, signedFields);
        }
    }

}