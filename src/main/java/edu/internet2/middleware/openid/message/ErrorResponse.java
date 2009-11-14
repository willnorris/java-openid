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

import edu.internet2.middleware.openid.message.validation.ValidatingMessage;

/**
 * OpenID error response.
 */
public interface ErrorResponse extends ValidatingMessage {

    /** Message mode for error messages. */
    public static final String MODE = "error";

    /**
     * A human-readable message indicating the cause of the error.
     * 
     * @return the error message
     */
    public String getError();

    /**
     * Set the human-readable error message.
     * 
     * @param newError the error message
     */
    public void setError(String newError);

    /**
     * Contact address for the administrator of the sever. The contact address may take any form, as it is intended to
     * be displayed to a person.
     * 
     * @return the contact
     */
    public String getContact();

    /**
     * Set the contact address for the administrator of the server.
     * 
     * @param newContact the contact address
     */
    public void setContact(String newContact);

    /**
     * A reference token, such as a support ticket number or a URL to a news blog, etc.
     * 
     * @return the reference
     */
    public String getReference();

    /**
     * Set the reference token for the error.
     * 
     * @param newReference the reference token
     */
    public void setReference(String newReference);

}