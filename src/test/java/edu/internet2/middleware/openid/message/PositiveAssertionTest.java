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

import java.util.Arrays;
import java.util.List;

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.internet2.middleware.openid.BaseMessageProviderTestCase;
import edu.internet2.middleware.openid.common.OpenIDConstants;
import edu.internet2.middleware.openid.common.OpenIDConstants.Parameter;

/**
 * Test case for creating, marshalling, and unmarshalling {@link AssociationRequest}.
 */
public class PositiveAssertionTest extends BaseMessageProviderTestCase {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(PositiveAssertionTest.class);

    /** Expected mode. */
    private String expectedMode;

    /** Expected OP Endpoint. */
    private String expectedEndpoint;

    /** Expected claimed id. */
    private String expectedClaimedId;

    /** Expected identity. */
    private String expectedIdentity;

    /** Expected association handle. */
    private String expectedAssociationHandle;

    /** Expected return to URL. */
    private String expectedReturnTo;

    /** Expected response nonce. */
    private String expectedNonce;

    /** Expected invalidate handle. */
    private String expectedInvalidateHandle;

    /** Expected list of signed fields. */
    private List<QName> expectedSignedFields;

    /** Expected signature. */
    private String expectedSignature;

    /** Constructor. */
    public PositiveAssertionTest() {
        messageFile = "/data/edu/internet2/middleware/openid/message/PositiveAssertion.txt";
    }

    /** {@inheritDoc} */
    public void setUp() throws Exception {
        super.setUp();

        expectedMode = PositiveAssertion.MODE;
        expectedEndpoint = "http://openid.example.com/server";
        expectedClaimedId = "http://example.org/";
        expectedIdentity = "http://example.com/username";
        expectedReturnTo = "http://rp.example.com/consumer";
        expectedNonce = "123";
        expectedInvalidateHandle = "old-handle";
        expectedAssociationHandle = "new-handle";
        expectedSignedFields = Arrays.asList(new QName[] { Parameter.mode.QNAME, Parameter.claimed_id.QNAME,
                Parameter.identity.QNAME, });
        expectedSignature = "2Vcbt2I3MYZuYe91ouJ4mLBX+YkcLiemOcP";
    }

    /** {@inheritDoc} */
    public void testMessageMarshall() {
        QName qname = new QName(OpenIDConstants.OPENID_20_NS, expectedMode);
        PositiveAssertion response = (PositiveAssertion) buildMessage(qname);

        response.setEndpoint(expectedEndpoint);
        response.setClaimedId(expectedClaimedId);
        response.setIdentity(expectedIdentity);
        response.setReturnTo(expectedReturnTo);
        response.setResponseNonce(expectedNonce);
        response.setInvalidateHandle(expectedInvalidateHandle);
        response.setAssociationHandle(expectedAssociationHandle);
        response.getSignedFields().addAll(expectedSignedFields);
        response.setSignature(expectedSignature);

        assertEquals(expectedParameters, response);
    }

    /** {@inheritDoc} */
    public void testMessageUnmarshall() {
        PositiveAssertion response = (PositiveAssertion) unmarshallMessage(messageFile);

        String mode = response.getMode();
        assertEquals("PositiveAssertion mode was " + mode + ", expected " + expectedMode, expectedMode, mode);

        String endpoint = response.getEndpoint();
        assertEquals("PositiveAssertion endpoint was " + endpoint + ", expected " + expectedEndpoint, expectedEndpoint,
                endpoint);

        String claimedId = response.getClaimedId();
        assertEquals("PositiveAssertion claimed_id was " + claimedId + ", expected " + expectedClaimedId,
                expectedClaimedId, claimedId);

        String identity = response.getIdentity();
        assertEquals("PositiveAssertion identity was " + identity + ", expected " + expectedIdentity, expectedIdentity,
                identity);

        String returnTo = response.getReturnTo();
        assertEquals("PositiveAssertion return_to was " + returnTo + ", expected " + expectedReturnTo,
                expectedReturnTo, returnTo);

        String nonce = response.getResponseNonce();
        assertEquals("PositiveAssertion response_nonce was " + nonce + ", expected " + expectedNonce, expectedNonce,
                nonce);

        String invalidateHandle = response.getInvalidateHandle();
        assertEquals("PositiveAssertion invalidate_handle was " + invalidateHandle + ", expected "
                + expectedInvalidateHandle, expectedInvalidateHandle, invalidateHandle);

        String handle = response.getAssociationHandle();
        assertEquals("PositiveAssertion assoc_handle was " + handle + ", expected " + expectedAssociationHandle,
                expectedAssociationHandle, handle);

        List<QName> signedFields = response.getSignedFields();
        assertEquals("PositiveAssertion signed was " + signedFields + ", expected " + expectedSignedFields,
                expectedSignedFields, signedFields);

        String signature = response.getSignature();
        assertEquals("PositiveAssertion signature was " + signature + ", expected " + expectedSignature,
                expectedSignature, signature);
    }

}