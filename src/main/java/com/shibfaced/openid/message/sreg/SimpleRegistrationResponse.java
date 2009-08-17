
package com.shibfaced.openid.message.sreg;

import java.util.EnumMap;

import com.shibfaced.openid.message.MessageExtension;
import com.shibfaced.openid.message.sreg.SimpleRegistration.Field;

/**
 * Simple Registration extension for an OpenID authentication response.
 */
public interface SimpleRegistrationResponse extends MessageExtension {

    /**
     * Get field map, which maps field names to values.
     * 
     * @return the field map
     */
    public EnumMap<Field, String> getFields();
}