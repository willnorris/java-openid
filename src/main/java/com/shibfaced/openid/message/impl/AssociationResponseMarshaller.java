/*
 * Copyright [2009] [University Corporation for Advanced Internet Development, Inc.]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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