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

import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;
import edu.internet2.middleware.openid.common.OpenIDConstants.SessionType;
import edu.internet2.middleware.openid.message.validation.ValidatingMessage;

/**
 * Response to an {@link AssociationRequest} indicating that the OpenID Provider does not support the requested session
 * type or association type.
 */
public interface AssociationError extends ValidatingMessage {

    /**
     * Unsuccessful response error code.
     */
    public static final String ERROR_CODE = "unsupported-type";

    /**
     * A human-readable message indicating the cause of the error.
     * 
     * @return the error message
     */
    public String getError();

    /**
     * Set the error message.
     * 
     * @param newError the error message
     */
    public void setError(String newError);

    /**
     * Error code. Value must be "unsupported-type".
     * 
     * @return the error code
     */
    public String getErrorCode();

    /**
     * A valid association session type that the OpenID Provider supports.
     * 
     * @return the session type
     */
    public SessionType getSessionType();

    /**
     * Set a valid session type that the OpenID Provider supports.
     * 
     * @param newSessionType supported session type
     */
    public void setSessionType(SessionType newSessionType);

    /**
     * A valid association type that the OpenID Provider supports.
     * 
     * @return the association type
     */
    public AssociationType getAssociationType();

    /**
     * Set a valid association type that the OpenID Provider supports.
     * 
     * @param newAssociationType supported association type
     */
    public void setAssociationType(AssociationType newAssociationType);

}