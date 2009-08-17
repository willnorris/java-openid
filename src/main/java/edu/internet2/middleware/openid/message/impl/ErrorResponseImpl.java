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

    /** {@inheritDoc} */
    public String getContact() {
        return parameters.get(Parameter.contact);
    }

    /** {@inheritDoc} */
    public String getError() {
        return parameters.get(Parameter.error);
    }

    /** {@inheritDoc} */
    public String getReference() {
        return parameters.get(Parameter.reference);
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
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        parameters.put(Parameter.contact, contact);
    }

    /**
     * Set the error message.
     * 
     * @param error the error to set
     */
    public void setError(String error) {
        parameters.put(Parameter.error, error);
    }

    /**
     * Set the error reference.
     * 
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        parameters.put(Parameter.reference, reference);
    }

}