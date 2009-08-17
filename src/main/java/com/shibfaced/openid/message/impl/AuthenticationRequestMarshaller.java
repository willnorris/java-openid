
package com.shibfaced.openid.message.impl;

import java.util.HashMap;
import java.util.Map;

import com.shibfaced.openid.message.AuthenticationRequest;
import com.shibfaced.openid.message.Marshaller;
import com.shibfaced.openid.message.Message.Parameter;

/**
 * AuthenticationRequestMarshaller.
 */
public class AuthenticationRequestMarshaller implements Marshaller<AuthenticationRequest> {

    /** {@inheritDoc} */
    public Map<String, String> marshall(AuthenticationRequest request) {
        Map<String, String> parameters = new HashMap<String, String>();

        parameters.put(Parameter.mode.toString(), request.getMode());

        // TODO claimed_id and identity MUST appear together
        parameters.put(Parameter.claimed_id.toString(), request.getClaimedId());
        parameters.put(Parameter.identity.toString(), request.getIdentity());

        parameters.put(Parameter.assoc_handle.toString(), request.getAssociationHandle());
        parameters.put(Parameter.return_to.toString(), request.getReturnTo());
        parameters.put(Parameter.realm.toString(), request.getRealm());
        return parameters;
    }

}