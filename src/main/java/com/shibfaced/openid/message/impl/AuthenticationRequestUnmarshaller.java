
package com.shibfaced.openid.message.impl;

import java.util.Map;

import com.shibfaced.openid.message.AuthenticationRequest;
import com.shibfaced.openid.message.Unmarshaller;
import com.shibfaced.openid.message.Message.Parameter;

/**
 * AuthenticationRequestUnmarshaller.
 */
public class AuthenticationRequestUnmarshaller implements Unmarshaller<AuthenticationRequest> {

    /** {@inheritDoc} */
    public AuthenticationRequest unmarshall(Map<String, String> parameters) {
        AuthenticationRequestImpl request = new AuthenticationRequestImpl();

        request.setAssociationHandle(parameters.get(Parameter.assoc_handle.toString()));

        // TODO claimed_id and identity MUST appear together
        request.setClaimedId(parameters.get(Parameter.claimed_id.toString()));
        request.setIdentity(parameters.get(Parameter.identity.toString()));

        request.setMode(parameters.get(Parameter.mode.toString()));
        request.setRealm(parameters.get(Parameter.realm.toString()));
        request.setReturnTo(parameters.get(Parameter.return_to.toString()));
        
        return request;
    }

}