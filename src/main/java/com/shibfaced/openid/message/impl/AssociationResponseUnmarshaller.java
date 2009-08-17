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
import com.shibfaced.openid.message.AssociationResponse;
import com.shibfaced.openid.message.Unmarshaller;
import com.shibfaced.openid.message.Message.Parameter;

/**
 * AssociationResponseUnmarshaller.
 */
public class AssociationResponseUnmarshaller implements Unmarshaller<AssociationResponse> {

    /** {@inheritDoc} */
    public AssociationResponse unmarshall(Map<String, String> parameters) {
        AssociationResponseImpl response = new AssociationResponseImpl();

        SessionType sessionType = SessionType.getType(parameters.get(Parameter.session_type.toString()));

        response.setAssociationHandle(parameters.get(Parameter.assoc_handle.toString()));
        response.setAssociationType(parameters.get(Parameter.assoc_type.toString()));
        response.setSessionType(sessionType);
        response.setLifetime(Integer.parseInt(parameters.get(Parameter.expires_in.toString())));

        if (sessionType.equals(SessionType.DH_SHA1) || sessionType.equals(SessionType.DH_SHA256)) {
            response.setDhPublicKey(parameters.get(Parameter.dh_server_public.toString()));
            response.setMACKey(parameters.get(Parameter.enc_mac_key.toString()));
        } else if (sessionType.equals(SessionType.no_encryption)) {
            response.setMACKey(parameters.get(Parameter.mac_key.toString()));
        }

        return response;
    }
}