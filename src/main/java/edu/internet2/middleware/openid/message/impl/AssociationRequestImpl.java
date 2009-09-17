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
import java.security.PublicKey;

import edu.internet2.middleware.openid.association.Association.AssociationType;
import edu.internet2.middleware.openid.association.Association.SessionType;
import edu.internet2.middleware.openid.message.AssociationRequest;

/**
 * Implementation of {@link AssociationRequest}.
 */
public class AssociationRequestImpl extends AbstractMessage implements AssociationRequest {

    /** Diffie-Hellman public key. */
    private PublicKey dhConsumerPublic;

    /** Diffie-Hellman generator. */
    private BigInteger dhGen;

    /** Diffie-Hellman modulus. */
    private BigInteger dhModulus;

    /**
     * Association type.
     */
    private AssociationType associationType;

    /**
     * Association session type.
     */
    private SessionType sessionType;

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
    public PublicKey getDHConsumerPublic() {
        return dhConsumerPublic;
    }

    /** {@inheritDoc} */
    public void setDHConsumerPublic(PublicKey newConsumerPublic) {
        dhConsumerPublic = newConsumerPublic;
    }

    /** {@inheritDoc} */
    public BigInteger getDHGen() {
        return dhGen;
    }

    /** {@inheritDoc} */
    public void setDHGen(BigInteger newGen) {
        dhGen = newGen;
    }

    /** {@inheritDoc} */
    public BigInteger getDHModulus() {
        return dhModulus;
    }

    /** {@inheritDoc} */
    public void setDHModulus(BigInteger newModulus) {
        dhModulus = newModulus;
    }

}