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

package edu.internet2.middleware.openid.extensions.sreg;

import java.util.EnumMap;

import edu.internet2.middleware.openid.extensions.MessageExtension;
import edu.internet2.middleware.openid.extensions.sreg.SimpleRegistration.Field;

/**
 * Simple Registration extension for an OpenID authentication response.
 */
public interface SimpleRegistrationResponse extends MessageExtension {

    /**
     * Get field map, which maps field names to values.
     * 
     * @return the field map
     */
    public EnumMap<Field, String> getFields();
}