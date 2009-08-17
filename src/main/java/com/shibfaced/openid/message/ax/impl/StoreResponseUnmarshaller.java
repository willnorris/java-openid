
package com.shibfaced.openid.message.ax.impl;

import java.util.Map;

import com.shibfaced.openid.message.Unmarshaller;
import com.shibfaced.openid.message.ax.StoreResponse;
import com.shibfaced.openid.message.ax.AttributeExchange.Parameter;

/**
 * StoreResponseUnmarshaller.
 */
public class StoreResponseUnmarshaller implements Unmarshaller<StoreResponse> {

    /** {@inheritDoc} */
    public StoreResponse unmarshall(Map<String, String> parameters) {
        StoreResponseImpl response = new StoreResponseImpl();

        response.setError(parameters.get(Parameter.error.toString()));

        return response;
    }

}