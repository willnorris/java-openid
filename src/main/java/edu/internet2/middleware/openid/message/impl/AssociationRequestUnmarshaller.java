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
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

import edu.internet2.middleware.openid.association.Association.SessionType;
import edu.internet2.middleware.openid.message.AssociationRequest;
import edu.internet2.middleware.openid.message.Unmarshaller;
import edu.internet2.middleware.openid.message.Message.Parameter;

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

            try {
                byte[] publicKeyBytes = parameters.get(Parameter.dh_consumer_public.toString()).getBytes();
                X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKeyBytes);
                KeyFactory keyFactory = KeyFactory.getInstance("DH");
                PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
                request.setDhConsumerPublic(publicKey);
            } catch (NoSuchAlgorithmException e) {
                // TODO
            } catch (InvalidKeySpecException e) {
                // TODO
            }

            String gen = parameters.get(Parameter.dh_gen.toString());
            request.setDhGen(new BigInteger(gen));

            String modulus = parameters.get(Parameter.dh_modulus.toString());
            request.setDhModulus(new BigInteger(modulus));
        }

        return request;
    }
}