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

import java.util.List;

/**
 * Response to an authentication request indicating that the OpenID Provider was successfully able to authenticate the
 * end user.
 */
public interface PositiveAssertion extends Message {

    /**
     * Message mode indicating a positive assertion.
     */
    public static final String MODE = "id_res";

    /**
     * The OpenID Provider endpoint URL.
     * 
     * @return the endpoint URL
     */
    public String getEndpoint();

    /**
     * The claimed identifier.
     * 
     * @return the claimed identifier
     */
    public String getClaimedId();

    /**
     * The OpenID Provider local identifier.
     * 
     * @return the local identifier
     */
    public String getIdentity();

    /**
     * The return-to URL from the authentication request.
     * 
     * @return return-to URL
     */
    public String getReturnTo();

    /**
     * Unique nonce for this response.
     * 
     * @return the nonce
     */
    public String getResponseNonce();

    /**
     * The association handle from the authentication request, if it was invalid.
     * 
     * @return the invalid handle
     */
    public String getInvalidateHandle();

    /**
     * The association handle that was used to sign this assertion.
     * 
     * @return the association handle
     */
    public String getAssociationHandle();

    /**
     * The message fields that were used to generate the message signature. Fields do not include the "openid." prefix.
     * 
     * @return the signed fields
     */
    public List<String> getSignedFields();

    /**
     * The Base64 encoded signature.
     * 
     * @return the signature
     */
    public String getSignature();

}