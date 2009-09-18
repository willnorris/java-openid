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

    /** Association type. */
    private AssociationType associationType;

    /** Expiration instant. */
    private DateTime expiration;

    /** Association handle. */
    private String handle;

    /** MAC key. */
    private Key macKey;

    /** {@inheritDoc} */
    public AssociationType getAssociationType() {
        return associationType;
    }

    public void setAssociationType(AssociationType newType) {
        associationType = newType;
    }

    /** {@inheritDoc} */
    public DateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(DateTime newExpiration) {
        expiration = newExpiration;
    }

    /** {@inheritDoc} */
    public String getHandle() {
        return handle;
    }

    public void setHandle(String newHandle) {
        handle = newHandle;
    }

    /** {@inheritDoc} */
    public Key getMacKey() {
        return macKey;
    }

    public void setMacKey(Key newMacKey) {
        macKey = newMacKey;
    }

}