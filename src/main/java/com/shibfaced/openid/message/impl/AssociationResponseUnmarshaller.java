
package com.shibfaced.openid.message.impl;

import java.util.Map;

import com.shibfaced.openid.association.Association.SessionType;
import com.shibfaced.openid.message.AssociationResponse;
import com.shibfaced.openid.message.Unmarshaller;
import com.shibfaced.openid.message.Message.Parameter;

/**
 * AssociationResponseUnmarshaller.
 */
public class AssociationResponseUnmarshaller implements Unmarshaller<AssociationResponse> {

    /** {@inheritDoc} */
    public AssociationResponse unmarshall(Map<String, String> parameters) {
        AssociationResponseImpl response = new AssociationResponseImpl();

        SessionType sessionType = SessionType.getType(parameters.get(Parameter.session_type.toString()));
        
        response.setAssociationHandle(parameters.get(Parameter.assoc_handle.toString()));
        response.setAssociationType(parameters.get(Parameter.assoc_type.toString()));
        response.setSessionType(sessionType);        
        response.setLifetime(Integer.parseInt(parameters.get(Parameter.expires_in.toString())));
        
        if (sessionType.equals(SessionType.DH_SHA1) || sessionType.equals(SessionType.DH_SHA256)) {
            response.setDhPublicKey(parameters.get(Parameter.dh_server_public.toString()));
            response.setMACKey(parameters.get(Parameter.enc_mac_key.toString()));
        } else if (sessionType.equals(SessionType.no_encryption)) {
            response.setMACKey(parameters.get(Parameter.mac_key.toString()));
        }
        
        return response;
    }
}