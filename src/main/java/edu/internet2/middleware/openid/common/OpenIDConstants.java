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

import java.math.BigInteger;

import javax.crypto.spec.DHParameterSpec;
import javax.xml.namespace.QName;

/**
 * OpenID Constants.
 */
public final class OpenIDConstants {

    /** OpenID 2.0 Namespace URI. */
    public static final String OPENID_20_NS = "http://specs.openid.net/auth/2.0";

    /**
     * Fake mode value for {@link edu.internet2.middleware.openid.message.AssociationResponse} messages.
     * 
     * All OpenID indirect messages as well as direct requests include a "mode" parameter to identify to kind of message
     * it is. Direct response messages technically do not require a "mode" parameter because these messages are always
     * present in the context of an accompanying request. However, when writing a generic factory for message
     * {@link edu.internet2.middleware.openid.message.io.MessageUnmarshaller}s, this lack of a "mode" parameter becomes
     * a problem. The following fake "mode" values are defined and used within this library solely for the purpose of
     * internal identification of message builders. If a future version of OpenID adds a "mode" parameter to these
     * messages, the changes necessary to support that should be quite minimal.
     */
    public static final String ASSOCIATION_RESPONSE_MODE = "X-associate_response";

    /** Fake mode value for {@link edu.internet2.middleware.openid.message.AssociationError} messages. */
    public static final String ASSOCIATION_ERROR_MODE = "X-associate_error";

    /** Fake mode value for {@link edu.internet2.middleware.openid.message.VerifyResponse} messages. */
    public static final String VERIFICATION_RESPONSE_MODE = "X-check_authentication_response";

    /** Default Diffie-Hellman Modulus (in hexadecimal). */
    public static final String DEFAULT_DH_MODULUS_HEX = "DCF93A0B883972EC0E19989AC5A2CE310E1D37717E8D9571BB7623731866E61E"
            + "F75A2E27898B057F9891C2E27A639C3F29B60814581CD3B2CA3986D268370557"
            + "7D45C2E7E52DC81C7A171876E5CEA74B1448BFDFAF18828EFD2519F14E45E382"
            + "6634AF1949E5B535CC829A483B8A76223E5D490A257F05BDFF16F2FB22C583AB";

    /** Default Diffie-Hellman Modulus. */
    public static final BigInteger DEFAULT_DH_MODULUS = new BigInteger(DEFAULT_DH_MODULUS_HEX, 16);

    /** Default Diffie-Hellman Generator. */
    public static final BigInteger DEFAULT_DH_GEN = BigInteger.valueOf(2);

    /** Default Diffie-Hellman Parameter Spec. */
    public static final DHParameterSpec DEFAULT_PARAMETER_SPEC = new DHParameterSpec(DEFAULT_DH_MODULUS, DEFAULT_DH_GEN);

    /** Prefix attached to each namespace alias declarations in OpenID Messages. */
    public static final String MESSAGE_NAMESPACE_PREFIX = "ns";

    /** Internet Date/Time format, specified by RFC 3339. */
    public static final String INTERNET_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

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
        HMAC_SHA1("HmacSHA1", 160),

        /**
         * Association type using the HMAC-SHA256 signature algorithm.
         */
        HMAC_SHA256("HmacSHA256", 256);

        /** The algorithm for this type. */
        private final String algorithm;

        /** Key size. */
        private final int keySize;

        /**
         * Constructor.
         * 
         * @param newAlgorithm the algorithm for this type
         * @param newSize the key size for this type
         */
        AssociationType(String newAlgorithm, int newSize) {
            this.algorithm = newAlgorithm;
            this.keySize = newSize;
        }

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

        /**
         * Get the algorithm for this type.
         * 
         * @return the algorithm for this type
         */
        public String getAlgorithm() {
            return algorithm;
        }

        /**
         * Get the key size for this type.
         * 
         * @return key size
         */
        public int getKeySize() {
            return keySize;
        }
    }

    /**
     * Valid association session types.
     */
    public static enum SessionType {

        /**
         * Association session type using Diffie-Hellman Key Exchange of 160 bit MAC keys.
         */
        DH_SHA1("SHA-1"),

        /**
         * Association session type using Diffie-Hellman Key Exchange of 256 bit MAC keys.
         */
        DH_SHA256("SHA-256"),

        /**
         * Association session type which does not encrypt the MAC key.
         */
        no_encryption(null);

        /** The algorithm for this type. */
        private final String algorithm;

        /**
         * Constructor.
         * 
         * @param newAlgorithm algorithm for this type
         */
        SessionType(String newAlgorithm) {
            this.algorithm = newAlgorithm;
        }

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

        /**
         * Get the algorithm for this type.
         * 
         * @return the algorithm for this type
         */
        public String getAlgorithm() {
            return algorithm;
        }
    }

    /** Constructor. */
    private OpenIDConstants() {
    }

}