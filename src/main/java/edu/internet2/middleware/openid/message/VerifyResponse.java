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
 * Response to a verification request.
 */
public interface VerifyResponse extends ValidatingMessage {

    /**
     * Whether the signature of the verification request is valid.
     * 
     * @return if the signature is valid
     */
    public boolean isValid();

    /**
     * Set whether the signature of the verification request is valid.
     * 
     * @param newValid if the signature is valid
     */
    public void setValid(boolean newValid);

    /**
     * The "invalidate_handle" value sent in the verification request, if the OpenID Provider confirms it is invalid.
     * 
     * @return the confirmed invalid handle
     */
    public String getInvalidateHandle();

    /**
     * Set the association handle to invalidate.
     * 
     * @param newHandle the association handle to invalidate
     */
    public void setInvalidateHandle(String newHandle);
}