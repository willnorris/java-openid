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

import java.net.URL;

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.BaseMessageProviderTestCase;
import edu.internet2.middleware.openid.common.OpenIDConstants;

/**
 * Test case for creating, marshalling, and unmarshalling {@link AssociationRequest}.
 */
public class AuthenticationInteractiveRequestTest extends BaseMessageProviderTestCase {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(AuthenticationInteractiveRequestTest.class);

    /** Expected mode. */
    private String expectedMode;

    /** Expected claimed id. */
    private String expectedClaimedId;

    /** Expected identity. */
    private String expectedIdentity;

    /** Expected association handle. */
    private String expectedAssociationHandle;

    /** Expected return to URL. */
    private URL expectedReturnTo;

    /** Expected realm. */
    private String expectedRealm;

    /** Constructor. */
    public AuthenticationInteractiveRequestTest() {
        messageFile = "/data/edu/internet2/middleware/openid/message/AuthenticationInteractiveRequest.txt";
    }

    /** {@inheritDoc} */
    public void setUp() throws Exception {
        super.setUp();

        expectedMode = AuthenticationRequest.MODE_INTERACTIVE;
        expectedClaimedId = "http://example.org/";
        expectedIdentity = "http://example.com/username";
        expectedAssociationHandle = "foobar";
        expectedReturnTo = new URL("http://rp.example.com/consumer");
        expectedRealm = "http://rp.example.com/";
    }

    /** {@inheritDoc} */
    public void testMessageMarshall() {
        QName qname = new QName(OpenIDConstants.OPENID_20_NS, expectedMode);
        AuthenticationRequest request = (AuthenticationRequest) buildMessage(qname);

        request.setMode(expectedMode);
        request.setClaimedId(expectedClaimedId);
        request.setIdentity(expectedIdentity);
        request.setAssociationHandle(expectedAssociationHandle);
        request.setReturnTo(expectedReturnTo);
        request.setRealm(expectedRealm);

        assertEquals(expectedParameters, request);
    }

    /** {@inheritDoc} */
    public void testMessageUnmarshall() {
        AuthenticationRequest request = (AuthenticationRequest) unmarshallMessage(messageFile);

        String mode = request.getMode();
        assertEquals("AuthenticationRequest mode was " + mode + ", expected " + expectedMode, expectedMode, mode);

        String claimedId = request.getClaimedId();
        assertEquals("AuthenticationRequest claimed_id was " + claimedId + ", expected " + expectedClaimedId,
                expectedClaimedId, claimedId);

        String identity = request.getIdentity();
        assertEquals("AuthenticationRequest identity was " + identity + ", expected " + expectedIdentity,
                expectedIdentity, identity);

        String handle = request.getAssociationHandle();
        assertEquals("AuthenticationRequest assoc_handle was " + handle + ", expected " + expectedAssociationHandle,
                expectedAssociationHandle, handle);

        URL returnTo = request.getReturnTo();
        assertEquals("AuthenticationRequest return_to was " + returnTo.toString() + ", expected "
                + expectedReturnTo.toString(), expectedReturnTo.toString(), returnTo.toString());

        String realm = request.getRealm();
        assertEquals("AuthenticationRequest realm was " + realm + ", expected " + expectedRealm, expectedRealm, realm);
    }

}