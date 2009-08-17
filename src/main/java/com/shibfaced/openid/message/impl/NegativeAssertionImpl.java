
package com.shibfaced.openid.message.impl;

import com.shibfaced.openid.message.NegativeAssertion;

/**
 * NegativeAssertionImpl.
 */
public class NegativeAssertionImpl extends AbstractMessage implements NegativeAssertion {

    /** {@inheritDoc} */
    public String getMode() {
        return parameters.get(Parameter.mode);
    }

    /**
     * Set mode.
     * 
     * @param mode new mode to set.
     */
    public void setMode(String mode) {
        parameters.put(Parameter.mode, mode);
    }

}