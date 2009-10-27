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

import java.util.Date;

import edu.internet2.middleware.openid.Configuration;
import edu.internet2.middleware.openid.common.IdentifierGenerator;
import edu.internet2.middleware.openid.security.NonceGenerator;

/**
 * Nonce generator that uses an {@link IdentifierGenerator} to ensure uniqueness.
 */
public class IdentifierNonceGenerator implements NonceGenerator {

    /** The unique id generator. */
    private IdentifierGenerator identifierGenerator;

    /** Constructor. */
    public IdentifierNonceGenerator() {
    }

    /**
     * Get the identifier generator.
     * 
     * @return the identifier generator
     */
    public IdentifierGenerator getIdentifierGenerator() {
        return identifierGenerator;
    }

    /**
     * Set the identifier generator.
     * 
     * @param newGenerator the identifier generator
     */
    public void setIdentifierGenerator(IdentifierGenerator newGenerator) {
        identifierGenerator = newGenerator;
    }

    /** {@inheritDoc} */
    public String generateNonce() {
        Date date = new Date();
        return Configuration.getInternetDateFormat().format(date) + identifierGenerator.generateIdentifier(4);
    }

}