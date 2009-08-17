
package com.shibfaced.openid.message.impl;

import java.util.HashMap;
import java.util.Map;

import com.shibfaced.openid.message.ErrorResponse;
import com.shibfaced.openid.message.Marshaller;
import com.shibfaced.openid.message.Message.Parameter;

/**
 * ErrorResponseMarshaller.
 */
public class ErrorResponseMarshaller implements Marshaller<ErrorResponse> {

    /** {@inheritDoc} */
    public Map<String, String> marshall(ErrorResponse response) {
        Map<String, String> parameters = new HashMap<String, String>();

        parameters.put(Parameter.contact.toString(), response.getContact());
        parameters.put(Parameter.error.toString(), response.getError());
        parameters.put(Parameter.reference.toString(), response.getReference());

        return parameters;
    }
}