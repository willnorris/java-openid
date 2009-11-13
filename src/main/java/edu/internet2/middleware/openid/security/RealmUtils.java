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

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Realm related utilities.
 */
public final class RealmUtils {

    /** Logger. */
    private static final Logger log = LoggerFactory.getLogger(RealmUtils.class);

    /**
     * Pattern to match wildcard realms that contain only a top level domain (TLD).
     */
    private static final Pattern REALM_TLD_PATTERN = Pattern.compile("\\*\\.[^\\.]+");

    /**
     * Pattern to match wildcard realms that contain only a country code second level domain (ccSLD). This will match
     * against any two letter ccSLD, as well as commonly used ccSLDs such as com, net, org, gov, mil, and edu.
     * 
     * @see http://www.icann.org/en/tlds/agreements/unsponsored/registry-agmt-appk-26apr01.htm
     * @see https://wiki.mozilla.org/TLD_List
     */
    private static final Pattern REALM_CCSLD_PATTERN = Pattern
            .compile("\\*\\.(com|net|org|gov|mil|edu|[a-z]{2})\\.[a-z]{2}");

    /** Constructor. */
    protected RealmUtils() {
    }

    /**
     * Verify that the given realm matches the given return-to URL.
     * 
     * @param realmString realm string to match
     * @param returnToString return-to string to match
     * @return true if the return-to matches the realm, false otherwise
     */
    public static boolean matchesReturnTo(String realmString, String returnToString) {
        URL returnTo;
        URL realm;

        try {
            returnTo = new URL(returnToString);
        } catch (MalformedURLException e) {
            log.error("ReturnTo is not a valid URL: {}", returnToString);
            return false;
        }

        try {
            realm = new URL(realmString);
        } catch (MalformedURLException e) {
            log.error("Realm is not a valid URL: {}", realmString);
            return false;
        }

        if (!realm.getProtocol().equalsIgnoreCase(returnTo.getProtocol())) {
            log.warn("Realm scheme does not match Return To: {}, {}", realm, returnTo);
            return false;
        }

        if (!portsMatch(realm, returnTo)) {
            log.warn("Realm port does not match Return To: {}, {}", realm, returnTo);
            return false;
        }

        if (!hostsMatch(realm, returnTo)) {
            log.warn("Realm host does not match Return To: {}, {}", realm, returnTo);
            return false;
        }

        if (!pathsMatch(realm, returnTo)) {
            log.warn("Realm path does not match Return To: {}, {}", realm, returnTo);
            return false;
        }

        return true;
    }

    /**
     * Check if the ports of the two provided URLs match. This considers the default port for the protocols of the
     * provided URLs when determining a match.
     * 
     * @param realm realm to compare
     * @param returnTo return-to to compare
     * @return true if the ports of the two URLs match
     */
    protected static boolean portsMatch(URL realm, URL returnTo) {
        int realmPort = realm.getPort();
        if (realmPort == -1) {
            realmPort = realm.getDefaultPort();
        }

        int returnToPort = returnTo.getPort();
        if (returnToPort == -1) {
            returnToPort = returnTo.getDefaultPort();
        }

        return realmPort == returnToPort;
    }

    /**
     * Check if the hostname of the return-to URL matches the realm. If the realm contains a wildcard, then the return
     * to hostname must be a sub-domain of the non-wildcarded realm hostname. Otherwise, it must be an exact match.
     * 
     * @param realm realm URL to compare
     * @param returnTo return-to URL to compare
     * @return true if the hostnames of the two URLs match
     */
    protected static boolean hostsMatch(URL realm, URL returnTo) {
        if (realm.getHost().startsWith("*.")) {
            String returnToHost = returnTo.getHost().toLowerCase();
            String realmHost = realm.getHost().toLowerCase().substring(2);
            return returnToHost.equals(realmHost) || returnToHost.endsWith("." + realmHost);
        } else {
            return realm.getHost().equalsIgnoreCase(returnTo.getHost());
        }
    }

    /**
     * Check if the path of the return-to URL matches, or is a sub-directory of, the realm's path.
     * 
     * @param realm realm URL to compare
     * @param returnTo return-to URL to compare
     * @return true if the paths of the two URLs match
     */
    protected static boolean pathsMatch(URL realm, URL returnTo) {
        String realmPath = realm.getPath();
        if (!realmPath.endsWith("/")) {
            realmPath += "/";
        }

        String returnToPath = returnTo.getPath();
        if (!returnToPath.endsWith("/")) {
            returnToPath += "/";
        }

        return returnToPath.startsWith(realmPath);
    }

    /**
     * Verify that the given realm is valid.
     * 
     * @param realmString realm to validate
     * @return true if the realm is valid, false otherwise
     */
    public static boolean isValid(String realmString) {
        URL realm;

        try {
            realm = new URL(realmString);
        } catch (MalformedURLException e) {
            log.warn("Realm is not a valid URL: {}", realmString);
            return false;
        }

        if (realm.getRef() != null) {
            log.warn("Realm MUST NOT contain a fragment: {}", realm);
            return false;
        }

        if (realm.getAuthority().contains("*")) {
            if (realm.getAuthority().indexOf("*") != 0) {
                log.warn("Realm wildcard may only appear at beginning of authority: {}", realm);
                return false;
            }

            if (realm.getAuthority().indexOf("*.") != 0) {
                log.warn("Realm wildcard missing period '*.': {}", realm);
                return false;
            }

            if (realm.getAuthority().lastIndexOf("*") != 0) {
                log.warn("Realm may contain only a single wildcard: {}", realm);
                return false;
            }
        }

        return true;
    }

    /**
     * Check if the given wildcard realm is overly general. For example, it includes only a top level domain (like
     * *.com), or a country code second level domain (like *.co,uk). An overly general realm is not necessarily invalid,
     * but an OpenID Provider may choose to reject it or warn the user of the potential dangers.
     * 
     * It should be noted that this function <b>will</b> result in some false positives such as for "http://*.tr.im/".
     * 
     * @param realmString realm to check
     * @return true if the realm overly general, false if the realm is okay
     */
    public static boolean isOverlyGeneral(String realmString) {
        try {
            URL realm = new URL(realmString);
            if (realm.getAuthority().contains("*.")) {
                if (REALM_TLD_PATTERN.matcher(realm.getAuthority()).matches()) {
                    log.info("Wildcard realm contains only a top level domain: {}", realm);
                    return true;
                } else if (REALM_CCSLD_PATTERN.matcher(realm.getAuthority()).matches()) {
                    log.info("Wildcard realm contains a generic country code second level domain: {}", realm);
                    return true;
                }
            }
        } catch (MalformedURLException e) {
            log.error("Unable to determine generality of realm, as it is not a valid URL");
        }

        return false;
    }

    /**
     * Convert the realm string into a URL for relying-party metadata discovery. A leading wildcard in the realm
     * authority will be converted to 'www'.
     * 
     * @param realmString realm string to convert
     * @return URL for relying party discovery, or null if unable to convert into discovery URL
     */
    public static URL toDiscoveryURL(String realmString) {

        try {
            URL realm = new URL(realmString);

            if (realm.getAuthority().contains("*")) {
                String authority = realm.getAuthority().replaceFirst("\\*.", "www.");
                URI discoveryURI = new URI(realm.getProtocol(), authority, realm.getPath(), realm.getQuery(), realm
                        .getRef());
                return discoveryURI.toURL();
            } else {
                return realm;
            }
        } catch (MalformedURLException e) {
            log.error("Cannot convert realm into discovery URL, realm is not a valid URL.");
        } catch (URISyntaxException e) {
            log.error("Cannot convert realm into discovery URL, generated discovery URI is not valid.");
        }

        return null;
    }

}