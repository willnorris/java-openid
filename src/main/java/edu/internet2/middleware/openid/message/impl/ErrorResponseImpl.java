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
import edu.internet2.middleware.openid.message.validation.AbstractValidatingMessage;

/**
 * Implementation of {@link ErrorResponse}.
 */
public class ErrorResponseImpl extends AbstractValidatingMessage implements ErrorResponse {

    /** Contact address. */
    private String contact;

    /** Error message. */
    private String error;

    /** Reference token. */
    private String reference;

    /** {@inheritDoc} */
    public String getMode() {
        return ErrorResponse.MODE;
    }

    /** {@inheritDoc} */
    public String getContact() {
        return contact;
    }

    /** {@inheritDoc} */
    public void setContact(String newContact) {
        contact = newContact;
    }

    /** {@inheritDoc} */
    public String getError() {
        return error;
    }

    /** {@inheritDoc} */
    public void setError(String newError) {
        error = newError;
    }

    /** {@inheritDoc} */
    public String getReference() {
        return reference;
    }

    /** {@inheritDoc} */
    public void setReference(String newReference) {
        reference = newReference;
    }

}