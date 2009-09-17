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

package edu.internet2.middleware.openid;

import edu.internet2.middleware.openid.message.ParameterMap;

/**
 * Base test case for all OpenID tests that work with Messages.
 */
public abstract class BaseMessageProviderTestCase extends BaseTestCase {

    /** Location of file containing a single element with NO optional attributes. */
    protected String messageFile;

    /** The expected result of a marshalled message. */
    protected ParameterMap expectedParameters;

    /** {@inheritDoc} */
    protected void setUp() throws Exception {
        super.setUp();

        if (messageFile != null) {
            expectedParameters = parseMessageFile(messageFile);
        }
    }

    /** Test marshalling a message. */
    public abstract void testMessageMarshall();

    /** Test unmarshalling a message. */
    public abstract void testMessageUnmarshall();

}