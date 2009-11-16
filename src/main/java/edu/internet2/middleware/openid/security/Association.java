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

package edu.internet2.middleware.openid.security;

import java.util.Date;

import javax.crypto.SecretKey;

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
    public Date getExpiration();

    /**
     * Get an identifier for the entity this association is to be used with. OpenID Provider entities are often
     * identified by their endpoint URL. OpenID Consumer entities are often identified by their realm.
     * 
     * @return the entity identifier
     */
    public String getEntity();

    /**
     * Get the MAC Key for the association.
     * 
     * @return the MAC Key
     */
    public SecretKey getMacKey();

    /**
     * Whether this a private association (one that does not have shared keys). OpenID Providers MUST use only private
     * associations for verifying signatures.
     * 
     * @return whether this association is private
     */
    public boolean isPrivate();

}