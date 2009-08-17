/*
 * Copyright 2009 University Corporation for Advanced Internet Development, Inc.
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

import java.util.ArrayList;
import java.util.List;

import edu.internet2.middleware.openid.message.Message;
import edu.internet2.middleware.openid.message.SignedMessage;

/**
 * Abstract implementation for signed messages.
 */
public abstract class AbstractSignedMessage extends AbstractMessage implements SignedMessage {

    /** Signed Fields. */
    private List<String> signedFields;

    /**
     * Constructor.
     */
    public AbstractSignedMessage() {
        signedFields = new ArrayList<String>();
    }

    /** {@inheritDoc} */
    public List<String> getSignedFields() {
        return signedFields;
    }

    /** {@inheritDoc} */
    public String getSignature() {
        return parameters.get(Message.Parameter.sig);
    }

    /**
     * Set the signature.
     * 
     * @param signature the signature to set
     */
    public void setSignature(String signature) {
        parameters.put(Parameter.sig, signature);
    }
}