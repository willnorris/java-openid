
package com.shibfaced.openid.message.impl;

import java.util.HashMap;
import java.util.Map;

import com.shibfaced.openid.message.Marshaller;
import com.shibfaced.openid.message.VerifyResponse;
import com.shibfaced.openid.message.Message.Parameter;

/**
 * VerifyResponseMarshaller.
 */
public class VerifyResponseMarshaller implements Marshaller<VerifyResponse> {

    /** {@inheritDoc} */
    public Map<String, String> marshall(VerifyResponse response) {
        Map<String, String> parameters = new HashMap<String, String>();

        parameters.put(Parameter.invalidate_handle.toString(), response.getInvalidateHandle());
        parameters.put(Parameter.is_valid.toString(), Boolean.toString(response.isValid()));

        return parameters;
    }

}