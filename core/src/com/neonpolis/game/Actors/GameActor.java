package com.neonpolis.game.Actors;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.neonpolis.game.Box2D.UserData;

/**
 * Created by Lauri on 21.3.2017.
 */

public abstract class GameActor extends Actor {

    public Body body;
    protected UserData userData;

    public GameActor(Body body) {
        this.body = body;
        this.userData = (UserData) body.getUserData();
    }
        public abstract UserData getUserData();
}
