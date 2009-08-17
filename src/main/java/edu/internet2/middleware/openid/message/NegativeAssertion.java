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
 * Response to an authentication request indication the OpenID Provider was unable to authenticate the end user.
 */
public interface NegativeAssertion extends Message {

    /**
     * Message mode for a negative assertion to an immediate authentcation request.
     */
    public static final String MODE_IMMEDIATE = "setup_needed";

    /**
     * Message mode for a negative assertion to a non-immediate authentication request.
     */
    public static final String MODE_INTERACTIVE = "cancel";
}