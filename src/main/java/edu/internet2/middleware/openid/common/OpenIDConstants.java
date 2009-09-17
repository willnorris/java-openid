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

    /** Constructor. */
    private OpenIDConstants() {
    }

}