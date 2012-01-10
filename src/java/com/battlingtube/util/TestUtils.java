package com.battlingtube.util;

/**
 * Created by IntelliJ IDEA.
 * User: eric
 * Date: Jan 12, 2009
 * Time: 8:26:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestUtils {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
