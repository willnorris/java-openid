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

package edu.internet2.middleware.openid;

import java.io.InputStream;
import java.util.Scanner;

import javax.xml.namespace.QName;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.common.OpenIDConstants.Parameter;
import edu.internet2.middleware.openid.message.Marshaller;
import edu.internet2.middleware.openid.message.MarshallingException;
import edu.internet2.middleware.openid.message.Message;
import edu.internet2.middleware.openid.message.MessageBuilder;
import edu.internet2.middleware.openid.message.ParameterMap;
import edu.internet2.middleware.openid.message.Unmarshaller;
import edu.internet2.middleware.openid.message.UnmarshallingException;
import edu.internet2.middleware.openid.message.encoding.EncodingException;
import edu.internet2.middleware.openid.message.encoding.impl.KeyValueFormCodec;

/**
 * Intermediate class that serves to initialize the configuration environment for other base test classes.
 */
public abstract class BaseTestCase extends TestCase {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(BaseTestCase.class);

    /** {@inheritDoc} */
    protected void setUp() throws Exception {
        super.setUp();

        DefaultBootstrap.bootstrap();
    }

    /**
     * Asserts a given Message is equal to an expected Parameter map. The Message is marshalled and the resulting map is
     * compared against the expected map.
     * 
     * @param expectedParameters the expected parameter map
     * @param message the message to be marshalled and compared against the expected map
     */
    public void assertEquals(ParameterMap expectedParameters, Message message) {
        assertEquals("Marshalled parameters were not the same as the expected parameters", expectedParameters, message);
    }

    /**
     * Asserts a given Message is equal to an expected Parameter map. The Message is marshalled and the resulting map is
     * compared against the expected map.
     * 
     * @param failMessage the message to display if the maps are not equal
     * @param expectedParameters the expected parameter map
     * @param message the message to be marshalled and compared against the expected map
     */
    public void assertEquals(String failMessage, ParameterMap expectedParameters, Message message) {
        QName qname = new QName(OpenIDConstants.OPENID_20_NS, expectedParameters.get(Parameter.mode.QNAME));
        Marshaller marshaller = Configuration.getMarshallers().get(qname);
        if (marshaller == null) {
            fail("Unable to find message marshaller for mode: " + message.getMode());
        }

        try {
            ParameterMap generatedParameters = marshaller.marshall(message);
            assertTrue(failMessage, expectedParameters.equals(generatedParameters));
        } catch (MarshallingException e) {
            fail("Unable to marshall message");
        }
    }

    /**
     * Unmarshalls a message file into its OpenID message. Message file must be Key-Value form encoded.
     * 
     * @param messageFile the classpath path to a message file to unmarshall
     * @return the OpenID message
     */
    protected Message unmarshallMessage(String messageFile) {
        ParameterMap parameters = parseMessageFile(messageFile);
        return unmarshallMessage(parameters, parameters.get(Parameter.mode.QNAME));
    }

    /**
     * Unmarshalls a parameter map into an OpenID message.
     * 
     * @param parameters OpenID message parameters
     * @return the OpenID message
     */
    protected Message unmarshallMessage(ParameterMap parameters, String mode) {
        QName qname = new QName(OpenIDConstants.OPENID_20_NS, mode);
        Unmarshaller unmarshaller = Configuration.getUnmarshallers().get(qname);

        if (unmarshaller == null) {
            fail("Unable to find message unmarshaller for mode: " + mode);
        }

        try {
            return unmarshaller.unmarshall(parameters);
        } catch (UnmarshallingException e) {
            fail("Unable to unmarshall message");
        }

        return null;
    }

    /**
     * Parse a Key-Value form encoded message file.
     * 
     * @param messageFile the classpath path to a message file to parse
     * @return Map of parameters in message
     */
    protected ParameterMap parseMessageFile(String messageFile) {
        log.debug("Parsing message file: {}", messageFile);

        InputStream input = BaseTestCase.class.getResourceAsStream(messageFile);
        if (input == null) {
            fail("Unable to open message file: " + messageFile);
        }

        Scanner scanner = new Scanner(input).useDelimiter("\\Z");
        String message = scanner.next();
        scanner.close();

        try {
            return KeyValueFormCodec.getInstance().decode(message);
        } catch (EncodingException e) {
            fail("Unable to decode message file: " + messageFile);
        }

        return null;
    }

    public Message buildMessage(QName qname) {
        MessageBuilder<Message> builder = Configuration.getBuilders().get(qname);

        if (builder == null) {
            fail("Unable to retrieve builder for message with QName: " + qname);
        }

        return builder.buildObject();
    }

    public QName buildQName(String name) {
        return new QName(OpenIDConstants.OPENID_20_NS, name);
    }
}