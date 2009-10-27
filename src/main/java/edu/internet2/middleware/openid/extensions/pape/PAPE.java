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

package edu.internet2.middleware.openid.extensions.pape;

import javax.xml.namespace.QName;

/**
 * OpenID Provider Authentication Policy Extension.
 */
public final class PAPE {

    /**
     * Namespace URI for PAPE version 1.0.
     */
    public static final String PAPE_10_NS = "http://specs.openid.net/extensions/pape/1.0";

    /**
     * Preferred namespace alias for PAPE message parameters.
     */
    public static final String PAPE_NS_ALIAS = "pape";

    /** Prefix for PAPE assurance level aliases. */
    public static final String ASSURANCE_ALIAS_PREFIX = "a";

    /** PAPE authentication policy representing Phishing-Resistant Authentication. */
    public static final String PAPE_AUTH_POLICY_PHISHING = "http://schemas.openid.net/pape/policies/2007/06/phishing-resistant";

    /** PAPE authentication policy representing Multi-Factory Authentication. */
    public static final String PAPE_AUTH_POLICY_MULTIFACTOR = "http://schemas.openid.net/pape/policies/2007/06/multi-factor";

    /** PAPE authentication policy representing Physical Multi-Factory Authentication. */
    public static final String PAPE_AUTH_POLICY_MULTIFACTOR_PHYSICAL = "http://schemas.openid.net/pape/policies/2007/06/multi-factor-physical";

    /** PAPE authentication policy representing no policies. */
    public static final String PAPE_AUTH_POLICY_NONE = "http://schemas.openid.net/pape/policies/2007/06/none";

    /**
     * PAPE message parameters.
     */
    public static enum Parameter {
        /**
         * Maximum amount of time allowed to pass before user must be re-authenticated in a matter fitting the requested
         * policies.
         */
        max_auth_age,

        /**
         * The date and time of when the user was most recently authenticated in accordance with the asserted
         * authentication policies.
         */
        auth_time,

        /**
         * Authentication policies that should be satisfied when authenticating the user.
         */
        preferred_auth_policies,

        /**
         * Authentication policies that were satisfied when authenticating the user.
         */
        auth_policies,

        /**
         * Custom assurance level.
         */
        auth_level,

        /**
         * Custom assurance levels requested.
         */
        preferred_auth_level_types;

        /** QName. */
        public final QName QNAME;

        /** Constructor. */
        Parameter() {
            this.QNAME = new QName(PAPE_10_NS, this.toString(), PAPE_NS_ALIAS);
        }
    }

    /** Constructor. */
    protected PAPE() {
    }

}