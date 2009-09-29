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

import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

import org.apache.commons.codec.binary.Base64;

import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.common.OpenIDConstants.Parameter;
import edu.internet2.middleware.openid.common.OpenIDConstants.SessionType;
import edu.internet2.middleware.openid.message.AssociationRequest;
import edu.internet2.middleware.openid.message.encoding.EncodingUtils;

/**
 * Marshaller for {@link AssociationRequest} message.
 */
public class AssociationRequestMarshaller extends AbstractMessageMarshaller<AssociationRequest> {

    /** {@inheritDoc} */
    protected void marshallParameters(AssociationRequest request, ParameterMap parameters) {
        parameters.put(Parameter.assoc_type.QNAME, request.getAssociationType().toString());

        SessionType sessionType = request.getSessionType();
        parameters.put(Parameter.session_type.QNAME, sessionType.toString());

        if (sessionType.equals(SessionType.DH_SHA1) || sessionType.equals(SessionType.DH_SHA256)) {

            DHParameterSpec dhParameters = request.getDHParameters();
            if (dhParameters != null) {
                byte[] modulus = Base64.encodeBase64(dhParameters.getP().toByteArray());
                parameters.put(Parameter.dh_modulus.QNAME, new String(modulus));

                byte[] gen = Base64.encodeBase64(dhParameters.getG().toByteArray());
                parameters.put(Parameter.dh_gen.QNAME, new String(gen));

            }

            DHPublicKey consumerPublic = request.getDHConsumerPublic();
            if (consumerPublic != null) {
                String publicKey = EncodingUtils.encodePublicKey(consumerPublic);
                parameters.put(Parameter.dh_consumer_public.QNAME, publicKey);
            }

        }
    }

    /** {@inheritDoc} */
    public Class<AssociationRequest> getType() {
        return AssociationRequest.class;
    }
}