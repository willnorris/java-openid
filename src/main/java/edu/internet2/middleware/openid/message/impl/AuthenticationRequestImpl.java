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

import java.net.URL;

import edu.internet2.middleware.openid.message.AuthenticationRequest;

/**
 * Implementation of {@link AuthenticationRequest}.
 */
public class AuthenticationRequestImpl extends AbstractMessage implements AuthenticationRequest {

    /** Association handle. */
    private String associationHandle;

    /** Claimed ID. */
    private String claimedId;

    /** Identity. */
    private String identity;

    /** Realm. */
    private String realm;

    /** Return to URL. */
    private String returnTo;

    /** Message mode. */
    private String mode;

    /** {@inheritDoc} */
    public String getMode() {
        return mode;
    }

    /** {@inheritDoc} */
    public void setMode(String newMode) {
        mode = newMode;
    }

    /** {@inheritDoc} */
    public String getAssociationHandle() {
        return associationHandle;
    }

    /** {@inheritDoc} */
    public void setAssociationHandle(String newHandle) {
        associationHandle = newHandle;
    }

    /** {@inheritDoc} */
    public String getClaimedId() {
        return claimedId;
    }

    /** {@inheritDoc} */
    public void setClaimedId(String newClaimedId) {
        claimedId = newClaimedId;
    }

    /** {@inheritDoc} */
    public String getIdentity() {
        return identity;
    }

    /** {@inheritDoc} */
    public void setIdentity(String newIdentity) {
        identity = newIdentity;
    }

    /** {@inheritDoc} */
    public String getRealm() {
        return realm;
    }

    /** {@inheritDoc} */
    public void setRealm(String newRealm) {
        realm = newRealm;
    }

    /** {@inheritDoc} */
    public String getReturnTo() {
        return returnTo;
    }

    /** {@inheritDoc} */
    public void setReturnTo(String newReturnTo) {
        returnTo = newReturnTo;
    }

    /** {@inheritDoc} */
    public boolean isImmediate() {
        return AuthenticationRequest.MODE_IMMEDIATE.equals(mode);
    }

}