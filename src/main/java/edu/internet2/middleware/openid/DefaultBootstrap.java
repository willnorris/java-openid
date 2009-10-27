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

import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.extensions.MessageExtensionMarshaller;
import edu.internet2.middleware.openid.extensions.MessageExtensionUnmarshaller;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchange;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchangeMarshallingDispatcher;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchangeUnmarshallingDispatcher;
import edu.internet2.middleware.openid.extensions.ax.FetchRequest;
import edu.internet2.middleware.openid.extensions.ax.FetchResponse;
import edu.internet2.middleware.openid.extensions.ax.StoreRequest;
import edu.internet2.middleware.openid.extensions.ax.StoreResponse;
import edu.internet2.middleware.openid.extensions.ax.impl.FetchRequestBuilder;
import edu.internet2.middleware.openid.extensions.ax.impl.FetchResponseBuilder;
import edu.internet2.middleware.openid.extensions.ax.impl.StoreRequestBuilder;
import edu.internet2.middleware.openid.extensions.ax.impl.StoreResponseBuilder;
import edu.internet2.middleware.openid.extensions.pape.PAPE;
import edu.internet2.middleware.openid.extensions.pape.PAPEMessageMarshaller;
import edu.internet2.middleware.openid.extensions.pape.PAPEMessageUnmarshaller;
import edu.internet2.middleware.openid.extensions.pape.PAPERequest;
import edu.internet2.middleware.openid.extensions.pape.PAPEResponse;
import edu.internet2.middleware.openid.extensions.pape.impl.PAPERequestBuilder;
import edu.internet2.middleware.openid.extensions.pape.impl.PAPEResponseBuilder;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistration;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistrationMessageMarshaller;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistrationMessageUnmarshaller;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistrationRequest;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistrationResponse;
import edu.internet2.middleware.openid.extensions.sreg.impl.SimpleRegistrationRequestBuilder;
import edu.internet2.middleware.openid.extensions.sreg.impl.SimpleRegistrationResponseBuilder;
import edu.internet2.middleware.openid.message.AssociationRequest;
import edu.internet2.middleware.openid.message.AuthenticationRequest;
import edu.internet2.middleware.openid.message.ErrorResponse;
import edu.internet2.middleware.openid.message.MessageBuilder;
import edu.internet2.middleware.openid.message.NegativeAssertion;
import edu.internet2.middleware.openid.message.PositiveAssertion;
import edu.internet2.middleware.openid.message.VerifyRequest;
import edu.internet2.middleware.openid.message.impl.AssociationErrorBuilder;
import edu.internet2.middleware.openid.message.impl.AssociationErrorMarshaller;
import edu.internet2.middleware.openid.message.impl.AssociationErrorUnmarshaller;
import edu.internet2.middleware.openid.message.impl.AssociationRequestBuilder;
import edu.internet2.middleware.openid.message.impl.AssociationRequestMarshaller;
import edu.internet2.middleware.openid.message.impl.AssociationRequestUnmarshaller;
import edu.internet2.middleware.openid.message.impl.AssociationResponseBuilder;
import edu.internet2.middleware.openid.message.impl.AssociationResponseMarshaller;
import edu.internet2.middleware.openid.message.impl.AssociationResponseUnmarshaller;
import edu.internet2.middleware.openid.message.impl.AuthenticationRequestBuilder;
import edu.internet2.middleware.openid.message.impl.AuthenticationRequestMarshaller;
import edu.internet2.middleware.openid.message.impl.AuthenticationRequestUnmarshaller;
import edu.internet2.middleware.openid.message.impl.ErrorResponseBuilder;
import edu.internet2.middleware.openid.message.impl.ErrorResponseMarshaller;
import edu.internet2.middleware.openid.message.impl.ErrorResponseUnmarshaller;
import edu.internet2.middleware.openid.message.impl.NegativeAssertionBuilder;
import edu.internet2.middleware.openid.message.impl.NegativeAssertionMarshaller;
import edu.internet2.middleware.openid.message.impl.NegativeAssertionUnmarshaller;
import edu.internet2.middleware.openid.message.impl.PositiveAssertionBuilder;
import edu.internet2.middleware.openid.message.impl.PositiveAssertionMarshaller;
import edu.internet2.middleware.openid.message.impl.PositiveAssertionUnmarshaller;
import edu.internet2.middleware.openid.message.impl.VerifyRequestBuilder;
import edu.internet2.middleware.openid.message.impl.VerifyRequestMarshaller;
import edu.internet2.middleware.openid.message.impl.VerifyRequestUnmarshaller;
import edu.internet2.middleware.openid.message.impl.VerifyResponseBuilder;
import edu.internet2.middleware.openid.message.impl.VerifyResponseMarshaller;
import edu.internet2.middleware.openid.message.impl.VerifyResponseUnmarshaller;
import edu.internet2.middleware.openid.message.io.MessageMarshaller;
import edu.internet2.middleware.openid.message.io.MessageUnmarshaller;

/**
 * This class can be used to bootstrap the OpenXRD library with the default configurations that ship with the library.
 */
public class DefaultBootstrap {

    /** Constructor. */
    protected DefaultBootstrap() {
    }

    /**
     * Initializes the OpenSAML library, loading default configurations.
     */
    public static synchronized void bootstrap() {
        initializeObjectProviders();
        initializeExtensionProviders();
    }

    /**
     * Initialize object providers.
     */
    public static void initializeObjectProviders() {
        QName qname;
        MessageBuilder builder;
        MessageMarshaller marshaller;
        MessageUnmarshaller unmarshaller;

        qname = new QName(OpenIDConstants.OPENID_20_NS, AssociationRequest.MODE);
        builder = new AssociationRequestBuilder();
        marshaller = new AssociationRequestMarshaller();
        unmarshaller = new AssociationRequestUnmarshaller();
        initializeObjectProvider(qname, builder, marshaller, unmarshaller);

        qname = new QName(OpenIDConstants.OPENID_20_NS, OpenIDConstants.ASSOCIATION_RESPONSE_MODE);
        builder = new AssociationResponseBuilder();
        marshaller = new AssociationResponseMarshaller();
        unmarshaller = new AssociationResponseUnmarshaller();
        initializeObjectProvider(qname, builder, marshaller, unmarshaller);

        qname = new QName(OpenIDConstants.OPENID_20_NS, OpenIDConstants.ASSOCIATION_ERROR_MODE);
        builder = new AssociationErrorBuilder();
        marshaller = new AssociationErrorMarshaller();
        unmarshaller = new AssociationErrorUnmarshaller();
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

        qname = new QName(OpenIDConstants.OPENID_20_NS, VerifyRequest.MODE);
        builder = new VerifyRequestBuilder();
        marshaller = new VerifyRequestMarshaller();
        unmarshaller = new VerifyRequestUnmarshaller();
        initializeObjectProvider(qname, builder, marshaller, unmarshaller);

        qname = new QName(OpenIDConstants.OPENID_20_NS, OpenIDConstants.VERIFICATION_RESPONSE_MODE);
        builder = new VerifyResponseBuilder();
        marshaller = new VerifyResponseMarshaller();
        unmarshaller = new VerifyResponseUnmarshaller();
        initializeObjectProvider(qname, builder, marshaller, unmarshaller);

        qname = new QName(OpenIDConstants.OPENID_20_NS, ErrorResponse.MODE);
        builder = new ErrorResponseBuilder();
        marshaller = new ErrorResponseMarshaller();
        unmarshaller = new ErrorResponseUnmarshaller();
        initializeObjectProvider(qname, builder, marshaller, unmarshaller);
    }

    public static void initializeObjectProvider(QName qname, MessageBuilder builder, MessageMarshaller marshaller,
            MessageUnmarshaller unmarshaller) {

        Configuration.getMessageBuilders().registerBuilder(qname, builder);
        Configuration.getMessageMarshallers().registerMarshaller(qname, marshaller);
        Configuration.getMessageUnmarshallers().registerUnmarshaller(qname, unmarshaller);

    }

    /** Initialize extension object providers. */
    public static void initializeExtensionProviders() {
        MessageExtensionMarshaller marshaller;
        MessageExtensionUnmarshaller unmarshaller;

        // Simple Registration
        marshaller = new SimpleRegistrationMessageMarshaller();
        unmarshaller = new SimpleRegistrationMessageUnmarshaller();
        // initializeExtensionProvider(SimpleRegistration.SREG_10_NS, marshaller, unmarshaller);
        initializeExtensionProvider(SimpleRegistration.SREG_11_NS, marshaller, unmarshaller);

        Configuration.getExtensionBuilders().registerBuilder(SimpleRegistrationRequest.class,
                new SimpleRegistrationRequestBuilder());
        Configuration.getExtensionBuilders().registerBuilder(SimpleRegistrationResponse.class,
                new SimpleRegistrationResponseBuilder());

        // Attribute Exchange
        marshaller = new AttributeExchangeMarshallingDispatcher();
        unmarshaller = new AttributeExchangeUnmarshallingDispatcher();
        initializeExtensionProvider(AttributeExchange.AX_10_NS, marshaller, unmarshaller);

        Configuration.getExtensionBuilders().registerBuilder(FetchRequest.class, new FetchRequestBuilder());
        Configuration.getExtensionBuilders().registerBuilder(FetchResponse.class, new FetchResponseBuilder());
        Configuration.getExtensionBuilders().registerBuilder(StoreRequest.class, new StoreRequestBuilder());
        Configuration.getExtensionBuilders().registerBuilder(StoreResponse.class, new StoreResponseBuilder());

        // PAPE
        marshaller = new PAPEMessageMarshaller();
        unmarshaller = new PAPEMessageUnmarshaller();
        initializeExtensionProvider(PAPE.PAPE_10_NS, marshaller, unmarshaller);

        Configuration.getExtensionBuilders().registerBuilder(PAPERequest.class, new PAPERequestBuilder());
        Configuration.getExtensionBuilders().registerBuilder(PAPEResponse.class, new PAPEResponseBuilder());
    }

    public static void initializeExtensionProvider(String namespace, MessageExtensionMarshaller marshaller,
            MessageExtensionUnmarshaller unmarshaller) {

        Configuration.getExtensionMarshallers().registerMarshaller(namespace, marshaller);
        Configuration.getExtensionUnmarshallers().registerUnmarshaller(namespace, unmarshaller);
    }
}