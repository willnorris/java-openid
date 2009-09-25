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

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;
import edu.internet2.middleware.openid.common.OpenIDConstants.Parameter;
import edu.internet2.middleware.openid.common.OpenIDConstants.SessionType;
import edu.internet2.middleware.openid.message.AssociationResponse;
import edu.internet2.middleware.openid.security.AssociationUtils;

/**
 * Marshaller for {@link AssociationResponse} messages.
 * 
 * The Server DH Public Key cannot be properly unmarshalled without the {@link DHParameterSpec} from the association
 * request this response is connected with. Therefore, the server public key is unmarshalled with the default DH
 * Parameters. Receivers of this unmarshalled message should verify that this is the correct value, and regenerate the
 * DHPublicKey if it is not.
 */
public class AssociationResponseUnmarshaller extends AbstractMessageUnmarshaller<AssociationResponse> {

    /** {@inheritDoc} */
    public void unmarshallParameters(AssociationResponse response, ParameterMap parameters) {

        SessionType sessionType = SessionType.getType(parameters.get(Parameter.session_type.QNAME));

        response.setAssociationHandle(parameters.get(Parameter.assoc_handle.QNAME));
        response.setAssociationType(AssociationType.getType(parameters.get(Parameter.assoc_type.QNAME)));
        response.setSessionType(sessionType);
        response.setLifetime(Integer.parseInt(parameters.get(Parameter.expires_in.QNAME)));

        String encodedMacKey = null;

        if (sessionType.equals(SessionType.DH_SHA1) || sessionType.equals(SessionType.DH_SHA256)) {

            try {
                String encodedKey = parameters.get(Parameter.dh_server_public.QNAME);
                // Temporarily unmarshall the public key using the default DHParameterSpec
                DHPublicKey publicKey = AssociationUtils.loadPublicKey(Base64.decodeBase64(encodedKey.getBytes()),
                        OpenIDConstants.DEFAULT_PARAMETER_SPEC);
                response.setDHServerPublic(publicKey);
            } catch (NoSuchAlgorithmException e) {
                // TODO
            } catch (InvalidKeySpecException e) {
                // TODO
            }

            encodedMacKey = parameters.get(Parameter.enc_mac_key.QNAME);
        } else if (sessionType.equals(SessionType.no_encryption)) {
            encodedMacKey = parameters.get(Parameter.mac_key.QNAME);
        }

        if (encodedMacKey != null) {
            Key macKey = new SecretKeySpec(Base64.decodeBase64(encodedMacKey.getBytes()), response.getAssociationType()
                    .getAlgorithm());
            response.setMacKey(macKey);
        }

    }
}