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

package edu.internet2.middleware.openid.extensions.ax.impl;

import edu.internet2.middleware.openid.extensions.ax.StoreResponse;

/**
 * StoreResponseImpl.
 */
public class StoreResponseImpl implements StoreResponse {

    /**
     * Whether this response indicates success.
     */
    private boolean success;

    /**
     * Error message.
     */
    private String error;

    /** {@inheritDoc} */
    public String getError() {
        return error;
    }

    /**
     * Set error message.
     * 
     * @param newError new error message
     */
    public void setError(String newError) {
        error = newError;
    }

    /**
     * Get whether this response indicates success.
     * 
     * @return if success
     */
    public boolean getSuccess() {
        return success;
    }

    /**
     * Set whether this response indicates success.
     * 
     * @param newSuccess if success
     */
    public void setSuccess(boolean newSuccess) {
        success = newSuccess;
    }

    /** {@inheritDoc} */
    public String getNamespace() {
        if (success) {
            return StoreResponse.MODE_SUCCESS;
        } else {
            return StoreResponse.MODE_FAILURE;
        }
    }

}