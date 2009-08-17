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
import edu.internet2.middleware.openid.message.AssociationRequest;
import edu.internet2.middleware.openid.message.Message.Parameter;

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

                String modulus = Base64.encodeBytes(request.getDHModulus().toByteArray());
                parameters.put(Parameter.dh_modulus.toString(), modulus);

                String gen = Base64.encodeBytes(request.getDHGen().toByteArray());
                parameters.put(Parameter.dh_gen.toString(), gen);

                String publicKey = Base64.encodeBytes(request.getDHConsumerPublic().getEncoded());
                parameters.put(Parameter.dh_consumer_public.toString(), publicKey);

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