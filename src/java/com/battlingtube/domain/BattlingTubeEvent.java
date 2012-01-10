package com.battlingtube.domain;

import org.springframework.context.ApplicationEvent;


/**
 * Created by IntelliJ IDEA.
 * User: eric
 * Date: Jan 26, 2009
 * Time: 8:40:48 AM
 * To change this template use File | Settings | File Templates.
 */

public class BattlingTubeEvent extends ApplicationEvent {

    BattlingTubeEvent(Object o) {
        super(o);
    }
}
