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

package edu.internet2.middleware.openid.common;

import javax.xml.namespace.QName;

/**
 * QName used to represent an OpenID namespace declaration. This is particular useful when a namespace declaration needs
 * to be included in collection of {@link QName}s, such as the 'signed' parameter of an Authentication Request.
 * 
 * It is not sufficient to simply use QNames with an empty string for the local part to represent namespace
 * declarations. This is because OpenID allows for message extensions to include a message parameter whose name is only
 * the extension alias. In that case, it would be impossible to differentiate between two QNames, one that is intended
 * to represent the namespace declaration, and the other intended to represent a standard message parameter. By
 * sub-classing {@link QName}, we can positively identify namespace declarations.
 * 
 * Users of this class should pay special attention to the class constructors. While the method signatures match
 * constructors of {@link QName}, they take different parameters.
 */
public class NamespaceQName extends QName {

    /** Serial Version UID. */
    private static final long serialVersionUID = -5483643911677688237L;

    /** Local Part to use for OpenIDNamespaceQName instances. */
    private static final String LOCAL_PART = "";

    /**
     * Constructor.
     * 
     * @param namespaceURI namespace URI
     * @param prefix namespace prefix
     */
    public NamespaceQName(String namespaceURI, String prefix) {
        super(namespaceURI, LOCAL_PART, prefix);
    }

}