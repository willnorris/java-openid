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
import edu.internet2.middleware.openid.message.MessageBuilderFactory;
import edu.internet2.middleware.openid.message.io.MarshallerFactory;
import edu.internet2.middleware.openid.message.io.UnmarshallerFactory;

/**
 * Class for loading library configuration files and retrieving the configured components.
 */
public class Configuration {

    /** Message Builders. */
    private static MessageBuilderFactory messageBuilders = new MessageBuilderFactory();

    /** Message Marshallers. */
    private static MarshallerFactory messageMarshallers = new MarshallerFactory();

    /** Message Unmarshallers. */
    private static UnmarshallerFactory messageUnmarshallers = new UnmarshallerFactory();

    /** Date Format to use when generating nonces. */
    private static DateFormat nonceDateFormat;

    /** Constructor. */
    protected Configuration() {
    }

    /**
     * Get message builders.
     * 
     * @return message builders.
     */
    public static MessageBuilderFactory getBuilders() {
        return messageBuilders;
    }

    /**
     * Get message marshallers.
     * 
     * @return message marshallers.
     */
    public static MarshallerFactory getMarshallers() {
        return messageMarshallers;
    }

    /**
     * Get message unmarshallers.
     * 
     * @return message unmarshallers.
     */
    public static UnmarshallerFactory getUnmarshallers() {
        return messageUnmarshallers;
    }

    /**
     * Get the nonce date format.
     * 
     * @return the nonce date format
     */
    public static DateFormat getNonceDateFormat() {
        if (nonceDateFormat == null) {
            nonceDateFormat = new SimpleDateFormat(OpenIDConstants.INTERNET_DATE_FORMAT);
            nonceDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        }

        return nonceDateFormat;
    }

}