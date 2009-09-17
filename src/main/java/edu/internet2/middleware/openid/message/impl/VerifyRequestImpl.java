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
import edu.internet2.middleware.openid.message.VerifyRequest;

/**
 * Implementation of {@link VerifyRequest}.
 */
public class VerifyRequestImpl extends AbstractSignedMessage implements VerifyRequest {

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
    public String getMode() {
        return PositiveAssertion.MODE;
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
    public String getEndpoint() {
        return endpoint;
    }

    /** {@inheritDoc} */
    public void setEndpoint(String newEndpoint) {
        endpoint = newEndpoint;
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
    public String getInvalidateHandle() {
        return invalidateHandle;
    }

    /** {@inheritDoc} */
    public void setInvalidateHandle(String newInvalidateHandle) {
        invalidateHandle = newInvalidateHandle;
    }

    /** {@inheritDoc} */
    public String getResponseNonce() {
        return nonce;
    }

    /** {@inheritDoc} */
    public void setResponseNonce(String newNonce) {
        nonce = newNonce;
    }

    /** {@inheritDoc} */
    public URL getReturnTo() {
        return returnTo;
    }

    /** {@inheritDoc} */
    public void setReturnTo(URL newReturnTo) {
        returnTo = newReturnTo;
    }

}