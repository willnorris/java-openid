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

import java.net.URL;

/**
 * Request to verify a positive authentication assertion. All of the data from the assertion to be verified is included
 * in the verification request.
 */
public interface VerifyRequest extends SignableMessage {

    /**
     * Message mode for verification requests.
     */
    public static final String MODE = "check_authentication";

    /**
     * The OpenID Provider endpoint URL.
     * 
     * @return the endpoint URL
     */
    public String getEndpoint();

    /**
     * Set the OpenID Provider endpoint URL.
     * 
     * @param newEndpoint the endpoint URL
     */
    public void setEndpoint(String newEndpoint);

    /**
     * The claimed identifier.
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
     * The OpenID Provider local identifier.
     * 
     * @return the local identifier
     */
    public String getIdentity();

    /**
     * Set the local identifier.
     * 
     * @param newIdentity the local identifier
     */
    public void setIdentity(String newIdentity);

    /**
     * The return-to URL from the authentication request.
     * 
     * @return return-to URL
     */
    public URL getReturnTo();

    /**
     * Set the return to URL.
     * 
     * @param newReturnTo the return to URL
     */
    public void setReturnTo(URL newReturnTo);

    /**
     * Unique nonce for this response.
     * 
     * @return the nonce
     */
    public String getResponseNonce();

    /**
     * Set the unique nonce for this response.
     * 
     * @param newNonce the response nonce
     */
    public void setResponseNonce(String newNonce);

    /**
     * The association handle from the authentication request, if it was invalid.
     * 
     * @return the invalid handle
     */
    public String getInvalidateHandle();

    /**
     * Set the association handle to invalidate.
     * 
     * @param newHandle the handle to invalidate
     */
    public void setInvalidateHandle(String newHandle);

    /**
     * The association handle that was used to sign this assertion.
     * 
     * @return the association handle
     */
    public String getAssociationHandle();

    /**
     * Set the association handle that was used to sign this assertion.
     * 
     * @param newHandle the association handle used to sign this assertion
     */
    public void setAssociationHandle(String newHandle);

}