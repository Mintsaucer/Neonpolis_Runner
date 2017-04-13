package com.neonpolis.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.neonpolis.game.Neonpolis;
import com.neonpolis.game.Screens.GameOverScreen;
import com.neonpolis.game.Screens.PlayScreen;
import com.neonpolis.game.Utils.Constants;

/**
 * Created by Lauri on 21.3.2017.
 */

public class Vivica extends Sprite {

    public World world;
    public Body b2body;
    public PolygonShape shape;
    public TextureRegion vivicaStand, vivicaRun, vivicaRun2, vivicaJump, vivicaSlide;
    public Texture texture;
    public Animation run;
    public boolean jumping;
    public boolean dodging;
    private Neonpolis game;

    private Sound jumpSound = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/jump.ogg"));
    private Sound runSound = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/run.ogg"));
    private Sound landSound = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/landing.ogg"));
    private Sound slideSound = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/slide.ogg"));

    public int health;

    public Vivica(World world, PlayScreen screen, Neonpolis game) {
        this.world = world;
        this.game = game;

        health = 3;

        // Create player body type, size, etc...
        defineVivica();
        texture = new Texture("vivica_moves.png");
        vivicaStand = new TextureRegion(texture, 20, 10, 50, 173);
        vivicaRun = new TextureRegion(texture, 77, 17, 170, 156);
        vivicaRun2 = new TextureRegion(texture, 240, 17, 170, 156);
        vivicaJump = new TextureRegion(texture, 460, 30, 140, 146);
        //vivicaSlide = new TextureRegion(texture, 385, 10, 60, 220);

        setBounds(0, 0 ,17, 32);
        setRegion(vivicaStand);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        runSound.play(1.7f);
        //setRegion(getFrame(dt));
    }

    public void landed() {
        setRegion(vivicaRun);
        setBounds(0, 0 ,18, 32);
        jumping = false;
        landSound.play();
    }

    public void jump() {
        if (!(jumping && dodging)) {
            //b2body.applyLinearImpulse(new Vector2(b2body.getLinearVelocity().x, 270f), b2body.getWorldCenter(), true);
            b2body.setLinearVelocity(b2body.getLinearVelocity().x, 400);
            jumping = true;
            jumpSound.play(2.0f);
        }
    }

    public void defineVivica() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(30, 45);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        b2body.setGravityScale(6.5f);

        FixtureDef fdef = new FixtureDef();
        shape = new PolygonShape();
        shape.setAsBox(7, 15);

        fdef.shape = shape;
        fdef.friction = 0.5f;
        b2body.createFixture(fdef).setUserData("vivica");
    }

    public void dodge() {
        if (!(jumping && dodging)) {
            b2body.setTransform(b2body.getPosition(), (float) (-90f * (Math.PI / 180f)));
            dodging = true;
            slideSound.play();
        }
    }

    public void stopDodge() {
        dodging = false;
            b2body.setTransform(b2body.getPosition(), 0f);
    }

    public void enemyHit() {
        health--;

        if (health == 0)
            game.setScreen(new GameOverScreen(game));
    }
}


