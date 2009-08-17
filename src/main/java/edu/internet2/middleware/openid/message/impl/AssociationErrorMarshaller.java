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

import java.util.HashMap;
import java.util.Map;

import edu.internet2.middleware.openid.association.Association.AssociationType;
import edu.internet2.middleware.openid.message.AssociationError;
import edu.internet2.middleware.openid.message.Marshaller;
import edu.internet2.middleware.openid.message.Message.Parameter;

/**
 * AssociationErrorMarshaller.
 */
public class AssociationErrorMarshaller implements Marshaller<AssociationError> {

    /** {@inheritDoc} */
    public Map<String, String> marshall(AssociationError response) {
        Map<String, String> parameters = new HashMap<String, String>();

        AssociationType associationType = response.getAssociationType();
        if (associationType != null) {
            parameters.put(Parameter.assoc_type.toString(), associationType.toString());

            parameters.put(Parameter.error.toString(), response.getError());
            parameters.put(Parameter.error_code.toString(), response.getErrorCode());
        }

        parameters.put(Parameter.session_type.toString(), response.getSessionType().toString());

        return parameters;
    }

}