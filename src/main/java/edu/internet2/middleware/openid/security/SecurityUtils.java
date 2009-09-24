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

package edu.internet2.middleware.openid.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Mac;
import javax.xml.namespace.QName;

import org.opensaml.xml.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.Configuration;
import edu.internet2.middleware.openid.message.Marshaller;
import edu.internet2.middleware.openid.message.MarshallingException;
import edu.internet2.middleware.openid.message.ParameterMap;
import edu.internet2.middleware.openid.message.SignableMessage;
import edu.internet2.middleware.openid.message.encoding.EncodingException;
import edu.internet2.middleware.openid.message.encoding.impl.KeyValueFormCodec;

/**
 *
 */
public class SecurityUtils {

    /** Logger. */
    private static final Logger log = LoggerFactory.getLogger(SecurityUtils.class);

    public static void signMessage(SignableMessage message, Association association) throws SecurityException {
        ParameterMap messageParameters;
        try {
            Marshaller marshaller = Configuration.getMarshallers().getMarshaller(message);
            messageParameters = marshaller.marshall(message);
        } catch (MarshallingException e) {
            throw new SecurityException("Unable to sign message", e);
        }

        String signatureData = buildSignatureData(messageParameters, message.getSignedFields());
        String signature = calculateSignature(association, signatureData);

        message.setSignature(signature);
    }

    public static String buildSignatureData(ParameterMap parameters, List<QName> signedFields) throws SecurityException {
        ParameterMap signedParameters = new ParameterMap();

        for (QName field : signedFields) {
            signedParameters.put(field, parameters.get(field));
        }

        try {
            return KeyValueFormCodec.getInstance().encode(signedParameters);
        } catch (EncodingException e) {
            throw new SecurityException("Unable to sign data", e);
        }
    }

    /**
     * Calculate signature for specified data using an Association.
     * 
     * @param association association
     * @param data data to calculate signature for
     * @return calculated signature
     */
    public static String calculateSignature(Association association, String data) throws SecurityException {
        try {
            Mac mac = Mac.getInstance(association.getMacKey().getAlgorithm());
            mac.init(association.getMacKey());

            byte[] rawHmac = mac.doFinal(data.getBytes());
            return Base64.encodeBytes(rawHmac, Base64.DONT_BREAK_LINES);
        } catch (InvalidKeyException e) {
            log.error("Unable to generate MAC - " + e.getMessage());
            throw new SecurityException("Unable to generate MAC", e);
        } catch (NoSuchAlgorithmException e) {
            log.error("Unable to generate MAC - " + e.getMessage());
            throw new SecurityException("Unable to generate MAC", e);
        }
    }

}