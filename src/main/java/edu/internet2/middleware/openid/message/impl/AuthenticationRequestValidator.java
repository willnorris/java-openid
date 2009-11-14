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

package edu.internet2.middleware.openid.message.impl;

import edu.internet2.middleware.openid.message.AuthenticationRequest;
import edu.internet2.middleware.openid.message.validation.ValidationException;

/**
 * Validator for {@link AuthenticationRequest} messages.
 */
public class AuthenticationRequestValidator extends AbstractMessageValidator<AuthenticationRequest> {

    /** {@inheritDoc} */
    public void validate(AuthenticationRequest request) throws ValidationException {
        super.validate(request);

        if (!AuthenticationRequest.MODE_IMMEDIATE.equals(request.getMode())
                && !AuthenticationRequest.MODE_INTERACTIVE.equals(request.getMode())) {
            throw new ValidationException("Authentication Request must have a mode value of '"
                    + AuthenticationRequest.MODE_IMMEDIATE + "' or '" + AuthenticationRequest.MODE_INTERACTIVE
                    + "'.  (value was '" + request.getMode() + "')");

        }

        if (request.getClaimedId() == null ^ request.getIdentity() == null) {
            throw new ValidationException("Authentication Request must have the claimed_id and identity "
                    + "parameters either both present, or both absent.");
        }

        if (request.getReturnTo() == null && request.getRealm() == null) {
            throw new ValidationException("Authentication Request must define a return_to or realm "
                    + "parameter (or both)");
        }

        // TODO: should return_to and realm be checked for valid URLs ?
    }

}