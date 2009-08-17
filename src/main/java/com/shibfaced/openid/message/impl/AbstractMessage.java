
package com.shibfaced.openid.message.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.shibfaced.openid.message.Message;
import com.shibfaced.openid.message.MessageExtension;

/**
 * AbstractMessage.
 */
public abstract class AbstractMessage implements Message {

    /** Message parameters. */
    Map<Parameter, String> parameters;

    /**
     * Message extensions.
     */
    private Collection<MessageExtension> extensions;

    public AbstractMessage() {
        parameters = new HashMap<Parameter, String>();
    }

    /** {@inheritDoc} */
    public String getNamespace() {
        return Message.OPENID2_NS;
    }

    /** {@inheritDoc} */
    public Collection<MessageExtension> getExtensions() {
        return extensions;
    }

    /** {@inheritDoc} */
    public Map<Parameter, String> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }
}
