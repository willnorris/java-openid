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

import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;
import edu.internet2.middleware.openid.common.OpenIDConstants.SessionType;

/**
 * Request for the initiation of an association session.
 */
public interface AssociationRequest extends Message {

    /**
     * Message mode for association requests.
     */
    public static final String MODE = "associate";

    /**
     * Get the preferred association type algorithm.
     * 
     * @return the association type
     */
    public AssociationType getAssociationType();

    /**
     * Set the preferred association type algorithm.
     * 
     * @param newAssociationType the association type
     */
    public void setAssociationType(AssociationType newAssociationType);

    /**
     * Get the preferred association session encryption method.
     * 
     * @return the session type
     */
    public SessionType getSessionType();

    /**
     * Set the preferred association session encryption method.
     * 
     * @param newSessionType the session type
     */
    public void setSessionType(SessionType newSessionType);

    /**
     * Get the Diffie-Hellman parameters.
     * 
     * @return DH parameters
     */
    public DHParameterSpec getDHParameters();

    /**
     * Set the Diffie-Hellman parameters.
     * 
     * @param newParamaters DH parameters
     */
    public void setDHParameters(DHParameterSpec newParamaters);

    /**
     * Get the Relying Party's Diffie-Hellman public key.
     * 
     * @return the DH key
     */
    public DHPublicKey getDHConsumerPublic();

    /**
     * Set the Relying Party's Diffie-Hellman public key.
     * 
     * @param newPublicKey the DH key
     */
    public void setDHConsumerPublic(DHPublicKey newPublicKey);

}