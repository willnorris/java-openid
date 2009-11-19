/*
 * Copyright 2009 University Corporation for Advanced Internet Development, Inc.
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

/**
 * Checks the validity of a nonce.
 */
public interface NonceValidator {

    /**
     * Check the validity of the nonce. To be valid, a nonce must have been issued within the configured lifetime, and
     * must not have been checked before.
     * 
     * @param issuer identifier for the nonce issuer
     * @param nonce nonce value
     * @return whether the nonce is valid
     */
    public boolean isValid(String issuer, String nonce);

    /**
     * Get the number of seconds that may elapse before an issued nonce is considered expired.
     * 
     * @return nonce lifetime
     */
    public int getLifetime();

    /**
     * Set the number of seconds that may elapse before an issued nonce is considered expired.
     * 
     * @param newLifetime nonce lifetime
     */
    public void setLifetime(int newLifetime);

}