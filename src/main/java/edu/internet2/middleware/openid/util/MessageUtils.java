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

package edu.internet2.middleware.openid.util;

import javax.xml.namespace.QName;

import edu.internet2.middleware.openid.Configuration;
import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.message.Message;

/**
 * Utilities for working with OpenID {@link Message} objects.
 */
public class MessageUtils {

    /**
     * Build an OpenID 2.0 message with the given mode value.
     * 
     * @param mode mode of message to build
     * @return built message
     */
    public static Message buildMessage(String mode) {
        QName qname = new QName(OpenIDConstants.OPENID_20_NS, mode);
        return buildMessage(qname);
    }

    /**
     * Build an OpenID message with the given mode.
     * 
     * @param mode mode of message to build
     * @return built message
     */
    public static Message buildMessage(QName mode) {
        return Configuration.getMessageBuilders().getBuilder(mode).buildObject();
    }

}