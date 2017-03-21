package com.neonpolis.game.Utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.neonpolis.game.Box2D.UserData;
import com.neonpolis.game.Enums.UserDataType;

/**
 * Created by Lauri on 21.3.2017.
 */

public class BodyUtils {

    public static boolean bodyIsRunner(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.RUNNER;
    }

    public static boolean bodyIsGround(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.GROUND;
    }
}
