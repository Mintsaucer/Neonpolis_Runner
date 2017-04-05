package com.neonpolis.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.neonpolis.game.Neonpolis;
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
    public PolygonShape shape;
    public TextureRegion vivicaStand, vivicaRun, vivicaJump;
    public Texture texture;
    private Animation run;
    private Animation jump;
    private float stateTimer;
    private boolean runningRight;
    public boolean jumping;
    public boolean dodging;

    public Vivica(World world, PlayScreen screen) {
        this.world = world;

        // Create player body type, size, etc...
        defineVivica();
        texture = new Texture("vivica.png");
        vivicaStand = new TextureRegion(texture, 1, 0, 50, 166);
        vivicaRun = new TextureRegion(texture, 50, 10, 170,156);
        vivicaJump = new TextureRegion(texture, 220, 10, 143,146);

        setBounds(0, 0 ,18, 41);
        setRegion(vivicaStand);

        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        //setRegion(getFrame(dt));
    }

    public void landed() {
        jumping = false;
    }

    public void jump() {
        if (!(jumping && dodging)) {
            //b2body.applyLinearImpulse(new Vector2(0, 200), b2body.getWorldCenter(), true);
            b2body.setLinearVelocity(0,220);
            jumping = true;
        }
    }

    public void defineVivica() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(30, 25);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        b2body.setGravityScale(5);

        FixtureDef fdef = new FixtureDef();
        shape = new PolygonShape();
        shape.setAsBox(9,20);

        fdef.shape = shape;
        fdef.friction = 0.7f;
        b2body.createFixture(fdef).setUserData("vivica");
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

    public void dodge() {
        if (!(jumping && dodging)) {
            b2body.setTransform(b2body.getPosition(), (float) (-90f * (Math.PI / 180f)));
            dodging = true;
        }
    }

    public void stopDodge() {
        dodging = false;
            b2body.setTransform(b2body.getPosition(), 0f);
    }
}
