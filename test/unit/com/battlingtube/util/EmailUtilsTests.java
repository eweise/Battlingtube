package com.battlingtube.util;

import groovy.util.GroovyTestCase;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: eric
 * Date: Feb 2, 2009
 * Time: 2:19:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class EmailUtilsTests extends GroovyTestCase {

    public void testParseEmailsIntoList() {
        String emails = "email1@none.com\nemail2@none.com";

        Set<String> list = EmailUtils.parseEmailIntoList(emails);

        assertEquals(2, list.size());
    }
}
