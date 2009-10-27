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

import edu.internet2.middleware.openid.BaseMessageProviderTestCase;
import edu.internet2.middleware.openid.Configuration;
import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.extensions.MessageExtension;
import edu.internet2.middleware.openid.extensions.MessageExtensionBuilder;
import edu.internet2.middleware.openid.extensions.MessageExtensionBuilderFactory;
import edu.internet2.middleware.openid.extensions.MessageExtensionMarshaller;
import edu.internet2.middleware.openid.extensions.MessageExtensionMarshallerFactory;
import edu.internet2.middleware.openid.extensions.MessageExtensionUnmarshaller;
import edu.internet2.middleware.openid.extensions.MessageExtensionUnmarshallerFactory;
import edu.internet2.middleware.openid.message.io.MarshallingException;
import edu.internet2.middleware.openid.message.io.UnmarshallingException;

/**
 * Base class for message extension tests.
 */
public abstract class BaseMessageExtensionTestCase extends BaseMessageProviderTestCase {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(BaseMessageExtensionTestCase.class);

    /** Message Extension Builder Factory. */
    protected MessageExtensionBuilderFactory extensionBuilders;

    /** Message Extension Marshaller Factory. */
    protected MessageExtensionMarshallerFactory extensionMarshallers;

    /** Message Extension Marshaller Factory. */
    protected MessageExtensionUnmarshallerFactory extensionUnmarshallers;

    /** Constructor. */
    public BaseMessageExtensionTestCase() {
        super();

        extensionBuilders = Configuration.getExtensionBuilders();
        extensionMarshallers = Configuration.getExtensionMarshallers();
        extensionUnmarshallers = Configuration.getExtensionUnmarshallers();
    }

    /**
     * Asserts a given MessageExtension is equal to an expected Parameter map. The MessageExtension is marshalled and
     * the resulting map is compared against the expected map.
     * 
     * @param expectedParameters the expected parameter map
     * @param message the message extension to be marshalled and compared against the expected map
     */
    public void assertEquals(ParameterMap expectedParameters, MessageExtension message) {
        assertEquals("Marshalled parameters were not the same as the expected parameters", expectedParameters, message);
    }

    /**
     * Asserts a given MessageExtension is equal to an expected Parameter map. The MessageExtension is marshalled and
     * the resulting map is compared against the expected map.
     * 
     * @param failMessage the message to display if the maps are not equal
     * @param expectedParameters the expected parameter map
     * @param message the message extension to be marshalled and compared against the expected map
     */
    public void assertEquals(String failMessage, ParameterMap expectedParameters, MessageExtension message) {

        MessageExtensionMarshaller marshaller = extensionMarshallers.getMarshaller(message.getNamespace());
        if (marshaller == null) {
            fail("Unable to find message marshaller for message extension: " + message.getNamespace());
        }

        try {
            ParameterMap generatedParameters = marshaller.marshall(message);
            assertTrue(failMessage, expectedParameters.equals(generatedParameters));
        } catch (MarshallingException e) {
            fail("Unable to marshall message extension");
        }
    }

    /**
     * Build new message extension.
     * 
     * @param extensionClass class of message extension to build
     * @return newly built message extension
     */
    public MessageExtension buildMessageExtension(Class extensionClass) {
        MessageExtensionBuilder builder = extensionBuilders.getBuilder(extensionClass);

        if (builder == null) {
            fail("Unable to retrieve builder for message extension with class: " + extensionClass);
        }

        return builder.buildObject();
    }

    /**
     * Unmarshalls a message file into its OpenID message extension. Message file must be Key-Value form encoded.
     * 
     * @param messageFile the classpath path to a message file to unmarshall
     * @return the OpenID message
     */
    protected MessageExtension unmarshallMessageExtension(String messageFile) {
        return unmarshallMessageExtension(parseMessageFile(messageFile));
    }

    /**
     * Unmarshalls a parameter map into an OpenID message.
     * 
     * @param parameters OpenID message parameters
     * @return the OpenID message
     */
    protected MessageExtension unmarshallMessageExtension(ParameterMap parameters) {
        String[] namespaces = parameters.getNamespaces().getURIs().toArray(new String[] {});
        if (namespaces == null || namespaces.length != 1) {
            fail("Unable to unmarshall message extension: expecting one namespace, but found " + namespaces.length);
        }

        MessageExtensionUnmarshaller unmarshaller;
        try {
            unmarshaller = extensionUnmarshallers.getUnmarshaller(namespaces[0]);
            return unmarshaller.unmarshall(parameters);
        } catch (UnmarshallingException e) {
            fail("Unable to unmarshall message extension: " + e.getMessage());
        }

        return null;
    }

    protected void logMessageParameters(MessageExtension message) {
        try {
            MessageExtensionMarshaller marshaller = extensionMarshallers.getMarshaller(message.getNamespace());
            logMessageParameters(marshaller.marshall(message));
        } catch (MarshallingException e) {
            log.error("unable to marshall message: {}", e.getMessage());
        }
    }
}