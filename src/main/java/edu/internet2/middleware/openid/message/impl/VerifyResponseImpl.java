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

import edu.internet2.middleware.openid.message.VerifyResponse;

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