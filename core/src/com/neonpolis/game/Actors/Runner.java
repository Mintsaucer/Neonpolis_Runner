package com.neonpolis.game.Actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.neonpolis.game.Box2D.RunnerUserData;
import com.neonpolis.game.Neonpolis;
import com.neonpolis.game.Screens.MenuScreen;

/**
 * Created by Lauri on 21.3.2017.
 */

public class Runner extends GameActor {

    Neonpolis game;
    private boolean jumping;
    private boolean dodging;
    private boolean hit;
    private boolean walkingLeft, walkingRight;
    public int health;

    public Runner (Body body) {
        super(body);
        health = 5;
    }

    @Override
    public RunnerUserData getUserData(){
        return (RunnerUserData) userData;
    }

    public void jump() {
        if (!(jumping || dodging || hit)) {
            body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
            jumping = true;
         }
    }

    public void moveRight() {
        if (!(dodging || hit)) {
            body.applyLinearImpulse(getUserData().getWalkingRightLinearImpulse(), body.getWorldCenter(), true);
            walkingRight = true;
        }
    }

    public void moveLeft() {
        if (!(dodging || hit)) {
            body.applyLinearImpulse(getUserData().getWalkingLeftLinearImpulse(), body.getWorldCenter(), true);
            walkingLeft = true;
        }
    }

    public void landed() {
        jumping = false;
    }

    public void dodge() {
        if (!(jumping || hit)) {
            body.setTransform(body.getPosition(), getUserData().getDodgeAngle());

            dodging = true;
        }
    }

    public void stopDodge() {
        dodging = false;
        if (!hit) {
            body.setTransform(getUserData().getRunningPosition(), 0f);
        }
    }

    public boolean isDodging() {
        return dodging;
    }

    public void hit() {
        body.applyAngularImpulse(getUserData().getHitAngularImpulse(), true);
        hit = true;
        health--;
    }

    public boolean isHit() {
        return hit;
    }
}


