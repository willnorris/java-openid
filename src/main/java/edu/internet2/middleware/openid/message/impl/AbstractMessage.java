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

package edu.internet2.middleware.openid.message.impl;

import java.util.Collection;

import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.extensions.MessageExtension;
import edu.internet2.middleware.openid.message.Message;

/**
 * Base class for OpenID message implementation.
 */
public abstract class AbstractMessage implements Message {

    /**
     * Message extensions.
     */
    private Collection<MessageExtension> extensions;

    /** {@inheritDoc} */
    public String getNamespace() {
        return OpenIDConstants.OPENID_20_NS;
    }

    /** {@inheritDoc} */
    public Collection<MessageExtension> getExtensions() {
        return extensions;
    }

}