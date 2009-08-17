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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import edu.internet2.middleware.openid.message.AuthenticationRequest;
import edu.internet2.middleware.openid.message.Unmarshaller;
import edu.internet2.middleware.openid.message.Message.Parameter;

/**
 * AuthenticationRequestUnmarshaller.
 */
public class AuthenticationRequestUnmarshaller implements Unmarshaller<AuthenticationRequest> {

    /** {@inheritDoc} */
    public AuthenticationRequest unmarshall(Map<String, String> parameters) {
        AuthenticationRequestImpl request = new AuthenticationRequestImpl();

        request.setAssociationHandle(parameters.get(Parameter.assoc_handle.toString()));

        // TODO claimed_id and identity MUST appear together
        request.setClaimedId(parameters.get(Parameter.claimed_id.toString()));
        request.setIdentity(parameters.get(Parameter.identity.toString()));

        request.setMode(parameters.get(Parameter.mode.toString()));
        request.setRealm(parameters.get(Parameter.realm.toString()));
        try {
            String returnTo = parameters.get(Parameter.return_to.toString());
            request.setReturnTo(new URL(returnTo));
        } catch (MalformedURLException e) {
            // TODO
        }

        return request;
    }

}