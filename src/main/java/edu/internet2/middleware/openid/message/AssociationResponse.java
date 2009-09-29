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

package edu.internet2.middleware.openid.message;

import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;

import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;
import edu.internet2.middleware.openid.common.OpenIDConstants.SessionType;

/**
 * Successful response to an association request.
 */
public interface AssociationResponse extends Message {

    /**
     * The association handle is used as a key to refer to this association in subsequent messages.
     * 
     * @return the association handle.
     */
    public String getAssociationHandle();

    /**
     * Set the association handle.
     * 
     * @param newHandle the association handle
     */
    public void setAssociationHandle(String newHandle);

    /**
     * The session encryption method for this association.
     * 
     * @return the session type
     */
    public SessionType getSessionType();

    /**
     * Set the session type.
     * 
     * @param newSessionType the session type
     */
    public void setSessionType(SessionType newSessionType);

    /**
     * The associate type algorithm for this association.
     * 
     * @return the association type
     */
    public AssociationType getAssociationType();

    /**
     * Set the association type.
     * 
     * @param newAssociationType the association type
     */
    public void setAssociationType(AssociationType newAssociationType);

    /**
     * The lifetime, in seconds, of this association.
     * 
     * @return the lifetime
     */
    public Integer getLifetime();

    /**
     * Set the lifetime of the association
     * 
     * @param newLifetime the lifetime of the association
     */
    public void setLifetime(Integer newLifetime);

    /**
     * The MAC key (shared secret) for this association. Depending on the session type, this may be the plain-text MAC
     * key or encrypted with the secret Diffie-Hellman value.
     * 
     * @return the MAC key
     */
    public SecretKey getMacKey();

    /**
     * Set the MAC key for the association.
     * 
     * @param newKey the MAC key
     */
    public void setMacKey(SecretKey newKey);

    /**
     * The OpenID Provider's Diffie-Hellman public key.
     * 
     * @return the DH server key
     */
    public DHPublicKey getDHServerPublic();

    /**
     * Set the OpenID Provider's Diffie-Hellman public key.
     * 
     * @param newPublicKey the DH server key
     */
    public void setDHServerPublic(DHPublicKey newPublicKey);

}