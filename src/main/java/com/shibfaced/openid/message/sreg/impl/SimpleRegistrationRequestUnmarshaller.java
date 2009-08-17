
package com.shibfaced.openid.message.sreg.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.shibfaced.openid.message.Unmarshaller;
import com.shibfaced.openid.message.sreg.SimpleRegistrationRequest;
import com.shibfaced.openid.message.sreg.SimpleRegistration.Field;
import com.shibfaced.openid.message.sreg.SimpleRegistration.Parameter;
import com.shibfaced.openid.util.DatatypeHelper;

/**
 * Unmarshaller for a simple registration request.
 */
public class SimpleRegistrationRequestUnmarshaller implements Unmarshaller<SimpleRegistrationRequest> {

    /** {@inheritDoc} */
    public SimpleRegistrationRequest unmarshall(Map<String, String> parameters) {
        SimpleRegistrationRequestImpl request = new SimpleRegistrationRequestImpl();

        // policy URL
        try {
            URL policyURL = new URL(parameters.get(Parameter.policy_url.toString()));
            request.setPolicyURL(policyURL);
        } catch (MalformedURLException e) {
            // do nothing
        }

        // required fields
        String requiredFields = parameters.get(Parameter.required.toString());
        if (!DatatypeHelper.isEmpty(requiredFields)) {
            for (String fieldName : requiredFields.split(",")) {
                try {
                    request.getRequiredFields().add(Field.valueOf(fieldName));
                } catch (IllegalArgumentException e) {
                    // do nothing
                }
            }
        }

        // optional fields
        String optionalFields = parameters.get(Parameter.optional.toString());
        if (!DatatypeHelper.isEmpty(optionalFields)) {
            for (String fieldName : optionalFields.split(",")) {
                try {
                    request.getOptionalFields().add(Field.valueOf(fieldName));
                } catch (IllegalArgumentException e) {
                    // do nothing
                }
            }
        }

        return request;
    }
}