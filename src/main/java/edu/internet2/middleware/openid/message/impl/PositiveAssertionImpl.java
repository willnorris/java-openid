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

import edu.internet2.middleware.openid.message.PositiveAssertion;

/**
 * PositiveAssertionImpl.
 */
public class PositiveAssertionImpl extends AbstractSignedMessage implements PositiveAssertion {

    /** Association handle. */
    private String associationHandle;

    /** Claimed ID. */
    private String claimedId;

    /** Endpoint. */
    private String endpoint;

    /** Identity. */
    private String identity;

    /** Handle to invalidate. */
    private String invalidateHandle;

    /** Nonce. */
    private String nonce;

    /** Return to URL. */
    private URL returnTo;

    /** {@inheritDoc} */
    public String getAssociationHandle() {
        return associationHandle;
    }

    /** {@inheritDoc} */
    public String getClaimedId() {
        return claimedId;
    }

    /** {@inheritDoc} */
    public String getEndpoint() {
        return endpoint;
    }

    /** {@inheritDoc} */
    public String getIdentity() {
        return identity;
    }

    /** {@inheritDoc} */
    public String getInvalidateHandle() {
        return invalidateHandle;
    }

    /** {@inheritDoc} */
    public String getResponseNonce() {
        return nonce;
    }

    /** {@inheritDoc} */
    public URL getReturnTo() {
        return returnTo;
    }

    /** {@inheritDoc} */
    public String getMode() {
        return PositiveAssertion.MODE;
    }

    /**
     * Set the association handle.
     * 
     * @param newHandle the associationHandle to set
     */
    public void setAssociationHandle(String newHandle) {
        associationHandle = newHandle;
    }

    /**
     * Set the claimed Id.
     * 
     * @param newClaimedId the claimedId to set
     */
    public void setClaimedId(String newClaimedId) {
        claimedId = newClaimedId;
    }

    /**
     * Set the local identity.
     * 
     * @param newIdentity the identity to set
     */
    public void setIdentity(String newIdentity) {
        identity = newIdentity;
    }

    /**
     * Set the return to address.
     * 
     * @param newReturnTo the returnTo to set
     */
    public void setReturnTo(URL newReturnTo) {
        returnTo = newReturnTo;
    }

    /**
     * Set the endpoint.
     * 
     * @param newEndpoint the endpoint to set
     */
    public void setEndpoint(String newEndpoint) {
        endpoint = newEndpoint;
    }

    /**
     * Set the handle to invalidate.
     * 
     * @param newInvalidateHandle the invalidateHandle to set
     */
    public void setInvalidateHandle(String newInvalidateHandle) {
        invalidateHandle = newInvalidateHandle;
    }

    /**
     * Set the response nonce.
     * 
     * @param newNonce the responseNonce to set
     */
    public void setResponseNonce(String newNonce) {
        nonce = newNonce;
    }

}