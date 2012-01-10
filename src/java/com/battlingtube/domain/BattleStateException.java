package com.battlingtube.domain;

import com.battlingtube.util.ApplicationException;

public class BattleStateException extends ApplicationException {

    public BattleStateException(String currentState, String newState) {
        super("cannot move from "+currentState + " to state "+newState);   
    }

}
