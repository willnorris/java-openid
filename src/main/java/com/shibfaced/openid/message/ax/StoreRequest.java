
package com.shibfaced.openid.message.ax;

import java.util.List;
import java.util.Map;

import com.shibfaced.openid.message.MessageExtension;

/**
 * Request for an OpenID Provider to store identity information for a user.
 */
public interface StoreRequest extends MessageExtension {

    /**
     * Attribute Exchange mode for a store request.
     */
    public static final String MODE = "store_request";

    /**
     * The attributes to store.
     * 
     * @return the attributes
     */
    public Map<String, List<String>> getAttributes();

}