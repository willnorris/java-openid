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

import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.message.Message;
import edu.internet2.middleware.openid.message.validation.MessageValidator;
import edu.internet2.middleware.openid.message.validation.ValidationException;

/**
 * Message validator base class.
 * 
 * @param <MessageType> type of Message that will be validated
 */
public abstract class AbstractMessageValidator<MessageType extends Message> implements MessageValidator<MessageType> {

    /** {@inheritDoc} */
    public void validate(MessageType message) throws ValidationException {
        // this library only supports OpenID 2.0 messages
        if (!OpenIDConstants.OPENID_20_NS.equals(message.getNamespace())) {
            throw new ValidationException("Message must have a namespace of '" + OpenIDConstants.OPENID_20_NS + "'");
        }
    }

}