
package com.shibfaced.openid.message.impl;

import java.util.HashMap;
import java.util.Map;

import com.shibfaced.openid.association.Association.AssociationType;
import com.shibfaced.openid.message.AssociationResponse;
import com.shibfaced.openid.message.Marshaller;
import com.shibfaced.openid.message.Message.Parameter;

/**
 * AssociationResponseMarshaller.
 */
public class AssociationResponseMarshaller implements Marshaller<AssociationResponse> {

    /** {@inheritDoc} */
    public Map<String, String> marshall(AssociationResponse response) {
        Map<String, String> parameters = new HashMap<String, String>();

        AssociationType associationType = response.getAssociationType();
        if (associationType != null) {
            parameters.put(Parameter.assoc_type.toString(), associationType.toString());

            if (associationType.equals(AssociationType.HMAC_SHA1)
                    || associationType.equals(AssociationType.HMAC_SHA256)) {

                parameters.put(Parameter.enc_mac_key.toString(), response.getMACKey());
                parameters.put(Parameter.dh_server_public.toString(), response.getDHPublicKey());
            } else {
                parameters.put(Parameter.mac_key.toString(), response.getMACKey());
            }

        }

        parameters.put(Parameter.session_type.toString(), response.getSessionType().toString());
        parameters.put(Parameter.expires_in.toString(), response.getLifetime() + "");

        return parameters;
    }

}