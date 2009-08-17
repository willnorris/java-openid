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

package com.shibfaced.openid.message.sreg;

import java.net.URL;
import java.util.EnumSet;

import com.shibfaced.openid.message.MessageExtension;
import com.shibfaced.openid.message.sreg.SimpleRegistration.Field;

/**
 * Simple Registration extension for an OpenID authentication request.
 */
public interface SimpleRegistrationRequest extends MessageExtension {

    /**
     * Fields which, if absent from the response, will prevent the relying party from completing the registration
     * without end user interaction. Fields do not include the "openid.sreg." prefix.
     * 
     * @return the required fields
     */
    public EnumSet<Field> getRequiredFields();

    /**
     * Fields that will be used by the relying party, but whose absence wil not prevent the registration from
     * completing. Fields do not include the "openid.sreg." prefix.
     * 
     * @return the optional fields
     */
    public EnumSet<Field> getOptionalFields();

    /**
     * A URL which the Relying Party provides to give the end user a place to read about how the profile data will be
     * used.
     * 
     * @return the policy URL
     */
    public URL getPolicyURL();
}