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
import java.net.URISyntaxException;
import java.net.URL;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test Realm Utilities.
 */
public class RealmUtilsTest extends TestCase {

    /** Logger. */
    private static final Logger log = LoggerFactory.getLogger(RealmUtilsTest.class);

    /**
     * Test realm validity.
     */
    public void testRealmValidity() {

        // valid realms
        assertTrue(RealmUtils.isValid("http://www.example.com"));
        assertTrue(RealmUtils.isValid("http://www.example.com/"));
        assertTrue(RealmUtils.isValid("http://www.example.com:8080/"));
        assertTrue(RealmUtils.isValid("https://www.example.com"));
        assertTrue(RealmUtils.isValid("http://www.example.com/foo"));
        assertTrue(RealmUtils.isValid("http://www.example.com/foo/"));
        assertTrue(RealmUtils.isValid("http://*.example.com/"));
        assertTrue(RealmUtils.isValid("http://*.com/"));
        assertTrue(RealmUtils.isValid("http://*.www.example.com/"));

        // invalid realms
        assertFalse(RealmUtils.isValid("http://example.com/#foo"));
        assertFalse(RealmUtils.isValid("http://*example.com/"));
        assertFalse(RealmUtils.isValid("http://example.*.com/"));
        assertFalse(RealmUtils.isValid("http://*.example.*.com/"));
    }

    /**
     * Test realm generality.
     */
    public void testRealmGenerality() {
        // sufficiently specific realms
        assertFalse(RealmUtils.isOverlyGeneral("http://www.example.com"));
        assertFalse(RealmUtils.isOverlyGeneral("http://*.example.com"));
        assertFalse(RealmUtils.isOverlyGeneral("http://*.x.org"));

        // overly general realms
        assertTrue(RealmUtils.isOverlyGeneral("http://*.com"));
        assertTrue(RealmUtils.isOverlyGeneral("http://*.co.uk"));
        assertTrue(RealmUtils.isOverlyGeneral("http://*.com.sg"));
    }

    /**
     * Test creation of discovery URLs.
     * 
     * @throws MalformedURLException if url is malformed
     * @throws URISyntaxException if uri is malformed
     */
    public void testDiscoveryURL() throws MalformedURLException, URISyntaxException {
        URL expected;
        URL actual;

        expected = new URL("http://example.com");
        actual = RealmUtils.toDiscoveryURL("http://example.com");
        assertEquals(expected.toString(), actual.toString());

        expected = new URL("http://www.example.com/");
        actual = RealmUtils.toDiscoveryURL("http://*.example.com/");
        assertEquals(expected.toString(), actual.toString());

        expected = new URL("https://www.example.com:8443/path?query#frag");
        actual = RealmUtils.toDiscoveryURL("https://*.example.com:8443/path?query#frag");
        assertEquals(expected.toString(), actual.toString());
    }

    /**
     * Test realm matching to return to URLs.
     */
    public void testRealmMatching() {
        // matching realms
        assertTrue(RealmUtils.matchesReturnTo("http://example.com/", "http://example.com/"));
        assertTrue(RealmUtils.matchesReturnTo("http://example.com/", "http://example.com/path"));
        assertTrue(RealmUtils.matchesReturnTo("http://example.com/", "http://example.com:80/"));
        assertTrue(RealmUtils.matchesReturnTo("https://example.com:443/", "https://example.com/"));
        assertTrue(RealmUtils.matchesReturnTo("http://example.com/", "http://example.com/path?query"));
        assertTrue(RealmUtils.matchesReturnTo("http://example.com/foo", "http://example.com/foo"));
        assertTrue(RealmUtils.matchesReturnTo("http://example.com/foo", "http://example.com/foo/bar"));
        assertTrue(RealmUtils.matchesReturnTo("http://*.example.com/", "http://example.com/"));
        assertTrue(RealmUtils.matchesReturnTo("http://*.example.com/", "http://www.example.com/"));

        // non-matching realms
        assertFalse(RealmUtils.matchesReturnTo("https://example.com/", "http://example.com/"));
        assertFalse(RealmUtils.matchesReturnTo("http://example.com/", "http://example.com:8080/"));
        assertFalse(RealmUtils.matchesReturnTo("http://example.com/", "http://www.example.com/"));
        assertFalse(RealmUtils.matchesReturnTo("http://www.example.com/", "http://example.com/"));
        assertFalse(RealmUtils.matchesReturnTo("http://*.www.example.com/", "http://example.com/"));
        assertFalse(RealmUtils.matchesReturnTo("http://*.example.com/", "http://someexample.com/"));
        assertFalse(RealmUtils.matchesReturnTo("http://example.com/foo", "http://example.com/"));
        assertFalse(RealmUtils.matchesReturnTo("http://example.com/foo", "http://www.example.com/foobar"));
    }

}