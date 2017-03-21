package com.neonpolis.game.Box2D;

import com.badlogic.gdx.math.Vector2;
import com.neonpolis.game.Utils.Constants;

/**
 * Created by Lauri on 21/03/2017.
 */

public class EnemyUserData extends UserData {

    private Vector2 linearVelocity;

    public EnemyUserData (float width, float height) {
        super(width, height);
        userDataType = userDataType.ENEMY;
        linearVelocity = Constants.ENEMY_LINEAR_VELOCITY;
    }

    public void setLinearVelocity(Vector2 linearVelocty) {
        this.linearVelocity = linearVelocty;
    }

    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }
}
