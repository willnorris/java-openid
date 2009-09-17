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

import edu.internet2.middleware.openid.common.OpenIDConstants.Parameter;
import edu.internet2.middleware.openid.message.AssociationError;
import edu.internet2.middleware.openid.message.ParameterMap;

/**
 * Marshaller for {@link AssociationError} messages.
 */
public class AssociationErrorMarshaller extends AbstractMessageMarshaller<AssociationError> {

    /** {@inheritDoc} */
    public void marshallParameters(AssociationError response, ParameterMap parameters) {
        parameters.put(Parameter.assoc_type.QNAME, response.getAssociationType().toString());
        parameters.put(Parameter.session_type.QNAME, response.getSessionType().toString());

        parameters.put(Parameter.error.QNAME, response.getError());
        parameters.put(Parameter.error_code.QNAME, response.getErrorCode());
        parameters.put(Parameter.contact.QNAME, response.getContact());
        parameters.put(Parameter.reference.QNAME, response.getReference());
    }

}