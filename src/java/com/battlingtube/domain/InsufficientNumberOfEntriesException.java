package com.battlingtube.domain;

import com.battlingtube.util.ApplicationException;

/**
 * Created by IntelliJ IDEA.
 * User: eric
 * Date: Dec 2, 2008
 * Time: 10:00:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class InsufficientNumberOfEntriesException extends ApplicationException {

    public InsufficientNumberOfEntriesException(int numberOfEntries) {
        super("Could not start battle with " + numberOfEntries + " entries");
    }
}
