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

package edu.internet2.middleware.openid.association;

import java.security.Key;

import org.joda.time.DateTime;

import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;

/**
 * An OpenID Association encapsulates a shared secret used to enable secure communication between two parties. The
 * association includes the shared secret key, the algorithm used to sign messages, and a shared public handle used to
 * identify the association.
 */
public interface Association {

    /**
     * Get the association handle.
     * 
     * @return the association handle
     */
    public String getHandle();

    /**
     * Get the association type.
     * 
     * @return the association type
     */
    public AssociationType getAssociationType();

    /**
     * Get the expiration instant of the association.
     * 
     * @return the expiration instant
     */
    public DateTime getExpiration();

    /**
     * Get the MAC Key for the association.
     * 
     * @return the MAC Key
     */
    public Key getMacKey();

}