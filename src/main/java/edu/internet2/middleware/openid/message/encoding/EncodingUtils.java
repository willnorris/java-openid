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

package edu.internet2.middleware.openid.message.encoding;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPrivateKeySpec;
import javax.crypto.spec.DHPublicKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.utils.URIUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.common.NamespaceMap;
import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.common.ParameterMap;
import edu.internet2.middleware.openid.util.StringUtils;

/**
 * Encoding Utilities.
 */
public final class EncodingUtils {

    /** Logger. */
    private static final Logger log = LoggerFactory.getLogger(EncodingUtils.class);

    /** Constructor. */
    private EncodingUtils() {
    }

    /**
     * Flatten the parameter map. Each parameter QName is converted into it's dotted namespace qualified form, and
     * namespace declarations are added to the resulting parameter map. The resulting map is suitable for passing to
     * {@link MessageCodec#encode(Map<String, String>)}.
     * 
     * @param parameterMap parameter map to flatten
     * @return parameter map with flattened parameter names and namespace declarations
     */
    public static Map<String, String> flattenParameterNames(ParameterMap parameterMap) {
        Map<String, String> parameters = new LinkedHashMap<String, String>();
        NamespaceMap namespaces = parameterMap.getNamespaces();

        // add namespaces to key-value parameter map
        for (String ns : namespaces.getURIs()) {
            String key = OpenIDConstants.MESSAGE_NAMESPACE_PREFIX;
            String alias = namespaces.getAlias(ns);

            if (alias != XMLConstants.DEFAULT_NS_PREFIX) {
                key += "." + alias;
            }

            parameters.put(key, ns);
        }

        // add parameters to key-value parameter map
        for (QName qname : parameterMap.keySet()) {
            String key = encodeParameterName(qname, namespaces);
            parameters.put(key, parameterMap.get(qname));
        }

        return parameters;
    }

    /**
     * Build an appropriate {@link QName} for a namespaced parameter name.
     * 
     * @param parameter namespaced parameter name
     * @param namespaces map of registered namespaces
     * @return QName for the parameter
     */
    public static QName decodeParameterName(String parameter, NamespaceMap namespaces) {
        String[] parts = parameter.split("\\.", 2);

        // check if parameter name is for a namespace declaration
        if (OpenIDConstants.MESSAGE_NAMESPACE_PREFIX.equals(parts[0])) {
            if (parts.length == 2) {
                return new QName(namespaces.getURI(parts[1]), "", parts[1]);
            } else {
                return new QName(namespaces.getURI(XMLConstants.DEFAULT_NS_PREFIX), "");
            }
        }

        // otherwise, parameter name is for a message parameter
        if (parts.length == 2) {
            return new QName(namespaces.getURI(parts[0]), parts[1], parts[0]);
        } else {
            // default namespace
            return new QName(namespaces.getURI(XMLConstants.DEFAULT_NS_PREFIX), parts[0]);
        }
    }

    /**
     * Build an appropriate namespaced parameter name for a {@link QName}.
     * 
     * @param qname QName to convert into parameter name
     * @param namespaces map of registered namespaces
     * @return namespaced parameter name
     */
    public static String encodeParameterName(QName qname, NamespaceMap namespaces) {
        String parameter = qname.getLocalPart();
        String ns = namespaces.getAlias(qname.getNamespaceURI());

        if (parameter.equals("")) {
            // parameter name is for a namespace declaration
            parameter = "ns";

            if (ns != XMLConstants.DEFAULT_NS_PREFIX) {
                parameter += "." + ns;
            }
        } else {
            // parameter name is for a message parameter
            if (ns != XMLConstants.DEFAULT_NS_PREFIX) {
                parameter = ns + "." + parameter;
            }
        }

        return parameter;
    }

    /**
     * Encode the parameter QNames into a comma separated list of encoded parameter names.
     * 
     * @param signedFields list of parameter QNames
     * @param namespaces map of registered namespaces
     * @return comma separated list of encoded parameter names
     */
    public static String encodeSignedFields(List<QName> signedFields, NamespaceMap namespaces) {
        List<String> encodedFields = new ArrayList<String>();

        for (QName field : signedFields) {
            encodedFields.add(encodeParameterName(field, namespaces));
        }

        return StringUtils.join(encodedFields, ",");
    }

    /**
     * Decode the comma separated list of encoded parameter names into QNames.
     * 
     * @param signedFields comma separated list of encoded parameter names
     * @param namespaces namespaces map of registered namespaces
     * @return QNames of the signed fields
     */
    public static List<QName> decodeSignedFields(String signedFields, NamespaceMap namespaces) {
        List<QName> decodedFields = new ArrayList<QName>();

        if (signedFields != null) {
            for (String field : signedFields.split(",")) {
                decodedFields.add(decodeParameterName(field, namespaces));
            }
        }

        return decodedFields;
    }

    /**
     * Append the URL encoded OpenID message parameters to the query string of the provided URL.
     * 
     * @param url URL to append OpenID message parameter to
     * @param message URL encoded OpenID message parameters
     * @return URL with message parameters appended
     */
    public static URL appendMessageParameters(URL url, String message) {
        try {
            return appendMessageParameters(url.toURI(), message).toURL();
        } catch (MalformedURLException e) {
            log.error("Unable to append message parameters to URL: {}", e);
        } catch (URISyntaxException e) {
            log.error("Unable to append message parameters to URL: {}", e);
        }

        return null;
    }

    /**
     * Append the URL encoded OpenID message parameters to the query string of the provided URI.
     * 
     * @param uri URI to append OpenID message parameter to
     * @param message URL encoded OpenID message parameters
     * @return URI with message parameters appended
     */
    public static URI appendMessageParameters(URI uri, String message) {
        if (uri == null || message == null) {
            return uri;
        }

        // build new query string
        StringBuffer queryBuffer = new StringBuffer();
        if (uri.getRawQuery() != null) {
            queryBuffer.append(uri.getRawQuery());
        }
        if (queryBuffer.length() > 0 && queryBuffer.charAt(queryBuffer.length() - 1) != '&') {
            queryBuffer.append('&');
        }
        queryBuffer.append(message);

        try {
            return URIUtils.createURI(uri.getScheme(), uri.getHost(), uri.getPort(), uri.getPath(), queryBuffer
                    .toString(), uri.getFragment());
        } catch (URISyntaxException e) {
            log.error("Unable to append message parameters to URI: {}", e);
        }

        return null;
    }

    /**
     * Encode a DH public key.
     * 
     * @param publicKey DH public key to encode
     * @return encoded public key
     */
    public static String encodePublicKey(DHPublicKey publicKey) {
        return new String(Base64.encodeBase64(publicKey.getY().toByteArray()));
    }

    /**
     * Decode a DH public key.
     * 
     * @param encodedKey public key to decode
     * @param parameters DH parameters used in decoding
     * @return decoded public key
     * @throws NoSuchAlgorithmException if DH algorithm is unavailable
     * @throws InvalidKeySpecException if unable to build a valid DH key spec
     */
    public static DHPublicKey decodePublicKey(String encodedKey, DHParameterSpec parameters)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Base64.decodeBase64(encodedKey.getBytes());
        DHPublicKeySpec keySpec = new DHPublicKeySpec(new BigInteger(keyBytes), parameters.getP(), parameters.getG());
        KeyFactory keyFactory = KeyFactory.getInstance("DH");
        return (DHPublicKey) keyFactory.generatePublic(keySpec);
    }

    /**
     * Encode a DH private key.
     * 
     * @param privateKey DH private key to encode
     * @return encoded private key
     */
    public static String encodePrivateKey(DHPrivateKey privateKey) {
        return new String(Base64.encodeBase64(privateKey.getX().toByteArray()));
    }

    /**
     * Decode a DH private key.
     * 
     * @param encodedKey private key to decode
     * @param parameters DH parameters used in decoding
     * @return decoded private key
     * @throws NoSuchAlgorithmException if DH algorithm is unavailable
     * @throws InvalidKeySpecException if unable to build a valid DH key spec
     */
    public static DHPrivateKey decodePrivateKey(String encodedKey, DHParameterSpec parameters)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Base64.decodeBase64(encodedKey.getBytes());
        DHPrivateKeySpec keySpec = new DHPrivateKeySpec(new BigInteger(keyBytes), parameters.getP(), parameters.getG());
        KeyFactory keyFactory = KeyFactory.getInstance("DH");
        return (DHPrivateKey) keyFactory.generatePrivate(keySpec);
    }

    /**
     * Encode a secret key. This can be used on DH shared secrets as well as MAC keys.
     * 
     * @param key secret key to encode
     * @return encoded secret key
     */
    public static String encodeSecretKey(SecretKey key) {
        return new String(Base64.encodeBase64(key.getEncoded()));
    }

    /**
     * Decode a secret key. This can be used on DH shared secrets as well as MAC keys.
     * 
     * @param encodedKey secret key to decode
     * @param algorithm algorithm to set on the decoded key
     * @return decoded secret key
     */
    public static SecretKey decodeSecretKey(String encodedKey, String algorithm) {
        return new SecretKeySpec(Base64.decodeBase64(encodedKey.getBytes()), algorithm);
    }

}