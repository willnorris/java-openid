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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.extensions.MessageExtensionBuilderFactory;
import edu.internet2.middleware.openid.extensions.MessageExtensionMarshallerFactory;
import edu.internet2.middleware.openid.extensions.MessageExtensionUnmarshallerFactory;
import edu.internet2.middleware.openid.message.MessageBuilderFactory;
import edu.internet2.middleware.openid.message.io.MessageMarshallerFactory;
import edu.internet2.middleware.openid.message.io.MessageUnmarshallerFactory;

/**
 * Class for loading library configuration files and retrieving the configured components.
 */
public class Configuration {

    /** Message Builders. */
    private static MessageBuilderFactory messageBuilders = new MessageBuilderFactory();

    /** Message Marshallers. */
    private static MessageMarshallerFactory messageMarshallers = new MessageMarshallerFactory();

    /** Message Unmarshallers. */
    private static MessageUnmarshallerFactory messageUnmarshallers = new MessageUnmarshallerFactory();

    /** Message Extension Builders. */
    private static MessageExtensionBuilderFactory extensionBuilders = new MessageExtensionBuilderFactory();

    /** Message Extension Marshallers. */
    private static MessageExtensionMarshallerFactory extensionMarshallers = new MessageExtensionMarshallerFactory();

    /** Message Extension Unmarshallers. */
    private static MessageExtensionUnmarshallerFactory extensionUnmarshallers = new MessageExtensionUnmarshallerFactory();

    /** Date Format that implements Internet time format according to RFC 3339. */
    private static DateFormat internetDateFormat;

    /** Constructor. */
    protected Configuration() {
    }

    /**
     * Get message builders.
     * 
     * @return message builders.
     */
    public static MessageBuilderFactory getMessageBuilders() {
        return messageBuilders;
    }

    /**
     * Get message marshallers.
     * 
     * @return message marshallers.
     */
    public static MessageMarshallerFactory getMessageMarshallers() {
        return messageMarshallers;
    }

    /**
     * Get message unmarshallers.
     * 
     * @return message unmarshallers.
     */
    public static MessageUnmarshallerFactory getMessageUnmarshallers() {
        return messageUnmarshallers;
    }

    /**
     * Get message extension builders.
     * 
     * @return message extension builders.
     */
    public static MessageExtensionBuilderFactory getExtensionBuilders() {
        return extensionBuilders;
    }

    /**
     * Get message extension marshallers.
     * 
     * @return message extension marshallers.
     */
    public static MessageExtensionMarshallerFactory getExtensionMarshallers() {
        return extensionMarshallers;
    }

    /**
     * Get message extension unmarshallers.
     * 
     * @return message extension unmarshallers.
     */
    public static MessageExtensionUnmarshallerFactory getExtensionUnmarshallers() {
        return extensionUnmarshallers;
    }

    /**
     * Get the Internet date format.
     * 
     * @return the Internet date format
     */
    public static DateFormat getInternetDateFormat() {
        if (internetDateFormat == null) {
            internetDateFormat = new SimpleDateFormat(OpenIDConstants.INTERNET_DATE_FORMAT);
            internetDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        }

        return internetDateFormat;
    }

}