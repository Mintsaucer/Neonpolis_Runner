package com.neonpolis.game.Box2D;

import com.neonpolis.game.Enums.UserDataType;

/**
 * Created by Lauri on 21.3.2017.
 */

public abstract class UserData {

    protected UserDataType userDataType;

    public UserData() {

    }

    public UserDataType getUserDataType() {
        return userDataType;
    }
}
