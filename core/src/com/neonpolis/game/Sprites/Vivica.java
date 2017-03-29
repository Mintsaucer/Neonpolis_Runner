package com.neonpolis.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.neonpolis.game.Screens.PlayScreen;
import com.neonpolis.game.Utils.Constants;

/**
 * Created by Lauri on 21.3.2017.
 */

public class Vivica extends Sprite {
    public enum State {FALLING, JUMPING, STANDING, RUNNING};

    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion stand;
    private Animation run;
    private Animation jump;
    private float stateTimer;
    private boolean runningRight;

    public Vivica(World world, PlayScreen screen) {
        this.world = world;

        defineVivica();

        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        //setRegion(getFrame(dt));
    }

    public void defineVivica() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(30, 30);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        b2body.setGravityScale(5);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(7);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

    public State getState() {
        if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
            return State.JUMPING;
        else if(b2body.getLinearVelocity().y < 0 )
            return State.FALLING;
        else if (b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANDING;
    }
}
