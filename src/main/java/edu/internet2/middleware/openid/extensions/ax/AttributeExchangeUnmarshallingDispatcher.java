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

package edu.internet2.middleware.openid.extensions.ax;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.Configuration;
import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.extensions.MessageExtensionBuilder;
import edu.internet2.middleware.openid.extensions.MessageExtensionBuilderFactory;
import edu.internet2.middleware.openid.extensions.MessageExtensionUnmarshaller;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchange.Parameter;
import edu.internet2.middleware.openid.extensions.ax.impl.FetchRequestUnmarshaller;
import edu.internet2.middleware.openid.extensions.ax.impl.FetchResponseUnmarshaller;
import edu.internet2.middleware.openid.extensions.ax.impl.StoreRequestUnmarshaller;
import edu.internet2.middleware.openid.extensions.ax.impl.StoreResponseUnmarshaller;
import edu.internet2.middleware.openid.message.io.UnmarshallingException;

/**
 * Marshaller for {@link AttributeExchangeMessage}.
 */
public class AttributeExchangeUnmarshallingDispatcher implements MessageExtensionUnmarshaller<AttributeExchangeMessage> {

    /** Logger. */
    private Logger log = LoggerFactory.getLogger(AttributeExchangeUnmarshallingDispatcher.class);

    /** Attribute Exchange marshallers. */
    private Map<Class, AttributeExchangeUnmarshaller> unmarshallers;

    /** Message Extension builders. */
    private MessageExtensionBuilderFactory builders;

    /** Constructor. */
    public AttributeExchangeUnmarshallingDispatcher() {
        unmarshallers = new HashMap<Class, AttributeExchangeUnmarshaller>();

        unmarshallers.put(FetchRequest.class, new FetchRequestUnmarshaller());
        unmarshallers.put(FetchResponse.class, new FetchResponseUnmarshaller());
        unmarshallers.put(StoreRequest.class, new StoreRequestUnmarshaller());
        unmarshallers.put(StoreResponse.class, new StoreResponseUnmarshaller());

        builders = Configuration.getExtensionBuilders();
    }

    /** {@inheritDoc} */
    public AttributeExchangeMessage unmarshall(ParameterMap parameters) throws UnmarshallingException {

        String mode = parameters.get(Parameter.mode.QNAME);
        if (mode == null) {
            log.error("Unable to unmarshall Attribute Exchange message: no mode set");
            throw new UnmarshallingException("Unable to unmarshall Attribute Exchange message: no mode set");
        }

        MessageExtensionBuilder builder = null;
        AttributeExchangeUnmarshaller unmarshaller = null;

        if (mode.equals(FetchRequest.MODE)) {
            builder = builders.getBuilder(FetchRequest.class);
            unmarshaller = unmarshallers.get(FetchRequest.class);
        } else if (mode.equals(FetchResponse.MODE)) {
            builder = builders.getBuilder(FetchResponse.class);
            unmarshaller = unmarshallers.get(FetchResponse.class);
        } else if (mode.equals(StoreRequest.MODE)) {
            builder = builders.getBuilder(StoreRequest.class);
            unmarshaller = unmarshallers.get(StoreRequest.class);
        } else if (mode.equals(StoreResponse.MODE_SUCCESS) || mode.equals(StoreResponse.MODE_FAILURE)) {
            builder = builders.getBuilder(StoreResponse.class);
            unmarshaller = unmarshallers.get(FetchRequest.class);
        }

        if (builder == null) {
            log.error("Unable to find Attribute Exchange builder for mode: {}", mode);
            throw new UnmarshallingException("Unable to find Attribute Exchange builder for mode: " + mode);
        }

        if (unmarshaller == null) {
            log.error("Unable to find Attribute Exchange unmarshaller for mode: {}", mode);
            throw new UnmarshallingException("Unable to find Attribute Exchange unmarshaller for mode: " + mode);
        }

        AttributeExchangeMessage message = (AttributeExchangeMessage) builder.buildObject();
        unmarshaller.unmarshall(message, parameters);
        return message;
    }

}