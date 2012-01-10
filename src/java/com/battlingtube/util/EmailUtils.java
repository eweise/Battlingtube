package com.battlingtube.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: eric
 * Date: Feb 2, 2009
 * Time: 2:17:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class EmailUtils {

    public static Set<String> parseEmailIntoList(String emails) {
        Set<String> emailSet = new HashSet<String>();
        String[] emailArray = emails.split("\n");
        for(int i = 0; i < emailArray.length; i++) {
            emailSet.add(emailArray[i].trim());
        }
        return emailSet;
    }
}
