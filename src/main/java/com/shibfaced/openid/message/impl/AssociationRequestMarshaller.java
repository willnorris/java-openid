
package com.shibfaced.openid.message.impl;

import java.util.HashMap;
import java.util.Map;

import com.shibfaced.openid.association.Association.AssociationType;
import com.shibfaced.openid.message.AssociationRequest;
import com.shibfaced.openid.message.Message.Parameter;

/**
 * AssociationRequestMarshaller.
 */
public class AssociationRequestMarshaller extends AbstractMessageMarshaller<AssociationRequest> {

    /** {@inheritDoc} */
    protected Map<String, String> marshallMessage(AssociationRequest request) {
        Map<String, String> parameters = new HashMap<String, String>();

        AssociationType associationType = request.getAssociationType();
        if (associationType != null) {
            parameters.put(Parameter.assoc_type.toString(), associationType.toString());

            if (associationType.equals(AssociationType.HMAC_SHA1)
                    || associationType.equals(AssociationType.HMAC_SHA256)) {

                parameters.put(Parameter.dh_modulus.toString(), request.getDHModulus());
                parameters.put(Parameter.dh_gen.toString(), request.getDHGen());
                parameters.put(Parameter.dh_consumer_public.toString(), request.getDHConsumerPublic());

            }
        }

        parameters.put(Parameter.session_type.toString(), request.getSessionType().toString());

        return parameters;
    }

    /** {@inheritDoc} */
    public Class<AssociationRequest> getType() {
        return AssociationRequest.class;
    }
}