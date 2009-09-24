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

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.common.IdentifierGenerator;
import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;
import edu.internet2.middleware.openid.common.impl.RandomIdentifierGenerator;
import edu.internet2.middleware.openid.security.Association;
import edu.internet2.middleware.openid.security.AssociationBuilder;
import edu.internet2.middleware.openid.security.AssociationUtils;

/**
 * Basic association builder.
 */
public class BasicAssociationBuilder implements AssociationBuilder {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(BasicAssociationBuilder.class);

    /** Handle Generator. */
    private IdentifierGenerator handleGenerator;

    /** Constructor. */
    public BasicAssociationBuilder() {
        handleGenerator = new RandomIdentifierGenerator();
    }

    /** {@inheritDoc} */
    public Association buildAssociation(AssociationType type, int lifetime, String entity) {
        log.debug("Building new association of type {}", type);

        BasicAssociation association = new BasicAssociation();
        association.setHandle(handleGenerator.generateIdentifier());
        association.setAssociationType(type);
        association.setEntity(entity);
        association.setExpiration(new DateTime().plus(lifetime * 1000));
        association.setMacKey(AssociationUtils.generateMacKey(type.getAlgorithm(), type.getKeySize()));

        return association;
    }

}