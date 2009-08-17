
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