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

import edu.internet2.middleware.openid.common.OpenIDException;

/**
 * Security related exception.
 */
public class SecurityException extends OpenIDException {

    /** Serial Version UID. */
    private static final long serialVersionUID = -8936481031752537254L;

    /** Constructor. */
    public SecurityException() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param message exception message
     */
    public SecurityException(String message) {
        super(message);
    }

    /**
     * Constructor.
     * 
     * @param cause exception to be wrapped by this one
     */
    public SecurityException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor.
     * 
     * @param message exception messaage
     * @param cause exception to be wrapped by this one
     */
    public SecurityException(String message, Throwable cause) {
        super(message, cause);
    }

}