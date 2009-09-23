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

import java.util.HashMap;
import java.util.Map;

import edu.internet2.middleware.openid.security.Association;
import edu.internet2.middleware.openid.security.AssociationStore;

/**
 * Implementation of {@link AssociationStore} that maintains storage in memory.
 */
public class InMemoryAssociationStore extends AbstractAssociationStore implements AssociationStore {

    /** Association map. */
    private Map<String, Association> associations;

    /** Constructor. */
    public InMemoryAssociationStore() {
        associations = new HashMap<String, Association>();
    }

    /** {@inheritDoc} */
    public void add(Association association) {
        associations.put(association.getHandle(), association);
    }

    /** {@inheritDoc} */
    public Association get(String handle) {
        return associations.get(handle);
    }

    /** {@inheritDoc} */
    public Association getByEntity(String entity) {
        for (Association association : associations.values()) {
            if (entity != null && entity.equals(association.getEntity())) {
                return association;
            }
        }

        return null;
    }

    /** {@inheritDoc} */
    public void invalidate(String handle) {
        remove(handle);
    }

    /** {@inheritDoc} */
    public void remove(String handle) {
        associations.remove(handle);
    }

}