
package com.shibfaced.openid.message.impl;

import com.shibfaced.openid.message.VerifyResponse;

/**
 * VerifyResponseImpl.
 */
public class VerifyResponseImpl extends AbstractMessage implements VerifyResponse {

    /** {@inheritDoc} */
    public String getInvalidateHandle() {
        return parameters.get(Parameter.invalidate_handle);
    }

    /** {@inheritDoc} */
    public boolean isValid() {
        return Boolean.parseBoolean(parameters.get(Parameter.is_valid));
    }

    /** {@inheritDoc} */
    public String getMode() {
        return null;
    }

    /**
     * Set invalidate Handle.
     * 
     * @param handle the invalidateHandle to set
     */
    public void setInvalidateHandle(String handle) {
        parameters.put(Parameter.invalidate_handle, handle);
    }

    /**
     * Set whether the signature is valid.
     * 
     * @param valid whether the signature is valid
     */
    public void setValid(boolean valid) {
        parameters.put(Parameter.is_valid, Boolean.toString(valid));
    }

}