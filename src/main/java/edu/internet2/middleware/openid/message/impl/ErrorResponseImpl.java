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

import edu.internet2.middleware.openid.message.ErrorResponse;

/**
 * ErrorResponseImpl.
 */
public class ErrorResponseImpl extends AbstractMessage implements ErrorResponse {

    /** Contact address. */
    private String contact;

    /** Error message. */
    private String error;

    /** Reference token. */
    private String reference;

    /** {@inheritDoc} */
    public String getContact() {
        return contact;
    }

    /** {@inheritDoc} */
    public String getError() {
        return error;
    }

    /** {@inheritDoc} */
    public String getReference() {
        return reference;
    }

    /**
     * Throws UnsupportedOperationException. Association errors do not have a mode value.
     * 
     * @return mode
     */
    public String getMode() {
        throw new UnsupportedOperationException();
    }

    /**
     * Set the contact.
     * 
     * @param newContact the contact to set
     */
    public void setContact(String newContact) {
        contact = newContact;
    }

    /**
     * Set the error message.
     * 
     * @param newError the error to set
     */
    public void setError(String newError) {
        error = newError;
    }

    /**
     * Set the error reference.
     * 
     * @param newReference the reference to set
     */
    public void setReference(String newReference) {
        reference = newReference;
    }

}