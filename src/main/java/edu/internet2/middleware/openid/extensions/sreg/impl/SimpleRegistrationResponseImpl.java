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

package edu.internet2.middleware.openid.extensions.sreg.impl;

import java.util.EnumMap;

import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistration;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistrationResponse;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistration.Field;

/**
 * Implementation of {@link SimpleRegistrationResponse}.
 */
public class SimpleRegistrationResponseImpl implements SimpleRegistrationResponse {

    /** Message fields. */
    private EnumMap<Field, String> fields;

    /** Constructor. */
    public SimpleRegistrationResponseImpl() {
        fields = new EnumMap(Field.class);
    }

    /** {@inheritDoc} */
    public String getNamespace() {
        // TODO handle SREG_10_NS
        return SimpleRegistration.SREG_11_NS;
    }

    /** {@inheritDoc} */
    public EnumMap<Field, String> getFields() {
        return fields;
    }

}