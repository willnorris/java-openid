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

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import org.apache.xml.security.exceptions.Base64DecodingException;
import org.apache.xml.security.utils.Base64;

import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;
import edu.internet2.middleware.openid.common.OpenIDConstants.Parameter;
import edu.internet2.middleware.openid.common.OpenIDConstants.SessionType;
import edu.internet2.middleware.openid.message.AssociationRequest;
import edu.internet2.middleware.openid.message.ParameterMap;
import edu.internet2.middleware.openid.security.AssociationUtils;

/**
 * Unmarshaller for {@link AssociationRequest} message.
 */
public class AssociationRequestUnmarshaller extends AbstractMessageUnmarshaller<AssociationRequest> {

    /** {@inheritDoc} */
    public void unmarshallParameters(AssociationRequest request, ParameterMap parameters) {

        SessionType sessionType = SessionType.getType(parameters.get(Parameter.session_type.QNAME));
        request.setAssociationType(AssociationType.getType(parameters.get(Parameter.assoc_type.QNAME)));

        if (sessionType != null) {
            request.setSessionType(sessionType);

            if (sessionType.equals(SessionType.DH_SHA1) || sessionType.equals(SessionType.DH_SHA256)) {

                String encodedKey = parameters.get(Parameter.dh_consumer_public.QNAME);
                if (encodedKey != null) {
                    try {
                        byte[] publicKeyBytes = Base64.decode(encodedKey);
                        PublicKey publicKey = AssociationUtils.loadPublicKey(publicKeyBytes);
                        request.setDHConsumerPublic(publicKey);
                    } catch (NoSuchAlgorithmException e) {
                        // TODO
                    } catch (InvalidKeySpecException e) {
                        // TODO
                    } catch (Base64DecodingException e) {
                        // TODO
                    }
                }

                try {
                    String gen = parameters.get(Parameter.dh_gen.QNAME);
                    request.setDHGen(new BigInteger(Base64.decode(gen)));
                } catch (Base64DecodingException e1) {
                    // TODO
                }

                try {
                    String modulus = parameters.get(Parameter.dh_modulus.QNAME);
                    request.setDHModulus(new BigInteger(Base64.decode(modulus)));
                } catch (Base64DecodingException e) {
                    // TODO
                }
            }
        }

    }
}