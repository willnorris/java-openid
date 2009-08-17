package com.shibfaced.openid.message.impl;

import java.util.Map;

import com.shibfaced.openid.message.AssociationError;
import com.shibfaced.openid.message.Unmarshaller;
import com.shibfaced.openid.message.Message.Parameter;

/**
 * AssociationErrorUnmarshaller.
 */
public class AssociationErrorUnmarshaller implements Unmarshaller<AssociationError> {

    /** {@inheritDoc} */
    public AssociationError unmarshall(Map<String, String> parameters) {
        AssociationErrorImpl response = new AssociationErrorImpl();
        
        response.setAssociationType(parameters.get(Parameter.assoc_type.toString()));
        response.setSessionType(parameters.get(Parameter.session_type.toString()));
        response.setError(parameters.get(Parameter.error.toString()));
        
        return response;
    }

}