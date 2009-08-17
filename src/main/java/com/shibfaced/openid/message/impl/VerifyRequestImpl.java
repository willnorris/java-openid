
package com.shibfaced.openid.message.impl;

import java.util.ArrayList;
import java.util.List;

import com.shibfaced.openid.message.Message;
import com.shibfaced.openid.message.VerifyRequest;

/**
 * VerifyRequestImpl.
 */
public class VerifyRequestImpl extends AbstractMessage implements VerifyRequest {

    /**
     * Signed Fields.
     */
    private List<String> signedFields;

    /**
     * Constructor.
     */
    public VerifyRequestImpl() {
        signedFields = new ArrayList<String>();
    }

    /** {@inheritDoc} */
    public String getAssociationHandle() {
        return parameters.get(Message.Parameter.assoc_handle);
    }

    /** {@inheritDoc} */
    public String getClaimedId() {
        return parameters.get(Message.Parameter.claimed_id);
    }

    /** {@inheritDoc} */
    public String getEndpoint() {
        return parameters.get(Message.Parameter.op_endpoint);
    }

    /** {@inheritDoc} */
    public String getIdentity() {
        return parameters.get(Message.Parameter.identity);
    }

    /** {@inheritDoc} */
    public String getInvalidateHandle() {
        return parameters.get(Message.Parameter.invalidate_handle);
    }

    /** {@inheritDoc} */
    public String getResponseNonce() {
        return parameters.get(Message.Parameter.response_nonce);
    }

    /** {@inheritDoc} */
    public String getReturnTo() {
        return parameters.get(Message.Parameter.return_to);
    }

    /** {@inheritDoc} */
    public String getSignature() {
        return parameters.get(Message.Parameter.sig);
    }

    /** {@inheritDoc} */
    public List<String> getSignedFields() {
        return signedFields;
    }

    /** {@inheritDoc} */
    public String getMode() {
        return VerifyRequest.MODE;
    }

    /**
     * Set the association handle.
     * 
     * @param handle the associationHandle to set
     */
    public void setAssociationHandle(String handle) {
        parameters.put(Parameter.assoc_handle, handle);
    }

    /**
     * Set the claimed Id.
     * 
     * @param claimedId the claimedId to set
     */
    public void setClaimedId(String claimedId) {
        parameters.put(Parameter.claimed_id, claimedId);
    }

    /**
     * Set the local identity.
     * 
     * @param identity the identity to set
     */
    public void setIdentity(String identity) {
        parameters.put(Parameter.identity, identity);
    }

    /**
     * Set the return to address.
     * 
     * @param returnTo the returnTo to set
     */
    public void setReturnTo(String returnTo) {
        parameters.put(Parameter.return_to, returnTo);
    }

    /**
     * Set the endpoint.
     * 
     * @param endpoint the endpoint to set
     */
    public void setEndpoint(String endpoint) {
        parameters.put(Parameter.op_endpoint, endpoint);
    }

    /**
     * Set the handle to invalidate.
     * 
     * @param handle the invalidateHandle to set
     */
    public void setInvalidateHandle(String handle) {
        parameters.put(Parameter.invalidate_handle, handle);
    }

    /**
     * Set the response nonce.
     * 
     * @param nonce the responseNonce to set
     */
    public void setResponseNonce(String nonce) {
        parameters.put(Parameter.response_nonce, nonce);
    }

    /**
     * Set the signature.
     * 
     * @param signature the signature to set
     */
    public void setSignature(String signature) {
        parameters.put(Parameter.sig, signature);
    }
}