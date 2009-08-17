
package com.shibfaced.openid.message.impl;

import java.util.HashMap;
import java.util.Map;

import com.shibfaced.openid.message.Marshaller;
import com.shibfaced.openid.message.PositiveAssertion;
import com.shibfaced.openid.message.Message.Parameter;
import com.shibfaced.openid.util.StringUtils;

/**
 * PositiveAssertionMarshaller.
 */
public class PositiveAssertionMarshaller implements Marshaller<PositiveAssertion> {

    /** {@inheritDoc} */
    public Map<String, String> marshall(PositiveAssertion response) {
        Map<String, String> parameters = new HashMap<String, String>();

        parameters.put(Parameter.assoc_handle.toString(), response.getAssociationHandle());
        parameters.put(Parameter.claimed_id.toString(), response.getClaimedId());
        parameters.put(Parameter.op_endpoint.toString(), response.getEndpoint());
        parameters.put(Parameter.identity.toString(), response.getIdentity());
        parameters.put(Parameter.invalidate_handle.toString(), response.getInvalidateHandle());
        parameters.put(Parameter.response_nonce.toString(), response.getResponseNonce());
        parameters.put(Parameter.return_to.toString(), response.getReturnTo());
        parameters.put(Parameter.sig.toString(), response.getSignature());
        parameters.put(Parameter.signed.toString(), StringUtils.join(response.getSignedFields(), ","));

        return parameters;
    }

}