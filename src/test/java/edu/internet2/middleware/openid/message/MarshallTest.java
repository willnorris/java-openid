
package edu.internet2.middleware.openid.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import edu.internet2.middleware.openid.message.Marshaller;
import edu.internet2.middleware.openid.message.Unmarshaller;
import edu.internet2.middleware.openid.message.ax.FetchResponse;
import edu.internet2.middleware.openid.message.ax.impl.FetchResponseImpl;
import edu.internet2.middleware.openid.message.ax.impl.FetchResponseMarshaller;
import edu.internet2.middleware.openid.message.encoding.MessageCodec;
import edu.internet2.middleware.openid.message.encoding.impl.KeyValueFormCodec;
import edu.internet2.middleware.openid.message.encoding.impl.URLFormCodec;
import edu.internet2.middleware.openid.message.sreg.SimpleRegistration;
import edu.internet2.middleware.openid.message.sreg.SimpleRegistrationRequest;
import edu.internet2.middleware.openid.message.sreg.SimpleRegistration.Field;
import edu.internet2.middleware.openid.message.sreg.impl.SimpleRegistrationRequestMarshaller;
import edu.internet2.middleware.openid.message.sreg.impl.SimpleRegistrationRequestUnmarshaller;

/**
 * Marshall Tests.
 */
public class MarshallTest extends TestCase {

    /**
     * Test simple registration marshalling.
     */
    public void testSimpleRegistration() {
        String queryString = "required=fullname,email&optional=gender,country,height";
        MessageCodec codec = new URLFormCodec();
        Unmarshaller<SimpleRegistrationRequest> unmarshaller = new SimpleRegistrationRequestUnmarshaller();
        Marshaller<SimpleRegistrationRequest> marshaller = new SimpleRegistrationRequestMarshaller();

        // build request
        SimpleRegistrationRequest request = unmarshaller.unmarshall(codec.decode(queryString));

        // check assertions
        assertEquals(SimpleRegistration.SREG_11_NS, request.getNamespace());

        assertEquals(2, request.getRequiredFields().size());
        assertTrue(request.getRequiredFields().contains(Field.valueOf("fullname")));
        assertTrue(request.getRequiredFields().contains(Field.valueOf("email")));

        assertEquals(2, request.getOptionalFields().size());
        assertTrue(request.getOptionalFields().contains(Field.valueOf("gender")));
        assertTrue(request.getOptionalFields().contains(Field.valueOf("country")));
        try {
            assertFalse(request.getOptionalFields().contains(Field.valueOf("height")));
        } catch (IllegalArgumentException e) {
            // do nothing - expected
        }

        //assertEquals(queryString, codec.encode(marshaller.marshall(request)));

    }

    /**
     * Test attribute exchange marshalling.
     */
    public void testAttributeExchange() {
        FetchResponseImpl response = new FetchResponseImpl();
        Marshaller<FetchResponse> marshaller = new FetchResponseMarshaller();
        MessageCodec codec = new KeyValueFormCodec();

        List<String> names = Arrays.asList(new String[] { "George Burdell" });
        response.getAttributes().put("http://axschema.org/namePerson/friendly", names);

        List<String> emails = new ArrayList<String>();
        emails.add("george@burdell.name");
        emails.add("gpburdell@gmail.com");

        response.getAttributes().put("http://axschema.org/contact/email", emails);

        System.out.println(codec.encode(marshaller.marshall(response)));
    }

}