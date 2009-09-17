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
import edu.internet2.middleware.openid.message.ParameterMap;
import edu.internet2.middleware.openid.message.Message.Parameter;

/**
 * Unmarshaller for {@link AssociationError} messages.
 */
public class AssociationErrorUnmarshaller extends AbstractMessageUnmarshaller<AssociationError> {

    /** {@inheritDoc} */
    public void unmarshallParameters(AssociationError response, ParameterMap parameters) {
        response.setAssociationType(AssociationType.getType(parameters.get(Parameter.assoc_type)));
        response.setSessionType(SessionType.getType(parameters.get(Parameter.session_type)));
        response.setError(parameters.get(Parameter.error));
        response.setContact(parameters.get(Parameter.contact));
        response.setReference(parameters.get(Parameter.reference));
    }

}