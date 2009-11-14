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

import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;
import edu.internet2.middleware.openid.common.OpenIDConstants.SessionType;
import edu.internet2.middleware.openid.message.AssociationRequest;
import edu.internet2.middleware.openid.message.validation.AbstractValidatingMessage;

/**
 * Implementation of {@link AssociationRequest}.
 */
public class AssociationRequestImpl extends AbstractValidatingMessage implements AssociationRequest {

    /** Association type. */
    private AssociationType associationType;

    /** Association session type. */
    private SessionType sessionType;

    /** Diffie-Hellman public key. */
    private DHPublicKey dhConsumerPublic;

    /** Diffie-Hellman parameters. */
    private DHParameterSpec dhParameters;

    /** {@inheritDoc} */
    public String getMode() {
        return AssociationRequest.MODE;
    }

    /** {@inheritDoc} */
    public AssociationType getAssociationType() {
        return associationType;
    }

    /** {@inheritDoc} */
    public void setAssociationType(AssociationType type) {
        associationType = type;
    }

    /** {@inheritDoc} */
    public SessionType getSessionType() {
        return sessionType;
    }

    /** {@inheritDoc} */
    public void setSessionType(SessionType type) {
        sessionType = type;
    }

    /** {@inheritDoc} */
    public DHPublicKey getDHConsumerPublic() {
        return dhConsumerPublic;
    }

    /** {@inheritDoc} */
    public void setDHConsumerPublic(DHPublicKey newConsumerPublic) {
        dhConsumerPublic = newConsumerPublic;
    }

    /** {@inheritDoc} */
    public DHParameterSpec getDHParameters() {
        return dhParameters;
    }

    /** {@inheritDoc} */
    public void setDHParameters(DHParameterSpec newParameters) {
        dhParameters = newParameters;
    }

}