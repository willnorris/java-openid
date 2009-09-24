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

package edu.internet2.middleware.openid.message;

import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.namespace.QName;

import org.opensaml.xml.util.LazyList;

/**
 * A map of parameters that make up an OpenID message.
 * 
 * Parameters are stored as {@link QName}s that consist of the parameter's namespace and name. For parameters that are
 * part of the core OpenID specification, the namespace should correspond to the OpenID protocol version. For parameters
 * that are defined by an OpenID extension, the parameter namespace should correspond with the namespace of that
 * extension. A preferred prefix may be included as the prefix portion of the {@link QName}. If different parameters
 * declare different prefixes for the same namesapce, only one will be used.
 * 
 * The 'signed' OpenID parameter is stored separately as a list of {@link QName}s. This is because its value is
 * dependent upon the resulting prefixes that are assigned to each namespace during transport. {@link MessageCodec}s are
 * responsible for properly encoding and decoding the 'signed' parameter value appopriately.
 */
public class ParameterMap extends LinkedHashMap<QName, String> {

    /** Serial Version UID. */
    private static final long serialVersionUID = 2956813358977663257L;

    /** Signed parameters. */
    private List<QName> signedParameters;

    /** Constructor. */
    public ParameterMap() {
        super();
        signedParameters = new LazyList<QName>();
    }

    /**
     * Get the names of the parameters that are used to generate the signature.
     * 
     * @return the signed parameters
     */
    public List<QName> getSignedParameters() {
        return signedParameters;
    }

}