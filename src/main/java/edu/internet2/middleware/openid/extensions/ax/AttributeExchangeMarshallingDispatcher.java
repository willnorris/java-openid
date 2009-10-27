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

import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.extensions.MessageExtensionMarshaller;
import edu.internet2.middleware.openid.extensions.ax.AttributeExchange.Parameter;
import edu.internet2.middleware.openid.extensions.ax.impl.FetchRequestMarshaller;
import edu.internet2.middleware.openid.extensions.ax.impl.FetchResponseMarshaller;
import edu.internet2.middleware.openid.extensions.ax.impl.StoreRequestMarshaller;
import edu.internet2.middleware.openid.extensions.ax.impl.StoreResponseMarshaller;
import edu.internet2.middleware.openid.message.io.MarshallingException;

/**
 * Marshaller for {@link AttributeExchangeMessage}.
 */
public class AttributeExchangeMarshallingDispatcher implements MessageExtensionMarshaller<AttributeExchangeMessage> {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(AttributeExchangeMarshallingDispatcher.class);

    /** Attribute Exchange marshallers. */
    private Map<String, AttributeExchangeMarshaller> marshallers;

    /** Constructor. */
    public AttributeExchangeMarshallingDispatcher() {
        marshallers = new HashMap<String, AttributeExchangeMarshaller>();

        marshallers.put(FetchRequest.MODE, new FetchRequestMarshaller());
        marshallers.put(FetchResponse.MODE, new FetchResponseMarshaller());
        marshallers.put(StoreRequest.MODE, new StoreRequestMarshaller());

        StoreResponseMarshaller storeResponseMarshaller = new StoreResponseMarshaller();
        marshallers.put(StoreResponse.MODE_SUCCESS, storeResponseMarshaller);
        marshallers.put(StoreResponse.MODE_FAILURE, storeResponseMarshaller);
    }

    /** {@inheritDoc} */
    public ParameterMap marshall(AttributeExchangeMessage message) throws MarshallingException {
        ParameterMap parameters = new ParameterMap();

        AttributeExchangeMarshaller marshaller = marshallers.get(message.getMode());
        if (marshallers == null) {
            log.error("Unable to find Attribute Exchange message marshaller for mode: {}", message.getMode());
            throw new MarshallingException("Unable to find Attribute Exchange message marshaller for mode: "
                    + message.getMode());
        }

        parameters.put(Parameter.mode.QNAME, message.getMode());

        marshaller.marshall(message, parameters);
        return parameters;
    }

}