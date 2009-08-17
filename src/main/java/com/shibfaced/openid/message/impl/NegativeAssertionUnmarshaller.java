
package com.shibfaced.openid.message.impl;

import java.util.Map;

import com.shibfaced.openid.message.NegativeAssertion;
import com.shibfaced.openid.message.Unmarshaller;
import com.shibfaced.openid.message.Message.Parameter;

/**
 * NegativeAssertionUnmarshaller.
 */
public class NegativeAssertionUnmarshaller implements Unmarshaller<NegativeAssertion> {

    /** {@inheritDoc} */
    public NegativeAssertion unmarshall(Map<String, String> parameters) {
        NegativeAssertionImpl response = new NegativeAssertionImpl();

        response.setMode(parameters.get(Parameter.mode.toString()));

        return response;
    }

}