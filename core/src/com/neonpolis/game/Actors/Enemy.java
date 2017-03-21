package com.neonpolis.game.Actors;


import com.badlogic.gdx.physics.box2d.Body;
import com.neonpolis.game.Box2D.EnemyUserData;


/**
 * Created by Lauri on 21/03/2017.
 */

public class Enemy extends GameActor {

    public Enemy(Body body) {
        super(body);
    }

    @Override
    public EnemyUserData getUserData() {
        return (EnemyUserData) userData;
    }

    @Override
    public void act (float delta) {
        super.act(delta);
        body.setLinearVelocity(getUserData().getLinearVelocity());
    }
}
