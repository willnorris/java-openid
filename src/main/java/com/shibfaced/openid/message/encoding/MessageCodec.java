
package com.shibfaced.openid.message.encoding;

import java.util.Map;

/**
 * Encodes and decodes a map of OpenID message parameters into a transport specific format.
 * 
 * @param <EncodedType> object type the codec produces
 */
public interface MessageCodec<EncodedType> {

    /**
     * Decode the message.
     * 
     * @param encoded encoded string
     * @return map of parameters
     */
    public Map<String, String> decode(EncodedType encoded);

    /**
     * Encode the message.
     * 
     * @param parameters parameters to encode
     * @return encoded format
     */
    public EncodedType encode(Map<String, String> parameters);

}