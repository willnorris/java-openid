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

import edu.internet2.middleware.openid.common.OpenIDConstants.SessionType;
import edu.internet2.middleware.openid.message.AssociationRequest;
import edu.internet2.middleware.openid.message.validation.ValidationException;

/**
 * Validator for {@link AssociationRequest} messages.
 */
public class AssociationRequestValidator extends AbstractMessageValidator<AssociationRequest> {

    /** {@inheritDoc} */
    public void validate(AssociationRequest request) throws ValidationException {
        super.validate(request);

        if (!AssociationRequest.MODE.equals(request.getMode())) {
            throw new ValidationException("Association Request must have a mode of '" + AssociationRequest.MODE
                    + "' (value was '" + request.getMode() + "')");
        }

        if (request.getAssociationType() == null) {
            throw new ValidationException("Association Request must contain an association type.");
        }

        validateSession(request);
    }

    /**
     * Validate session related parameters.
     * 
     * @param request request to validate
     * @throws ValidationException thrown if session related parameters are not valid
     */
    protected void validateSession(AssociationRequest request) throws ValidationException {
        SessionType sessionType = request.getSessionType();
        if (sessionType == null) {
            throw new ValidationException("Association Request must contain a session type.");
        }

        if (sessionType.equals(SessionType.DH_SHA1) || sessionType.equals(SessionType.DH_SHA256)) {
            if (request.getDHConsumerPublic() == null) {
                throw new ValidationException(
                        "Association Request with DH session type must contain the DH consumer public key.");
            }

            if (request.getDHParameters() == null) {
                throw new ValidationException("Association Request with DH session type must contain "
                        + "DH parameter spec");
            }
        } else if (sessionType.equals(SessionType.no_encryption)) {
            if (request.getDHConsumerPublic() != null) {
                throw new ValidationException("Association Request with no-encryption session type should not "
                        + "contain a DH consumer public key.");
            }

            if (request.getDHParameters() != null) {
                throw new ValidationException(
                        "Association Request with no-encryption session type should not contain a DH parameter spec");
            }
        }
    }

}