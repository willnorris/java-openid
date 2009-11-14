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
import edu.internet2.middleware.openid.message.PositiveAssertion;
import edu.internet2.middleware.openid.message.validation.ValidationException;
import edu.internet2.middleware.openid.message.validation.ValidationUtils;
import edu.internet2.middleware.openid.util.DatatypeHelper;

/**
 * Validator for {@link PositiveAssertion} messages.
 */
public class PositiveAssertionValidator extends AbstractMessageValidator<PositiveAssertion> {

    /** {@inheritDoc} */
    public void validate(PositiveAssertion response) throws ValidationException {
        super.validate(response);

        if (!PositiveAssertion.MODE.equals(response.getMode())) {
            throw new ValidationException("Positive Assertion must have a mode value of '" + PositiveAssertion.MODE
                    + "' (value was '" + response.getMode() + "'");
        }

        if (DatatypeHelper.isEmpty(response.getEndpoint())) {
            throw new ValidationException("Positive Assertion must define the OP endpoint");
        }

        if (DatatypeHelper.isEmpty(response.getClaimedId()) ^ DatatypeHelper.isEmpty(response.getIdentity())) {
            throw new ValidationException("Positive Assertion must have the claimed_id and identity "
                    + "parameters either both present, or both absent.");
        }

        if (DatatypeHelper.isEmpty(response.getReturnTo())) {
            throw new ValidationException("Positive Assertion must define return-to URL");
        }

        if (DatatypeHelper.isEmpty(response.getResponseNonce())) {
            throw new ValidationException("Positive Assertion must define a nonce");
        }

        ValidationUtils.validateNonce(response.getResponseNonce());

        if (response.getAssociationHandle() == null) {
            throw new ValidationException("Positive Assertion must define an association handle");
        }

        ValidationUtils.validateAssociationHandle(response.getAssociationHandle());

        validateSignature(response);
    }

    /**
     * Validate the signature related parameters. The signed fields and signature parameters must be present, and the
     * signed fields list must contain the requisite parameters.
     * 
     * @param response response to validate
     * @throws ValidationException if the signature related parameters are not valid
     */
    public void validateSignature(PositiveAssertion response) throws ValidationException {
        Set<QName> requiredSignedFields = new HashSet();
        requiredSignedFields.addAll(Arrays.asList(ValidationUtils.REQUIRED_SIGNED_FIELDS));

        if (!DatatypeHelper.isEmpty(response.getClaimedId())) {
            requiredSignedFields.add(Parameter.claimed_id.QNAME);
        }

        if (!DatatypeHelper.isEmpty(response.getIdentity())) {
            requiredSignedFields.add(Parameter.identity.QNAME);
        }

        ValidationUtils.validateSignature(response, requiredSignedFields);
    }

}