
package com.shibfaced.openid.message.impl;

import java.util.HashMap;
import java.util.Map;

import com.shibfaced.openid.message.Marshaller;
import com.shibfaced.openid.message.NegativeAssertion;
import com.shibfaced.openid.message.Message.Parameter;

/**
 * NegativeAssertionMarshaller.
 */
public class NegativeAssertionMarshaller implements Marshaller<NegativeAssertion> {

    /** {@inheritDoc} */
    public Map<String, String> marshall(NegativeAssertion response) {
        Map<String, String> parameters = new HashMap<String, String>();

        parameters.put(Parameter.mode.toString(), response.getMode());
        
        return parameters;
    }

}