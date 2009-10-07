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

package edu.internet2.middleware.openid.extensions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.Configuration;
import edu.internet2.middleware.openid.message.io.UnmarshallingException;

/**
 * Base class for message extension unmarshallers.
 * 
 * @param <MessageExtensionType> type of message this unmarshaller handles
 */
public abstract class AbstractMessageExtensionUnmarshaller<MessageExtensionType extends MessageExtension> implements
        MessageExtensionUnmarshaller<MessageExtensionType> {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(AbstractMessageExtensionUnmarshaller.class);

    /** Message builders. */
    private MessageExtensionBuilderFactory extensionBuilders;

    /** Constructor. */
    public AbstractMessageExtensionUnmarshaller() {
        extensionBuilders = Configuration.getExtensionBuilders();
    }

    /**
     * Build an OpenID message extension object.
     * 
     * @param type class or interface of message extension type to build
     * @return constructed OpenID message
     * @throws UnmarshallingException if unable to build the message
     */
    protected MessageExtensionType buildMessage(Class type) throws UnmarshallingException {
        MessageExtensionBuilder builder = extensionBuilders.getBuilder(type);

        if (builder == null) {
            log.error("Unable to find builder for class: {}", type);
            throw new UnmarshallingException("Unable to find builder for class: " + type.toString());
        }

        return (MessageExtensionType) builder.buildObject();
    }

}