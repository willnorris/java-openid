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

import edu.internet2.middleware.openid.message.Message;

/**
 * MessageValidators check the validity of OpenID Messages.
 * 
 * @param <MessageType> type of Message that will be validated
 */
public interface MessageValidator<MessageType extends Message> {

    /**
     * Checks to see if a Message is valid.
     * 
     * @param message the Message to validate
     * 
     * @throws ValidationException thrown if the message is not valid
     */
    public void validate(MessageType message) throws ValidationException;

}