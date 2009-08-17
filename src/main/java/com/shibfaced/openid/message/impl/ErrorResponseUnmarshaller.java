
package com.shibfaced.openid.message.impl;

import java.util.Map;

import com.shibfaced.openid.message.ErrorResponse;
import com.shibfaced.openid.message.Unmarshaller;
import com.shibfaced.openid.message.Message.Parameter;

/**
 * ErrorResponseUnmarshaller.
 */
public class ErrorResponseUnmarshaller implements Unmarshaller<ErrorResponse> {

    /** {@inheritDoc} */
    public ErrorResponse unmarshall(Map<String, String> parameters) {
        ErrorResponseImpl response = new ErrorResponseImpl();

        response.setContact(parameters.get(Parameter.contact.toString()));
        response.setError(parameters.get(Parameter.error.toString()));
        response.setReference(parameters.get(Parameter.reference.toString()));

        return response;
    }

}