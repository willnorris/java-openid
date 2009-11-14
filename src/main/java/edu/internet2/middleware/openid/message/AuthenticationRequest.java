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

import edu.internet2.middleware.openid.message.validation.ValidatingMessage;

/**
 * OpenID authentication request.
 */
public interface AuthenticationRequest extends ValidatingMessage {

    /**
     * Message mode for authentication requests in which the relying party does not wish for the OpenID Provider to
     * interact with the user.
     */
    public static final String MODE_IMMEDIATE = "checkid_immediate";

    /**
     * Message mode for authentication requests in which the relying party wishes for the OpenID Provider to interact
     * with the user if necessary.
     */
    public static final String MODE_INTERACTIVE = "checkid_setup";

    /**
     * Get the claimed identifier.
     * 
     * @return the claimed identifier
     */
    public String getClaimedId();

    /**
     * Set the claimed identifier.
     * 
     * @param newClaimedId the claimed identifier
     */
    public void setClaimedId(String newClaimedId);

    /**
     * Get the OpenID Provider local identifier.
     * 
     * @return the local identifier
     */
    public String getIdentity();

    /**
     * Set the OpenID Provider local identifier.
     * 
     * @param newIdentity the local identifier
     */
    public void setIdentity(String newIdentity);

    /**
     * Get the handle for the established association.
     * 
     * @return the association handle
     */
    public String getAssociationHandle();

    /**
     * Set the handle for the established association.
     * 
     * @param newHandle the association handle
     */
    public void setAssociationHandle(String newHandle);

    /**
     * Get the URL to which the OpenID Provider should return the user with the response.
     * 
     * @return the return-to URL
     */
    public String getReturnTo();

    /**
     * Set the return to URL.
     * 
     * @param newReturnTo the return to URL
     */
    public void setReturnTo(String newReturnTo);

    /**
     * Get the URL pattern the OpenID Provider should ask the end user to trust.
     * 
     * @return the realm URL pattern
     */
    public String getRealm();

    /**
     * Set the trust realm.
     * 
     * @param newRealm the trust realm
     */
    public void setRealm(String newRealm);

    /**
     * Set the mode of this message.
     * 
     * @param newMode the mode of this message
     */
    public void setMode(String newMode);

    /**
     * Check if this authentication request is an immediate request.
     * 
     * @return if this is an immediate authentication request
     */
    public boolean isImmediate();

}