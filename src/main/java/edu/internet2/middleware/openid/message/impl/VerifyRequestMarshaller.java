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

import edu.internet2.middleware.openid.common.OpenIDConstants.Parameter;
import edu.internet2.middleware.openid.message.ParameterMap;
import edu.internet2.middleware.openid.message.VerifyRequest;
import edu.internet2.middleware.openid.message.encoding.EncodingUtils;

/**
 * Marshaller for {@link VerifyRequest} messages.
 */
public class VerifyRequestMarshaller extends AbstractMessageMarshaller<VerifyRequest> {

    /** {@inheritDoc} */
    public void marshallParameters(VerifyRequest request, ParameterMap parameters) {
        parameters.put(Parameter.assoc_handle.QNAME, request.getAssociationHandle());
        parameters.put(Parameter.claimed_id.QNAME, request.getClaimedId());
        parameters.put(Parameter.op_endpoint.QNAME, request.getEndpoint());
        parameters.put(Parameter.identity.QNAME, request.getIdentity());
        parameters.put(Parameter.invalidate_handle.QNAME, request.getInvalidateHandle());
        parameters.put(Parameter.response_nonce.QNAME, request.getResponseNonce());
        parameters.put(Parameter.return_to.QNAME, request.getReturnTo().toString());
        parameters.put(Parameter.sig.QNAME, request.getSignature());

        if (!request.getSignedFields().isEmpty()) {
            String signedFields = EncodingUtils.encodeSignedFields(request.getSignedFields(), parameters
                    .getNamespaces());
            parameters.put(Parameter.signed.QNAME, signedFields);
        }

    }

}