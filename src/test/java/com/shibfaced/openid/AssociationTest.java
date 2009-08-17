
package com.shibfaced.openid;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shibfaced.openid.association.Association.AssociationType;
import com.shibfaced.openid.association.Association.SessionType;
import com.shibfaced.openid.message.sreg.SimpleRegistration.Field;

public class AssociationTest extends TestCase {

    private static final Logger log = LoggerFactory.getLogger(AssociationTest.class);

    public void testAssociation() {
        log.info(Field.valueOf("nickname").toString());
        log.info(AssociationType.getType("HMAC-SHA1").toString());
        log.info(SessionType.getType("no-encryption").toString());
    }
}