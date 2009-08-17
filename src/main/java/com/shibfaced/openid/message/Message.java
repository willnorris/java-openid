
package com.shibfaced.openid.message;

import java.util.Collection;
import java.util.Map;

/**
 * OpenID protocol message.
 */
public interface Message {

    /**
     * Namespace for all OpenID 2.0 messages.
     */
    public static final String OPENID2_NS = "http://specs.openid.net/auth/2.0";

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
        is_valid,

    }

    /**
     * Get the message namespace.
     * 
     * @return the namespace
     */
    public String getNamespace();

    /**
     * Get the message mode.
     * 
     * @return the mode
     */
    public String getMode();

    /**
     * Get message extensions.
     * 
     * @return the extenesions
     */
    public Collection<MessageExtension> getExtensions();

    /**
     * Get an unmodifiable map of the message's parameters.
     * 
     * @return message parameters
     */
    public Map<Parameter, String> getParameters();

}