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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.xml.namespace.QName;

import edu.internet2.middleware.openid.common.OpenIDConstants.Parameter;
import edu.internet2.middleware.openid.message.VerifyRequest;
import edu.internet2.middleware.openid.message.validation.ValidationException;
import edu.internet2.middleware.openid.message.validation.ValidationUtils;
import edu.internet2.middleware.openid.util.DatatypeHelper;

/**
 * Validator for {@link VerifyRequest} messages.
 */
public class VerifyRequestValidator extends AbstractMessageValidator<VerifyRequest> {

    /** {@inheritDoc} */
    public void validate(VerifyRequest request) throws ValidationException {
        super.validate(request);

        if (!VerifyRequest.MODE.equals(request.getMode())) {
            throw new ValidationException("Verification Request must have a mode value of '" + VerifyRequest.MODE
                    + "' (value was '" + request.getMode() + "'");
        }

        if (DatatypeHelper.isEmpty(request.getEndpoint())) {
            throw new ValidationException("Verification Request must define the OP endpoint");
        }

        if (DatatypeHelper.isEmpty(request.getClaimedId()) ^ DatatypeHelper.isEmpty(request.getIdentity())) {
            throw new ValidationException("Verification Request must have the claimed_id and identity "
                    + "parameters either both present, or both absent.");
        }

        if (DatatypeHelper.isEmpty(request.getReturnTo())) {
            throw new ValidationException("Verification Request must define return-to URL");
        }

        if (DatatypeHelper.isEmpty(request.getResponseNonce())) {
            throw new ValidationException("Verification Request must define a nonce");
        }

        ValidationUtils.validateNonce(request.getResponseNonce());

        if (request.getAssociationHandle() == null) {
            throw new ValidationException("Verification Request must define an association handle");
        }

        ValidationUtils.validateAssociationHandle(request.getAssociationHandle());

        validateSignature(request);
    }

    /**
     * Validate the signature related parameters. The signed fields and signature parameters must be present, and the
     * signed fields list must contain the requisite parameters.
     * 
     * @param request request to validate
     * @throws ValidationException if the signature related parameters are not valid
     */
    public void validateSignature(VerifyRequest request) throws ValidationException {
        Set<QName> requiredSignedFields = new HashSet();
        requiredSignedFields.addAll(Arrays.asList(ValidationUtils.REQUIRED_SIGNED_FIELDS));

        if (!DatatypeHelper.isEmpty(request.getClaimedId())) {
            requiredSignedFields.add(Parameter.claimed_id.QNAME);
        }

        if (!DatatypeHelper.isEmpty(request.getIdentity())) {
            requiredSignedFields.add(Parameter.identity.QNAME);
        }

        ValidationUtils.validateSignature(request, requiredSignedFields);
    }

}