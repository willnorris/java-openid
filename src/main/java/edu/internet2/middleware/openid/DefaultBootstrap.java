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

package edu.internet2.middleware.openid;

import javax.xml.namespace.QName;

import org.opensaml.xml.ConfigurationException;

import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.message.AssociationRequest;
import edu.internet2.middleware.openid.message.impl.AssociationRequestBuilder;
import edu.internet2.middleware.openid.message.impl.AssociationRequestMarshaller;
import edu.internet2.middleware.openid.message.impl.AssociationRequestUnmarshaller;

/**
 * This class can be used to bootstrap the OpenXRD library with the default configurations that ship with the library.
 */
public class DefaultBootstrap {

    /** Constructor. */
    protected DefaultBootstrap() {
    }

    /**
     * Initializes the OpenSAML library, loading default configurations.
     * 
     * @throws ConfigurationException thrown if there is a problem initializing the OpenSAML library
     */
    public static synchronized void bootstrap() throws ConfigurationException {
        initializeObjectProviders();
    }

    /**
     * Initialize object providers.
     */
    public static void initializeObjectProviders() {
        QName qname = new QName(OpenIDConstants.OPENID_20_NS, AssociationRequest.MODE);
        Configuration.getBuilders().put(qname, new AssociationRequestBuilder());
        Configuration.getMarshallers().put(qname, new AssociationRequestMarshaller());
        Configuration.getUnmarshallers().put(qname, new AssociationRequestUnmarshaller());
    }

}