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

package edu.internet2.middleware.openid.common;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Date Formatter representing Internet Date Format as specified by RFC 3339.
 * 
 * @see http://tools.ietf.org/html/rfc3339#section-5.6
 */
public class InternetDateFormat extends SimpleDateFormat {

    /** Serial Version UID. */
    private static final long serialVersionUID = -9091480984026853870L;

    /** Internet Date/Time format. */
    private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    /** GMT TimeZone. */
    private static final TimeZone TIMEZONE = TimeZone.getTimeZone("GMT");

    /** Constructor. */
    public InternetDateFormat() {
        super(FORMAT);
        setTimeZone(TIMEZONE);
    }

}