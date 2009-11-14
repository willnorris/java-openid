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

package edu.internet2.middleware.openid.message.validation;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import javax.crypto.SecretKey;
import javax.xml.namespace.QName;

import edu.internet2.middleware.openid.Configuration;
import edu.internet2.middleware.openid.common.OpenIDConstants.AssociationType;
import edu.internet2.middleware.openid.common.OpenIDConstants.Parameter;
import edu.internet2.middleware.openid.message.SignableMessage;
import edu.internet2.middleware.openid.util.DatatypeHelper;

/**
 * Validation Utilities.
 */
public final class ValidationUtils {

    /**
     * Array of message parameters that must be used in calculating the message signature.
     */
    public static final QName[] REQUIRED_SIGNED_FIELDS = { Parameter.op_endpoint.QNAME, Parameter.return_to.QNAME,
            Parameter.response_nonce.QNAME, Parameter.assoc_handle.QNAME, };

    /** Constructor. */
    private ValidationUtils() {
    }

    /**
     * Validate the association handle is of allowed length and character set.
     * 
     * @param handle association handle to validate
     * @throws ValidationException thrown if association handle is not valid
     */
    public static void validateAssociationHandle(String handle) throws ValidationException {
        if (handle.length() > 255) {
            throw new ValidationException("Association handle must be 255 characters or less");
        }

        if (handle.matches("[\\x21-\\x7E]+")) {
            throw new ValidationException(
                    "Association handle must contain only ASCII printable non-whitespace characters");
        }
    }

    /**
     * Validate that the MAC key is of the appropriate length for the given association type.
     * 
     * @param associationType association type to check against
     * @param key MAC key to validate
     * @throws ValidationException thrown if the MAC key is the wrong size for the association type
     */
    public static void validateMacKeyLength(AssociationType associationType, SecretKey key) throws ValidationException {
        if (associationType.getKeySize() != (key.getEncoded().length * 8)) {
            throw new ValidationException("Association type of " + associationType.toString()
                    + " must have a MAC key of length " + associationType.getKeySize());
        }
    }

    /**
     * Validate the nonce is of allowed length and format.
     * 
     * @param nonce nonce to validate
     * @throws ValidationException thrown if nonce is not valid
     */
    public static void validateNonce(String nonce) throws ValidationException {
        if (nonce.length() > 255) {
            throw new ValidationException("Nonce must be 255 characters or less");
        }

        if (nonce.matches("[\\x21-\\x7E]+")) {
            throw new ValidationException("Nonce must contain only ASCII printable non-whitespace characters");
        }

        try {
            String timestamp = nonce.substring(0, 20);
            Configuration.getInternetDateFormat().parse(timestamp);
        } catch (ParseException e) {
            throw new ValidationException("Nonce must start with a timestamp in Internet date format (value was '"
                    + nonce + "')");
        }
    }

    /**
     * Validate the signature related parameters. The signed fields and signature parameters must be present, and the
     * signed fields list must contain the requisite parameters.
     * 
     * @param message response to validate
     * @param requiredSignedFields field names that must be present in the list of signed fields
     * @throws ValidationException if the signature related parameters are not valid
     */
    public static void validateSignature(SignableMessage message, Set<QName> requiredSignedFields)
            throws ValidationException {
        if (DatatypeHelper.isEmpty(message.getSignature())) {
            throw new ValidationException("Signable message must define signature");
        }

        List<QName> signedFields = message.getSignedFields();
        if (signedFields.isEmpty()) {
            throw new ValidationException("Signable messagen must define list of signed fields");
        }

        if (!signedFields.containsAll(requiredSignedFields)) {
            throw new ValidationException("Signable message signed fields list must contain all of the "
                    + "required fields: " + requiredSignedFields.toString());
        }
    }
}