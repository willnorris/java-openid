
package com.shibfaced.openid.message.impl;

import java.util.HashMap;
import java.util.Map;

import com.shibfaced.openid.association.Association.AssociationType;
import com.shibfaced.openid.message.AssociationError;
import com.shibfaced.openid.message.Marshaller;
import com.shibfaced.openid.message.Message.Parameter;

/**
 * AssociationErrorMarshaller.
 */
public class AssociationErrorMarshaller implements Marshaller<AssociationError> {

    /** {@inheritDoc} */
    public Map<String, String> marshall(AssociationError response) {
        Map<String, String> parameters = new HashMap<String, String>();

        AssociationType associationType = response.getAssociationType();
        if (associationType != null) {
            parameters.put(Parameter.assoc_type.toString(), associationType.toString());

            parameters.put(Parameter.error.toString(), response.getError());
            parameters.put(Parameter.error_code.toString(), response.getErrorCode());
        }

        parameters.put(Parameter.session_type.toString(), response.getSessionType().toString());

        return parameters;
    }

}