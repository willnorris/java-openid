/*
 * Copyright [2009] [University Corporation for Advanced Internet Development, Inc.]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
