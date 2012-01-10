package com.battlingtube.domain;

/**
 * Created by IntelliJ IDEA.
 * User: eric
 * Date: Jan 27, 2009
 * Time: 10:48:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class BattleEvent extends BattlingTubeEvent {
    public enum Action {
        created, started, finished, cancelled, roundChanged
    }

    Action action;

    public BattleEvent(Object source, Action action) {
        super(source);
        this.action = action;
    }
}
