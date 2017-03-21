package com.neonpolis.game.Actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.neonpolis.game.Box2D.GroundUserData;

/**
 * Created by Lauri on 21.3.2017.
 */

public class Ground extends GameActor {

    public Ground (Body body) {
        super(body);
    }

    // @Override
    public GroundUserData getUserData() {
        return (GroundUserData) userData;
    }
}
