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

package edu.internet2.middleware.openid.message.validation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.message.impl.AbstractMessage;

/**
 * Base class for validating OpenID message implementation.
 */
public abstract class AbstractValidatingMessage extends AbstractMessage implements ValidatingMessage {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(AbstractValidatingMessage.class);

    /** Registered message validators. */
    private Set<MessageValidator> validators;

    /** {@inheritDoc} */
    public void deregisterValidator(MessageValidator validator) {
        if (validator != null) {
            validators.remove(validator);
        }
    }

    /** {@inheritDoc} */
    public Set<MessageValidator> getValidators() {
        if (validators == null) {
            return Collections.EMPTY_SET;
        }

        return Collections.unmodifiableSet(validators);
    }

    /** {@inheritDoc} */
    public void registerValidator(MessageValidator validator) {
        if (validator != null) {
            if (validators == null) {
                validators = new HashSet<MessageValidator>();
            }

            validators.add(validator);
        }
    }

    /** {@inheritDoc} */
    public void validate() throws ValidationException {
        for (MessageValidator validator : validators) {
            log.debug("Validating {} message using Validator class {}", this.getClass().getName(), validator.getClass()
                    .getName());
            validator.validate(this);
        }

        // TODO: optionally validate message extensions as well
    }

}