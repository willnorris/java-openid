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
public class VerifyRequestTest extends BaseMessageProviderTestCase {

    /** Logger. */
    private final Logger log = LoggerFactory.getLogger(VerifyRequestTest.class);

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
    public VerifyRequestTest() {
        messageFile = "/data/edu/internet2/middleware/openid/message/VerifyRequest.txt";
    }

    /** {@inheritDoc} */
    public void setUp() throws Exception {
        super.setUp();

        expectedMode = VerifyRequest.MODE;
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
        VerifyRequest request = (VerifyRequest) buildMessage(qname);

        request.setEndpoint(expectedEndpoint);
        request.setClaimedId(expectedClaimedId);
        request.setIdentity(expectedIdentity);
        request.setReturnTo(expectedReturnTo);
        request.setResponseNonce(expectedNonce);
        request.setInvalidateHandle(expectedInvalidateHandle);
        request.setAssociationHandle(expectedAssociationHandle);
        request.getSignedFields().addAll(expectedSignedFields);
        request.setSignature(expectedSignature);

        assertEquals(expectedParameters, request);
    }

    /** {@inheritDoc} */
    public void testMessageUnmarshall() {
        VerifyRequest request = (VerifyRequest) unmarshallMessage(messageFile);

        String mode = request.getMode();
        assertEquals("VerifyRequest mode was " + mode + ", expected " + expectedMode, expectedMode, mode);

        String endpoint = request.getEndpoint();
        assertEquals("VerifyRequest endpoint was " + endpoint + ", expected " + expectedEndpoint, expectedEndpoint,
                endpoint);

        String claimedId = request.getClaimedId();
        assertEquals("VerifyRequest claimed_id was " + claimedId + ", expected " + expectedClaimedId,
                expectedClaimedId, claimedId);

        String identity = request.getIdentity();
        assertEquals("VerifyRequest identity was " + identity + ", expected " + expectedIdentity, expectedIdentity,
                identity);

        String returnTo = request.getReturnTo();
        assertEquals("VerifyRequest return_to was " + returnTo + ", expected " + expectedReturnTo, expectedReturnTo,
                returnTo);

        String nonce = request.getResponseNonce();
        assertEquals("VerifyRequest response_nonce was " + nonce + ", expected " + expectedNonce, expectedNonce, nonce);

        String invalidateHandle = request.getInvalidateHandle();
        assertEquals("VerifyRequest invalidate_handle was " + invalidateHandle + ", expected "
                + expectedInvalidateHandle, expectedInvalidateHandle, invalidateHandle);

        String handle = request.getAssociationHandle();
        assertEquals("VerifyRequest assoc_handle was " + handle + ", expected " + expectedAssociationHandle,
                expectedAssociationHandle, handle);

        List<QName> signedFields = request.getSignedFields();
        assertEquals("VerifyRequest signed was " + signedFields + ", expected " + expectedSignedFields,
                expectedSignedFields, signedFields);

        String signature = request.getSignature();
        assertEquals("VerifyRequest signature was " + signature + ", expected " + expectedSignature, expectedSignature,
                signature);
    }

}