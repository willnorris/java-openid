
package com.shibfaced.openid.message.impl;

import com.shibfaced.openid.association.Association.AssociationType;
import com.shibfaced.openid.association.Association.SessionType;
import com.shibfaced.openid.message.AssociationError;

/**
 * AssociationErrorImpl.
 */
public class AssociationErrorImpl extends AbstractMessage implements AssociationError {

    /**
     * Association type.
     */
    private AssociationType associationType;

    /**
     * Association session type.
     */
    private SessionType sessionType;

    /** {@inheritDoc} */
    public AssociationType getAssociationType() {
        return associationType;
    }

    /** {@inheritDoc} */
    public String getError() {
        return parameters.get(Parameter.error);
    }

    /** {@inheritDoc} */
    public String getErrorCode() {
        return AssociationError.ERROR_CODE;
    }

    /** {@inheritDoc} */
    public SessionType getSessionType() {
        return sessionType;
    }

    /**
     * Throws UnsupportedOperationException. Association errors do not have a mode value.
     * 
     * @return mode
     */
    public String getMode() {
        throw new UnsupportedOperationException();
    }

    /**
     * Set the association type.
     * 
     * @param type the associationType to set
     */
    public void setAssociationType(AssociationType type) {
        associationType = type;
    }
    
    /**
     * Set the association type.
     * 
     * @param type the associationType to set
     */
    public void setAssociationType(String type) {
        setAssociationType(AssociationType.getType(type));
    }

    /**
     * Set the error message.
     * 
     * @param newError the error to set
     */
    public void setError(String newError) {
        parameters.put(Parameter.error, newError);
    }

    /**
     * Set the association session type.
     * 
     * @param type the sessionType to set
     */
    public void setSessionType(SessionType type) {
        sessionType = type;
    }
    
    /**
     * Set the association session type.
     * 
     * @param type the sessionType to set
     */
    public void setSessionType(String type) {
        setSessionType(SessionType.getType(type));
    }

}