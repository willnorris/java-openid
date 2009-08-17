
package com.shibfaced.openid.message.impl;

import com.shibfaced.openid.message.AuthenticationRequest;

/**
 * AuthenticationRequestImpl.
 */
public class AuthenticationRequestImpl extends AbstractMessage implements AuthenticationRequest {

    /** {@inheritDoc} */
    public String getAssociationHandle() {
        return parameters.get(Parameter.assoc_handle);
    }

    /** {@inheritDoc} */
    public String getClaimedId() {
        return parameters.get(Parameter.claimed_id);
    }

    /** {@inheritDoc} */
    public String getIdentity() {
        return parameters.get(Parameter.identity);
    }

    /** {@inheritDoc} */
    public String getRealm() {
        return parameters.get(Parameter.realm);
    }

    /** {@inheritDoc} */
    public String getReturnTo() {
        return parameters.get(Parameter.return_to);
    }

    /** {@inheritDoc} */
    public String getMode() {
        return parameters.get(Parameter.mode);
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
     * Set the realm.
     * 
     * @param realm the realm to set
     */
    public void setRealm(String realm) {
        parameters.put(Parameter.realm, realm);
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
     * Set the mode.
     * 
     * @param mode the mode to set
     */
    public void setMode(String mode) {
        parameters.put(Parameter.mode, mode);
    }

}