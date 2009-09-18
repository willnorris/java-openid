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

import javax.xml.namespace.QName;

import org.opensaml.xml.ConfigurationException;

import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.message.AssociationRequest;
import edu.internet2.middleware.openid.message.AuthenticationRequest;
import edu.internet2.middleware.openid.message.Marshaller;
import edu.internet2.middleware.openid.message.MessageBuilder;
import edu.internet2.middleware.openid.message.NegativeAssertion;
import edu.internet2.middleware.openid.message.PositiveAssertion;
import edu.internet2.middleware.openid.message.Unmarshaller;
import edu.internet2.middleware.openid.message.impl.AssociationRequestBuilder;
import edu.internet2.middleware.openid.message.impl.AssociationRequestMarshaller;
import edu.internet2.middleware.openid.message.impl.AssociationRequestUnmarshaller;
import edu.internet2.middleware.openid.message.impl.AuthenticationRequestBuilder;
import edu.internet2.middleware.openid.message.impl.AuthenticationRequestMarshaller;
import edu.internet2.middleware.openid.message.impl.AuthenticationRequestUnmarshaller;
import edu.internet2.middleware.openid.message.impl.NegativeAssertionBuilder;
import edu.internet2.middleware.openid.message.impl.NegativeAssertionMarshaller;
import edu.internet2.middleware.openid.message.impl.NegativeAssertionUnmarshaller;
import edu.internet2.middleware.openid.message.impl.PositiveAssertionBuilder;
import edu.internet2.middleware.openid.message.impl.PositiveAssertionMarshaller;
import edu.internet2.middleware.openid.message.impl.PositiveAssertionUnmarshaller;

/**
 * This class can be used to bootstrap the OpenXRD library with the default configurations that ship with the library.
 */
public class DefaultBootstrap {

    /** Constructor. */
    protected DefaultBootstrap() {
    }

    /**
     * Initializes the OpenSAML library, loading default configurations.
     * 
     * @throws ConfigurationException thrown if there is a problem initializing the OpenSAML library
     */
    public static synchronized void bootstrap() throws ConfigurationException {
        initializeObjectProviders();
    }

    /**
     * Initialize object providers.
     */
    public static void initializeObjectProviders() {
        QName qname;
        MessageBuilder builder;
        Marshaller marshaller;
        Unmarshaller unmarshaller;

        qname = new QName(OpenIDConstants.OPENID_20_NS, AssociationRequest.MODE);
        builder = new AssociationRequestBuilder();
        marshaller = new AssociationRequestMarshaller();
        unmarshaller = new AssociationRequestUnmarshaller();
        initializeObjectProvider(qname, builder, marshaller, unmarshaller);

        qname = new QName(OpenIDConstants.OPENID_20_NS, AuthenticationRequest.MODE_IMMEDIATE);
        builder = new AuthenticationRequestBuilder();
        marshaller = new AuthenticationRequestMarshaller();
        unmarshaller = new AuthenticationRequestUnmarshaller();
        initializeObjectProvider(qname, builder, marshaller, unmarshaller);

        qname = new QName(OpenIDConstants.OPENID_20_NS, AuthenticationRequest.MODE_INTERACTIVE);
        // same providers
        initializeObjectProvider(qname, builder, marshaller, unmarshaller);

        qname = new QName(OpenIDConstants.OPENID_20_NS, PositiveAssertion.MODE);
        builder = new PositiveAssertionBuilder();
        marshaller = new PositiveAssertionMarshaller();
        unmarshaller = new PositiveAssertionUnmarshaller();
        initializeObjectProvider(qname, builder, marshaller, unmarshaller);

        qname = new QName(OpenIDConstants.OPENID_20_NS, NegativeAssertion.MODE_IMMEDIATE);
        builder = new NegativeAssertionBuilder();
        marshaller = new NegativeAssertionMarshaller();
        unmarshaller = new NegativeAssertionUnmarshaller();
        initializeObjectProvider(qname, builder, marshaller, unmarshaller);

        qname = new QName(OpenIDConstants.OPENID_20_NS, NegativeAssertion.MODE_INTERACTIVE);
        // same providers
        initializeObjectProvider(qname, builder, marshaller, unmarshaller);

    }

    public static void initializeObjectProvider(QName qname, MessageBuilder builder, Marshaller marshaller,
            Unmarshaller unmarshaller) {

        Configuration.getBuilders().put(qname, builder);
        Configuration.getMarshallers().put(qname, marshaller);
        Configuration.getUnmarshallers().put(qname, unmarshaller);

    }

}