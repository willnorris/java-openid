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

package com.shibfaced.openid.message.impl;

import java.util.Map;

import com.shibfaced.openid.message.Unmarshaller;
import com.shibfaced.openid.message.VerifyResponse;
import com.shibfaced.openid.message.Message.Parameter;

/**
 * VerifyResponseUnmarshaller.
 */
public class VerifyResponseUnmarshaller implements Unmarshaller<VerifyResponse> {

    /** {@inheritDoc} */
    public VerifyResponse unmarshall(Map<String, String> parameters) {
        VerifyResponseImpl response = new VerifyResponseImpl();

        response.setInvalidateHandle(parameters.get(Parameter.invalidate_handle.toString()));
        response.setValid(Boolean.parseBoolean(parameters.get(Parameter.is_valid.toString())));

        return response;
    }

}