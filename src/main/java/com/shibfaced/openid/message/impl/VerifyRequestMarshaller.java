
package com.shibfaced.openid.message.impl;

import java.util.HashMap;
import java.util.Map;

import com.shibfaced.openid.message.Marshaller;
import com.shibfaced.openid.message.VerifyRequest;
import com.shibfaced.openid.message.Message.Parameter;
import com.shibfaced.openid.util.StringUtils;

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