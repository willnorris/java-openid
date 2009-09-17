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
import edu.internet2.middleware.openid.message.VerifyRequest;
import edu.internet2.middleware.openid.message.Message.Parameter;
import edu.internet2.middleware.openid.util.StringUtils;

/**
 * Marshaller for {@link VerifyRequest} messages.
 */
public class VerifyRequestMarshaller extends AbstractMessageMarshaller<VerifyRequest> {

    /** {@inheritDoc} */
    public void marshallParameters(VerifyRequest request, ParameterMap parameters) {
        parameters.put(Parameter.assoc_handle, request.getAssociationHandle());
        parameters.put(Parameter.claimed_id, request.getClaimedId());
        parameters.put(Parameter.op_endpoint, request.getEndpoint());
        parameters.put(Parameter.identity, request.getIdentity());
        parameters.put(Parameter.invalidate_handle, request.getInvalidateHandle());
        parameters.put(Parameter.response_nonce, request.getResponseNonce());
        parameters.put(Parameter.return_to, request.getReturnTo().toString());
        parameters.put(Parameter.sig, request.getSignature());
        parameters.put(Parameter.signed, StringUtils.join(request.getSignedFields(), ","));
    }

}