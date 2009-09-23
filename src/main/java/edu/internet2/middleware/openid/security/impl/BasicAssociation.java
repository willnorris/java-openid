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

package edu.internet2.middleware.openid.security.impl;

import java.security.Key;

import org.joda.time.DateTime;

import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;
import edu.internet2.middleware.openid.security.Association;

/**
 * Implementation of {@link Association}.
 */
public class BasicAssociation implements Association {

    /** Association handle. */
    private String handle;

    /** Association type. */
    private AssociationType associationType;

    /** Expiration instant. */
    private DateTime expiration;

    /** Entity identifier. */
    private String entity;

    /** MAC key. */
    private Key macKey;

    /** {@inheritDoc} */
    public String getHandle() {
        return handle;
    }

    /**
     * Set the association handle.
     * 
     * @param newHandle the association handle
     */
    public void setHandle(String newHandle) {
        handle = newHandle;
    }

    /** {@inheritDoc} */
    public AssociationType getAssociationType() {
        return associationType;
    }

    /**
     * Set the association type.
     * 
     * @param newType the association type
     */
    public void setAssociationType(AssociationType newType) {
        associationType = newType;
    }

    /** {@inheritDoc} */
    public DateTime getExpiration() {
        return expiration;
    }

    /**
     * Set the association expiration.
     * 
     * @param newExpiration the expiration
     */
    public void setExpiration(DateTime newExpiration) {
        expiration = newExpiration;
    }

    /** {@inheritDoc} */
    public String getEntity() {
        return entity;
    }

    /**
     * Set the association entity identifier.
     * 
     * @param newEntity the entity identifier
     */
    public void setEntity(String newEntity) {
        entity = newEntity;
    }

    /** {@inheritDoc} */
    public Key getMacKey() {
        return macKey;
    }

    /**
     * Set the MAC key for the association.
     * 
     * @param newMacKey the MAC key
     */
    public void setMacKey(Key newMacKey) {
        macKey = newMacKey;
    }

}