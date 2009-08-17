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

import java.security.Key;
import java.security.PublicKey;

import javax.crypto.Mac;

import edu.internet2.middleware.openid.association.Association.AssociationType;
import edu.internet2.middleware.openid.association.Association.SessionType;
import edu.internet2.middleware.openid.message.AssociationRequest;
import edu.internet2.middleware.openid.message.AssociationResponse;

/**
 * AssociationRequestImpl.
 */
public class AssociationResponseImpl extends AbstractMessage implements AssociationResponse {

    /** Association Handle. */
    private String associationHandle;

    /** Diffie-Hellman public key. */
    private PublicKey publicKey;

    /** MAC Key. */
    private Key macKey;

    /** Lifetime of the association. */
    private int lifetime;

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
    public SessionType getSessionType() {
        return sessionType;
    }

    /** {@inheritDoc} */
    public String getMode() {
        return AssociationRequest.MODE;
    }

    /** {@inheritDoc} */
    public String getAssociationHandle() {
        return associationHandle;
    }

    /** {@inheritDoc} */
    public PublicKey getDHServerPublic() {
        return publicKey;
    }

    /** {@inheritDoc} */
    public int getLifetime() {
        return lifetime;
    }

    /** {@inheritDoc} */
    public Key getMacKey() {
        return macKey;
    }

    /**
     * Set association handle.
     * 
     * @param newHandle the association handle to set
     */
    public void setAssociationHandle(String newHandle) {
        associationHandle = newHandle;
    }

    /**
     * Set association type.
     * 
     * @param type the associationType to set
     */
    public void setAssociationType(AssociationType type) {
        associationType = type;
    }

    /**
     * Set association type.
     * 
     * @param type the associationType to set
     */
    public void setAssociationType(String type) {
        setAssociationType(AssociationType.getType(type));
    }

    /**
     * Set association session type.
     * 
     * @param type the sessionType to set
     */
    public void setSessionType(SessionType type) {
        sessionType = type;
    }

    /**
     * Set association session type.
     * 
     * @param type the sessionType to set
     */
    public void setSessionType(String type) {
        setSessionType(SessionType.getType(type));
    }

    /**
     * Set OpenID Provider's Diffie-Hellman public key.
     * 
     * @param newPublicKey the DH public key to set
     */
    public void setPublicKey(PublicKey newPublicKey) {
        publicKey = newPublicKey;
    }

    /**
     * Set lifetime of response in seconds.
     * 
     * @param newLifetime the lifetime to set
     */
    public void setLifetime(int newLifetime) {
        lifetime = newLifetime;
    }

    /**
     * Set the MAC key.
     * 
     * @param newMacKey the MAC key to set
     */
    public void setMacKey(Key newMacKey) {
        macKey = newMacKey;
    }

    /**
     * Get the MAC Key parameter, determined by the session type.
     * 
     * @return the MAC Key parameter
     */
    private Parameter getMacKeyParameter() {
        if (sessionType == null) {
            throw new IllegalArgumentException("Session Type must be defined before getting or setting MAC Key");
        }

        if (sessionType.equals(SessionType.no_encryption)) {
            return Parameter.mac_key;
        } else {
            return Parameter.enc_mac_key;
        }
    }
}