/*
 * Copyright [2009] [University Corporation for Advanced Internet Development, Inc.]
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

package edu.internet2.middleware.openid.message.ax;

/**
 * Attribute Exchange.
 */
public final class AttributeExchange {

    /** Namespace for Attribute Exchange version 1.0. */
    public static final String AX_10_NS = "http://openid.net/srv/ax/1.0";

    /** Attribute Exchange parameters. */
    public static enum Parameter {

        /** Relying party's update URL. */
        update_url,

        /** Required SimpleRegistration fields. */
        required,

        /** Optional SimpleRegistration fields. */
        if_available,

        /** Error message. */
        error,

        /** Attribute type. */
        type,

        /** Number of attribute values. */
        count,

        /** Attribute value. */
        value,
    }

    /** Prefix for AX attribute aliases. */
    public static final String ALIAS_PREFIX = "attr";

}