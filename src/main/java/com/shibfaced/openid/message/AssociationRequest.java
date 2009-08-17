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

package com.shibfaced.openid.message;

import com.shibfaced.openid.association.Association.AssociationType;
import com.shibfaced.openid.association.Association.SessionType;

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
     * Get the preferred association session encryption method.
     * 
     * @return the session type
     */
    public SessionType getSessionType();

    /**
     * The Diffie-Hellman modulus.
     * 
     * @return DH modulus
     */
    public String getDHModulus();

    /**
     * The Diffie-Hellman generator.
     * 
     * @return the DH generator
     */
    public String getDHGen();

    /**
     * The Relying Party's Diffie-Hellman public key.
     * 
     * @return the DH key
     */
    public String getDHConsumerPublic();

}