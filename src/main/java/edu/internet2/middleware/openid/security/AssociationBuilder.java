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

import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;

/**
 * Association Builder.
 */
public interface AssociationBuilder {

    /**
     * Build an association with the specified properties.
     * 
     * @param type AssociationType for the built association
     * @param lifetime lifetime of the build association, in seconds
     * @param entity Entity identifier for the build association
     * @param isPrivate whether the association is private
     * @return the built association
     */
    public Association buildAssociation(AssociationType type, int lifetime, String entity, boolean isPrivate);

}