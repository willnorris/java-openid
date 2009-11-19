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

package edu.internet2.middleware.openid.security.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.Configuration;
import edu.internet2.middleware.openid.security.NonceValidator;

/**
 * Nonce validator implementation that stores nonces in memory to detect replays.
 */
public class InMemoryNonceValidator implements NonceValidator {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(InMemoryNonceValidator.class);

    /** Nonce lifetime. */
    private int lifetime;

    /** Map of nonces that have already been checked, keyed on the nonce issuer. */
    private Map<String, Set<String>> nonces;

    /** Constructor. */
    public InMemoryNonceValidator() {
        nonces = new HashMap<String, Set<String>>();
    }

    /** {@inheritDoc} */
    public boolean isValid(String issuer, String nonce) {
        if (nonces.containsKey(issuer) && nonces.get(issuer).contains(nonce)) {
            log.info("Nonce '{}' has already been validated.", nonce);
            return false;
        }

        if (nonce.length() < 20) {
            log.info("Nonce '{}' not properly formatted", nonce);
            return false;
        }

        Date nonceDate;
        try {
            nonceDate = Configuration.getInternetDateFormat().parse(nonce.substring(0, 20));
        } catch (ParseException e) {
            log.info("Nonce '{}' not properly formatted", nonce);
            return false;
        }

        Calendar oldestValid = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        oldestValid.add(Calendar.SECOND, getLifetime() * -1);
        if (nonceDate.before(oldestValid.getTime())) {
            log.info("Nonce '{}' is expired.  Last valid issuance time was {}", nonce, Configuration
                    .getInternetDateFormat().format(oldestValid.getTime()));
            return false;
        }

        // add nonce to map
        if (!nonces.containsKey(issuer)) {
            nonces.put(issuer, new HashSet<String>());
        }
        nonces.get(issuer).add(nonce);

        return true;
    }

    /** {@inheritDoc} */
    public int getLifetime() {
        return lifetime;
    }

    /** {@inheritDoc} */
    public void setLifetime(int newLifetime) {
        lifetime = newLifetime;
    }

}