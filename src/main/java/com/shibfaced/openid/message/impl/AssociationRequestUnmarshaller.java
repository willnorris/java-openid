
package com.shibfaced.openid.message.impl;

import java.util.Map;

import com.shibfaced.openid.association.Association.SessionType;
import com.shibfaced.openid.message.AssociationRequest;
import com.shibfaced.openid.message.Unmarshaller;
import com.shibfaced.openid.message.Message.Parameter;

/**
 * AssociationRequestUnmarshaller.
 */
public class AssociationRequestUnmarshaller implements Unmarshaller<AssociationRequest> {

    /** {@inheritDoc} */
    public AssociationRequest unmarshall(Map<String, String> parameters) {
        AssociationRequestImpl request = new AssociationRequestImpl();
        
        SessionType sessionType = SessionType.getType(parameters.get(Parameter.session_type.toString()));
        request.setAssociationType(parameters.get(Parameter.assoc_type.toString()));
        request.setSessionType(sessionType);

        if (sessionType.equals(SessionType.DH_SHA1) || sessionType.equals(SessionType.DH_SHA256)) {
            request.setDhConsumerPublic(parameters.get(Parameter.dh_consumer_public.toString()));
            request.setDhGen(parameters.get(Parameter.dh_gen.toString()));
            request.setDhModulus(parameters.get(Parameter.dh_modulus.toString()));
        }

        return request;
    }

}