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
import edu.internet2.middleware.openid.message.AssociationResponse;
import edu.internet2.middleware.openid.message.validation.ValidationException;
import edu.internet2.middleware.openid.message.validation.ValidationUtils;

/**
 * Validator for {@link AssociationResponse} messages.
 */
public class AssociationResponseValidator extends AbstractMessageValidator<AssociationResponse> {

    /** {@inheritDoc} */
    public void validate(AssociationResponse response) throws ValidationException {
        super.validate(response);

        if (response.getAssociationHandle() == null) {
            throw new ValidationException("Association Response must contain an association handle.");
        }

        ValidationUtils.validateAssociationHandle(response.getAssociationHandle());

        if (response.getAssociationType() == null) {
            throw new ValidationException("Association Response must contain an association type.");
        }

        validateSession(response);

        if (response.getLifetime() == null) {
            throw new ValidationException("Association Response must define the association lifetime");
        }

        if (response.getMacKey() == null) {
            throw new ValidationException("Association Response must contain a MAC key");
        }

        ValidationUtils.validateMacKeyLength(response.getAssociationType(), response.getMacKey());
    }

    /**
     * Validate session related parameters.
     * 
     * @param response response to validate
     * @throws ValidationException thrown if session related parameters are not valid
     */
    protected void validateSession(AssociationResponse response) throws ValidationException {
        SessionType sessionType = response.getSessionType();
        if (sessionType == null) {
            throw new ValidationException("Association Response must contain a session type.");
        }

        if (sessionType.equals(SessionType.DH_SHA1) || sessionType.equals(SessionType.DH_SHA256)) {
            if (response.getDHServerPublic() == null) {
                throw new ValidationException(
                        "Association Response with DH session type must contain the DH server public key.");
            }
        } else if (sessionType.equals(SessionType.no_encryption)) {
            if (response.getDHServerPublic() != null) {
                throw new ValidationException("Association Response with no-encryption session type should not "
                        + "contain a DH server public key.");
            }
        }

    }

}