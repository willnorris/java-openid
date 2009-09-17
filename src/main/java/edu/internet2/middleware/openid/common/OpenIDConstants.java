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

package edu.internet2.middleware.openid.common;

import javax.xml.namespace.QName;

/**
 * OpenID Constants.
 */
public class OpenIDConstants {

    /** OpenID 2.0 Namespace URI. */
    public static final String OPENID_20_NS = "http://specs.openid.net/auth/2.0";

    /**
     * OpenID parameters.
     */
    public static enum Parameter {

        /** Namespace. */
        ns,

        /** Mode. */
        mode,

        /** Association type. */
        assoc_type,

        /** OpenID Provider endpoint URL. */
        op_endpoint,

        /** Claimed ID. */
        claimed_id,

        /** Identity. */
        identity,

        /** Return-to URL. */
        return_to,

        /** Response nonece. */
        response_nonce,

        /** Invalidate handle. */
        invalidate_handle,

        /** Signed parameters. */
        signed,

        /** Signature. */
        sig,

        /** Realm. */
        realm,

        /** Session type. */
        session_type,

        /** Diffie-Hellman modulus. */
        dh_modulus,

        /** Diffie-Hellman generator. */
        dh_gen,

        /** Relying party's Diffie-Hellman public key. */
        dh_consumer_public,

        /** Association handle. */
        assoc_handle,

        /** Lifetime of response. */
        expires_in,

        /** MAC key. */
        mac_key,

        /** OpenID provider's Diffie-Hellman public key. */
        dh_server_public,

        /** Encrypted MAC key. */
        enc_mac_key,

        /** Error message. */
        error,

        /** Error code. */
        error_code,

        /** Contact. */
        contact,

        /** Reference. */
        reference,

        /** Is Valid. */
        is_valid;

        /** QName. */
        public final QName QNAME;

        /** Constructor. */
        Parameter() {
            this.QNAME = new QName(OPENID_20_NS, this.toString());
        }

    }

    /**
     * Valid association types.
     */
    public static enum AssociationType {
        /**
         * Association type using the HMAC-SHA1 signature algorithm.
         */
        HMAC_SHA1,

        /**
         * Association type using the HMAC-SHA256 signature algorithm.
         */
        HMAC_SHA256;

        /** {@inheritDoc} */
        public String toString() {
            return name().replace('_', '-');
        }

        /**
         * Get specified association type.
         * 
         * @param type name of association type to get
         * @return the association type
         */
        public static AssociationType getType(String type) {
            if (type == null) {
                return null;
            }

            return AssociationType.valueOf(type.replace('-', '_'));
        }
    }

    /**
     * Valid association session types.
     */
    public static enum SessionType {

        /**
         * Association session type using Diffie-Hellman Key Exchange of 160 bit MAC keys.
         */
        DH_SHA1,

        /**
         * Association session type using Diffie-Hellman Key Exchange of 256 bit MAC keys.
         */
        DH_SHA256,

        /**
         * Association session type which does not encrypt the MAC key.
         */
        no_encryption;

        /** {@inheritDoc} */
        public String toString() {
            return name().replace('_', '-');
        }

        /**
         * Get specified session type.
         * 
         * @param type name of session type to get
         * @return the session type
         */
        public static SessionType getType(String type) {
            if (type == null) {
                return null;
            }

            return SessionType.valueOf(type.replace('-', '_'));
        }
    }

    /** Constructor. */
    private OpenIDConstants() {
    }

}