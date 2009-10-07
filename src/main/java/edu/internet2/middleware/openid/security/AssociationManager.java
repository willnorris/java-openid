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

package edu.internet2.middleware.openid.security;

import java.util.Date;

import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;

/**
 * Manager for building, storing, and validating associations.
 */
public class AssociationManager {

    /** Association store. */
    private AssociationStore store;

    /** Association builder. */
    private AssociationBuilder builder;

    /** Default lifetime for new associations. */
    private int defaultLifetime;

    /** Constructor. */
    public AssociationManager() {
    }

    /**
     * Get the association store.
     * 
     * @return the association store.
     */
    public AssociationStore getStore() {
        return store;
    }

    /**
     * Set the association store.
     * 
     * @param newStore the association store
     */
    public void setStore(AssociationStore newStore) {
        store = newStore;
    }

    /**
     * Get the association builder.
     * 
     * @return the association builder
     */
    public AssociationBuilder getBuilder() {
        return builder;
    }

    /**
     * Set the association builder.
     * 
     * @param newBuilder the association builder
     */
    public void setBuilder(AssociationBuilder newBuilder) {
        builder = newBuilder;
    }

    /**
     * Get the default lifetime.
     * 
     * @return the default lifetime
     */
    public int getDefaultLifetime() {
        return defaultLifetime;
    }

    /**
     * Set the default lifetime.
     * 
     * @param newLifetime the default lifetime
     */
    public void setDefaultLifetime(int newLifetime) {
        defaultLifetime = newLifetime;
    }

    /**
     * Build a new association.
     * 
     * @param type type of association to build
     * @return newly built association
     */
    public Association buildAssociation(AssociationType type) {
        return buildAssociation(type, null);
    }

    /**
     * Build a new association.
     * 
     * @param type type of association to build
     * @param entity entity identifier the association is for
     * @return newly build association
     */
    public Association buildAssociation(AssociationType type, String entity) {
        Association association = builder.buildAssociation(type, defaultLifetime, entity);
        store.add(association);
        return association;
    }

    /**
     * Get an association.
     * 
     * @param handle handle of association to get
     * @return association with the requested handle, or null if no such association exists
     */
    public Association getAssociation(String handle) {
        return store.get(handle);
    }

    /**
     * Check if an association is valid.
     * 
     * @param handle handle of association to check
     * @return true if the association is still valid, false if not
     */
    public boolean isValid(String handle) {
        return isValid(store.get(handle));
    }

    /**
     * Check if an association is valid.
     * 
     * @param association association to check
     * @return true if the association is still valid, false if not
     */
    public boolean isValid(Association association) {
        if (association == null) {
            return false;
        }

        Date now = new Date();
        if (now.after(association.getExpiration())) {
            // expired
            return false;
        }

        return true;
    }

    /**
     * Invalidate an association.
     * 
     * @param handle handle of association to invalidate
     */
    public void invalidate(String handle) {
        store.invalidate(handle);
    }

    /**
     * Invalidate an association.
     * 
     * @param association association to invalidate
     */
    public void invalidate(Association association) {
        store.invalidate(association);
    }

}