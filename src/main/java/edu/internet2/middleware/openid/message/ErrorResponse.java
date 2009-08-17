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

/**
 * OpenID error response.
 */
public interface ErrorResponse extends Message {

    /**
     * A human-readable message indicating the cause of the error.
     * 
     * @return the error message
     */
    public String getError();

    /**
     * Contact address for the administrator of the sever. The contact address may take any form, as it is intended to
     * be displayed to a person.
     * 
     * @return the contact
     */
    public String getContact();

    /**
     * A reference token, such as a support ticket number or a URL to a news blog, etc.
     * 
     * @return the reference
     */
    public String getReference();

}