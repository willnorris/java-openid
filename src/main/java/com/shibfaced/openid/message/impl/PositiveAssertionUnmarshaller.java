
package com.shibfaced.openid.message.impl;

import java.util.Arrays;
import java.util.Map;

import com.shibfaced.openid.message.PositiveAssertion;
import com.shibfaced.openid.message.Unmarshaller;
import com.shibfaced.openid.message.Message.Parameter;

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