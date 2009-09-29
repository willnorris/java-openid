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

import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.common.OpenIDConstants.Parameter;
import edu.internet2.middleware.openid.common.OpenIDConstants.SessionType;
import edu.internet2.middleware.openid.message.AssociationResponse;
import edu.internet2.middleware.openid.message.encoding.EncodingUtils;

/**
 * Marshaller for {@link AssociationResponse} messages.
 */
public class AssociationResponseMarshaller extends AbstractMessageMarshaller<AssociationResponse> {

    /** {@inheritDoc} */
    public void marshallParameters(AssociationResponse response, ParameterMap parameters) {
        parameters.put(Parameter.assoc_type.QNAME, response.getAssociationType().toString());

        SessionType sessionType = response.getSessionType();
        parameters.put(Parameter.session_type.QNAME, sessionType.toString());

        String macKey = EncodingUtils.encodeSecretKey(response.getMacKey());

        if (sessionType.equals(SessionType.DH_SHA1) || sessionType.equals(SessionType.DH_SHA256)) {
            parameters.put(Parameter.enc_mac_key.QNAME, macKey);
            String publicKey = EncodingUtils.encodePublicKey(response.getDHServerPublic());
            parameters.put(Parameter.dh_server_public.QNAME, publicKey);
        } else {
            parameters.put(Parameter.mac_key.QNAME, macKey);
        }

        parameters.put(Parameter.assoc_handle.QNAME, response.getAssociationHandle());
        parameters.put(Parameter.expires_in.QNAME, response.getLifetime().toString());
    }

}