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

import edu.internet2.middleware.openid.message.AuthenticationRequest;
import edu.internet2.middleware.openid.message.ParameterMap;
import edu.internet2.middleware.openid.message.Message.Parameter;

/**
 * Marshaller for {@link AuthenticationRequest} messages.
 */
public class AuthenticationRequestMarshaller extends AbstractMessageMarshaller<AuthenticationRequest> {

    /** {@inheritDoc} */
    public void marshallParameters(AuthenticationRequest request, ParameterMap parameters) {
        parameters.put(Parameter.mode, request.getMode());

        // TODO claimed_id and identity MUST appear together
        parameters.put(Parameter.claimed_id, request.getClaimedId());
        parameters.put(Parameter.identity, request.getIdentity());

        parameters.put(Parameter.assoc_handle, request.getAssociationHandle());
        parameters.put(Parameter.return_to, request.getReturnTo().toString());
        parameters.put(Parameter.realm, request.getRealm());
    }

}