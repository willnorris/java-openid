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

package com.shibfaced.openid.message.impl;

import java.util.Arrays;
import java.util.Map;

import com.shibfaced.openid.message.Unmarshaller;
import com.shibfaced.openid.message.VerifyRequest;
import com.shibfaced.openid.message.Message.Parameter;

/**
 * VerifyRequestUnmarshaller.
 */
public class VerifyRequestUnmarshaller implements Unmarshaller<VerifyRequest> {

    /** {@inheritDoc} */
    public VerifyRequest unmarshall(Map<String, String> parameters) {
        VerifyRequestImpl request = new VerifyRequestImpl();

        request.setAssociationHandle(parameters.get(Parameter.assoc_handle.toString()));
        request.setClaimedId(parameters.get(Parameter.claimed_id.toString()));
        request.setEndpoint(parameters.get(Parameter.op_endpoint.toString()));
        request.setIdentity(parameters.get(Parameter.identity.toString()));
        request.setInvalidateHandle(parameters.get(Parameter.invalidate_handle.toString()));
        request.setResponseNonce(parameters.get(Parameter.response_nonce.toString()));
        request.setReturnTo(parameters.get(Parameter.return_to.toString()));
        request.setSignature(parameters.get(Parameter.sig.toString()));
        request.getSignedFields().addAll(Arrays.asList(Parameter.signed.toString().split(",")));

        return request;
    }

}