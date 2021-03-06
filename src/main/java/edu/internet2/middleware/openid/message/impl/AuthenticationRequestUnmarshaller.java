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
import edu.internet2.middleware.openid.message.AuthenticationRequest;

/**
 * Unmarshaller for {@link AuthenticationRequest} messages.
 */
public class AuthenticationRequestUnmarshaller extends AbstractMessageUnmarshaller<AuthenticationRequest> {

    /** {@inheritDoc} */
    public void unmarshallParameters(AuthenticationRequest request, ParameterMap parameters) {
        request.setAssociationHandle(parameters.get(Parameter.assoc_handle.QNAME));

        // TODO claimed_id and identity MUST appear together
        request.setClaimedId(parameters.get(Parameter.claimed_id.QNAME));
        request.setIdentity(parameters.get(Parameter.identity.QNAME));

        request.setMode(parameters.get(Parameter.mode.QNAME));
        request.setRealm(parameters.get(Parameter.realm.QNAME));
        request.setReturnTo(parameters.get(Parameter.return_to.QNAME));
    }

}