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

package edu.internet2.middleware.openid.message.impl;

import edu.internet2.middleware.openid.association.Association.AssociationType;
import edu.internet2.middleware.openid.association.Association.SessionType;
import edu.internet2.middleware.openid.message.AssociationError;

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