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

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import edu.internet2.middleware.openid.message.Marshaller;
import edu.internet2.middleware.openid.message.MessageBuilder;
import edu.internet2.middleware.openid.message.Unmarshaller;

/**
 * Class for loading library configuration files and retrieving the configured components.
 */
public class Configuration {

    /** Message Builders. */
    private static Map<QName, MessageBuilder> messageBuilders = new HashMap<QName, MessageBuilder>();

    /** Message Marshallers. */
    private static Map<QName, Marshaller> messageMarshallers = new HashMap<QName, Marshaller>();

    /** Message Unmarshallers. */
    private static Map<QName, Unmarshaller> messageUnmarshallers = new HashMap<QName, Unmarshaller>();

    /** Constructor. */
    protected Configuration() {
    }

    /**
     * Get message builders.
     * 
     * @return message builders.
     */
    public static Map<QName, MessageBuilder> getBuilders() {
        return messageBuilders;
    }

    /**
     * Get message marshallers.
     * 
     * @return message marshallers.
     */

    public static Map<QName, Marshaller> getMarshallers() {
        return messageMarshallers;
    }

    /**
     * Get message unmarshallers.
     * 
     * @return message unmarshallers.
     */

    public static Map<QName, Unmarshaller> getUnmarshallers() {
        return messageUnmarshallers;
    }

}