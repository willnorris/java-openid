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

package edu.internet2.middleware.openid.message.impl;

import java.util.HashMap;
import java.util.Map;

import org.opensaml.xml.util.Base64;

import edu.internet2.middleware.openid.association.Association.AssociationType;
import edu.internet2.middleware.openid.message.AssociationResponse;
import edu.internet2.middleware.openid.message.Marshaller;
import edu.internet2.middleware.openid.message.Message.Parameter;

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

            String macKey = Base64.encodeBytes(response.getMacKey().getEncoded());

            if (associationType.equals(AssociationType.HMAC_SHA1)
                    || associationType.equals(AssociationType.HMAC_SHA256)) {

                parameters.put(Parameter.enc_mac_key.toString(), macKey);

                String publicKey = Base64.encodeBytes(response.getDHServerPublic().getEncoded());
                parameters.put(Parameter.dh_server_public.toString(), publicKey);
            } else {
                parameters.put(Parameter.mac_key.toString(), macKey);
            }

        }

        parameters.put(Parameter.session_type.toString(), response.getSessionType().toString());
        parameters.put(Parameter.expires_in.toString(), response.getLifetime() + "");

        return parameters;
    }

}