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

import java.util.Map;

import com.shibfaced.openid.association.Association.SessionType;
import com.shibfaced.openid.message.AssociationRequest;
import com.shibfaced.openid.message.Unmarshaller;
import com.shibfaced.openid.message.Message.Parameter;

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
            request.setDhConsumerPublic(parameters.get(Parameter.dh_consumer_public.toString()));
            request.setDhGen(parameters.get(Parameter.dh_gen.toString()));
            request.setDhModulus(parameters.get(Parameter.dh_modulus.toString()));
        }

        return request;
    }

}