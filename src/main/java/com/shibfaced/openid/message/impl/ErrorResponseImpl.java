
package com.shibfaced.openid.message.impl;

import com.shibfaced.openid.message.ErrorResponse;

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