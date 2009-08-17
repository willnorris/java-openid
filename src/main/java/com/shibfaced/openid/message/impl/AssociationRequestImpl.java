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

import com.shibfaced.openid.association.Association.AssociationType;
import com.shibfaced.openid.association.Association.SessionType;
import com.shibfaced.openid.message.AssociationRequest;

/**
 * AssociationRequestImpl.
 */
public class AssociationRequestImpl extends AbstractMessage implements AssociationRequest {

    /**
     * Association type.
     */
    private AssociationType associationType;

    /**
     * Association session type.
     */
    private SessionType sessionType;

    /** {@inheritDoc} */
    public AssociationType getAssociationType() {
        return associationType;
    }

    /** {@inheritDoc} */
    public String getDHConsumerPublic() {
        return parameters.get(Parameter.dh_consumer_public);
    }

    /** {@inheritDoc} */
    public String getDHGen() {
        return parameters.get(Parameter.dh_gen);
    }

    /** {@inheritDoc} */
    public String getDHModulus() {
        return parameters.get(Parameter.dh_modulus);
    }

    /** {@inheritDoc} */
    public SessionType getSessionType() {
        return sessionType;
    }

    /** {@inheritDoc} */
    public String getMode() {
        return AssociationRequest.MODE;
    }

    /**
     * Set association type.
     * 
     * @param type the association type to set
     */
    public void setAssociationType(AssociationType type) {
        associationType = type;
    }

    /**
     * Set association type.
     * 
     * @param type the association type to set
     */
    public void setAssociationType(String type) {
        setAssociationType(AssociationType.getType(type));
    }

    /**
     * Set association session type.
     * 
     * @param type the session type to set
     */
    public void setSessionType(SessionType type) {
        sessionType = type;
    }

    /**
     * Set association session type.
     * 
     * @param type name of the session type to set
     */
    public void setSessionType(String type) {
        setSessionType(SessionType.getType(type));
    }

    /**
     * Set relying party's Diffie-Hellman public key.
     * 
     * @param dhConsumerPublic the dhConsumerPublic to set
     */
    public void setDhConsumerPublic(String dhConsumerPublic) {
        parameters.put(Parameter.dh_consumer_public, dhConsumerPublic);
    }

    /**
     * Set the Diffie-Hellman generator.
     * 
     * @param dhGen the dhGen to set
     */
    public void setDhGen(String dhGen) {
        parameters.put(Parameter.dh_gen, dhGen);
    }

    /**
     * Set the Diffie-Hellman modulus.
     * 
     * @param dhModulus the dhModulus to set
     */
    public void setDhModulus(String dhModulus) {
        parameters.put(Parameter.dh_modulus, dhModulus);
    }

}