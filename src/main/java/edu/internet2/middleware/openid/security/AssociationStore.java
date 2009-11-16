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

/**
 * Manage the persistent storage and retrieval of {@link Association}s.
 */
public interface AssociationStore {

    /**
     * Add association to the store.
     * 
     * @param association association to add
     */
    public void add(Association association);

    /**
     * Retrieve an association from the store by its handle.
     * 
     * @param handle handle of association to retrieve
     * @return store association with specified handle
     */
    public Association get(String handle);

    /**
     * Retrieve the association to use for the specified entity identifier.
     * 
     * @param entity entity to retrieve association for
     * @return association for the entity
     */
    public Association getByEntity(String entity);

    /**
     * Invalidate the specified association. Some store implementations may treat this the same as
     * {@link #remove(Association)}.
     * 
     * @param association association to invalidate
     */
    public void invalidate(Association association);

    /**
     * Invalidate the association with the specified handle. Some store implementations may treat this the same as
     * {@link #remove(String)}.
     * 
     * @param handle handle of association to invalidate
     */
    public void invalidate(String handle);

    /**
     * Remove the specified association from the store.
     * 
     * @param association association to remove
     */
    public void remove(Association association);

    /**
     * Remove the association with the specified handle from the store.
     * 
     * @param handle handle of association to remove
     */
    public void remove(String handle);

}