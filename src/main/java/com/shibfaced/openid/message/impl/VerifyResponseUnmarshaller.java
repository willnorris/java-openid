
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