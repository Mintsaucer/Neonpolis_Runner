package com.neonpolis.game.Box2D;

import com.badlogic.gdx.math.Vector2;
import com.neonpolis.game.Enums.UserDataType;
import com.neonpolis.game.Utils.Constants;

/**
 * Created by Lauri on 21.3.2017.
 */

public class RunnerUserData extends UserData {

    private final Vector2 runningPosition = new Vector2(Constants.RUNNER_X, Constants.RUNNER_Y);
    private final Vector2 dodgePosition = new Vector2(Constants.RUNNER_DODGE_X, Constants.RUNNER_DODGE_Y);
    private Vector2 jumpingLinearImpulse;

    public RunnerUserData(float width, float height) {
        super(width,height);
        jumpingLinearImpulse = Constants.RUNNER_JUMPING_LINEAR_IMPULSE;
        userDataType = UserDataType.RUNNER;
    }

    public Vector2 getJumpingLinearImpulse() {
        return jumpingLinearImpulse;
    }

    public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse) {
        this.jumpingLinearImpulse = jumpingLinearImpulse;
    }

    public float getDodgeAngle() {
        // radians
        return (float) (-90f * (Math.PI / 180f));
    }

    public float getHitAngularImpulse() {
        return Constants.RUNNER_HIT_ANGULAR_IMPULSE;
    }

    public Vector2 getRunningPosition() {
        return runningPosition;
    }

    public Vector2 getDodgePosition() {
        return dodgePosition;
    }
}
