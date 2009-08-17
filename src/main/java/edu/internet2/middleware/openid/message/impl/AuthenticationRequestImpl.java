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

import edu.internet2.middleware.openid.message.AuthenticationRequest;

/**
 * AuthenticationRequestImpl.
 */
public class AuthenticationRequestImpl extends AbstractMessage implements AuthenticationRequest {

    /** {@inheritDoc} */
    public String getAssociationHandle() {
        return parameters.get(Parameter.assoc_handle);
    }

    /** {@inheritDoc} */
    public String getClaimedId() {
        return parameters.get(Parameter.claimed_id);
    }

    /** {@inheritDoc} */
    public String getIdentity() {
        return parameters.get(Parameter.identity);
    }

    /** {@inheritDoc} */
    public String getRealm() {
        return parameters.get(Parameter.realm);
    }

    /** {@inheritDoc} */
    public String getReturnTo() {
        return parameters.get(Parameter.return_to);
    }

    /** {@inheritDoc} */
    public String getMode() {
        return parameters.get(Parameter.mode);
    }

    /**
     * Set the association handle.
     * 
     * @param handle the associationHandle to set
     */
    public void setAssociationHandle(String handle) {
        parameters.put(Parameter.assoc_handle, handle);
    }

    /**
     * Set the claimed Id.
     * 
     * @param claimedId the claimedId to set
     */
    public void setClaimedId(String claimedId) {
        parameters.put(Parameter.claimed_id, claimedId);
    }

    /**
     * Set the local identity.
     * 
     * @param identity the identity to set
     */
    public void setIdentity(String identity) {
        parameters.put(Parameter.identity, identity);
    }

    /**
     * Set the realm.
     * 
     * @param realm the realm to set
     */
    public void setRealm(String realm) {
        parameters.put(Parameter.realm, realm);
    }

    /**
     * Set the return to address.
     * 
     * @param returnTo the returnTo to set
     */
    public void setReturnTo(String returnTo) {
        parameters.put(Parameter.return_to, returnTo);
    }

    /**
     * Set the mode.
     * 
     * @param mode the mode to set
     */
    public void setMode(String mode) {
        parameters.put(Parameter.mode, mode);
    }

}