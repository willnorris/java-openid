
package com.shibfaced.openid.message.ax.impl;

import java.util.HashMap;
import java.util.Map;

import com.shibfaced.openid.message.Marshaller;
import com.shibfaced.openid.message.ax.StoreResponse;
import com.shibfaced.openid.message.ax.AttributeExchange.Parameter;
import com.shibfaced.openid.util.DatatypeHelper;

/**
 * StoreResponseMarshaller.
 */
public class StoreResponseMarshaller implements Marshaller<StoreResponse> {

    /** {@inheritDoc} */
    public Map<String, String> marshall(StoreResponse response) {
        Map<String, String> parameters = new HashMap<String, String>();

        if (!DatatypeHelper.isEmpty(response.getError())) {
            parameters.put(Parameter.error.toString(), response.getError());
        }

        return parameters;
    }

}