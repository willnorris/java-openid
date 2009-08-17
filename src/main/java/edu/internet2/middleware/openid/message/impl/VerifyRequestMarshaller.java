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
import edu.internet2.middleware.openid.message.VerifyRequest;
import edu.internet2.middleware.openid.message.Message.Parameter;
import edu.internet2.middleware.openid.util.StringUtils;

/**
 * VerifyRequestMarshaller.
 */
public class VerifyRequestMarshaller implements Marshaller<VerifyRequest> {

    /** {@inheritDoc} */
    public Map<String, String> marshall(VerifyRequest request) {
        Map<String, String> parameters = new HashMap<String, String>();

        parameters.put(Parameter.assoc_handle.toString(), request.getAssociationHandle());
        parameters.put(Parameter.claimed_id.toString(), request.getClaimedId());
        parameters.put(Parameter.op_endpoint.toString(), request.getEndpoint());
        parameters.put(Parameter.identity.toString(), request.getIdentity());
        parameters.put(Parameter.invalidate_handle.toString(), request.getInvalidateHandle());
        parameters.put(Parameter.response_nonce.toString(), request.getResponseNonce());
        parameters.put(Parameter.return_to.toString(), request.getReturnTo());
        parameters.put(Parameter.sig.toString(), request.getSignature());
        parameters.put(Parameter.signed.toString(), StringUtils.join(request.getSignedFields(), ","));

        return parameters;
    }

}