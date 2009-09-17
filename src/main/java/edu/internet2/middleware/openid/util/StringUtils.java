/*
 * Copyright [2009] [University Corporation for Advanced Internet Development, Inc.]
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

package edu.internet2.middleware.openid.util;

import java.util.Iterator;

/**
 * String Utilities.
 */
public final class StringUtils {

    /** Private constructor. */
    private StringUtils() {
    }

    /**
     * Join a Collection using a delimiter.
     * 
     * @param objects objects to join
     * @param delimiter String used to join objects
     * @return joined string
     */
    public static String join(Iterable objects, String delimiter) {
        StringBuffer buffer = new StringBuffer();
        Iterator<?> iterator = objects.iterator();

        while (iterator.hasNext()) {
            buffer.append(iterator.next().toString());
            if (iterator.hasNext()) {
                buffer.append(delimiter);
            }
        }

        return buffer.toString();
    }

}