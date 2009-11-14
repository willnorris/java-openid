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

import edu.internet2.middleware.openid.message.NegativeAssertion;
import edu.internet2.middleware.openid.message.validation.ValidationException;

/**
 * Validator for {@link NegativeAssertion} messages.
 */
public class NegativeAssertionValidator extends AbstractMessageValidator<NegativeAssertion> {

    /** {@inheritDoc} */
    public void validate(NegativeAssertion response) throws ValidationException {
        super.validate(response);

        if (!NegativeAssertion.MODE_IMMEDIATE.equals(response.getMode())
                && !NegativeAssertion.MODE_INTERACTIVE.equals(response.getMode())) {
            throw new ValidationException("Negative Assertion must have a mode value of '"
                    + NegativeAssertion.MODE_IMMEDIATE + "' or '" + NegativeAssertion.MODE_INTERACTIVE
                    + "'.  (value was '" + response.getMode() + "')");
        }
    }

}