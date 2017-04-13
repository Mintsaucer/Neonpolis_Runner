package com.neonpolis.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.neonpolis.game.Screens.PlayScreen;

/**
 * Created by Lauri on 13.4.2017.
 */

public class Enemy extends Sprite {
    public World world;
    public Body b2body;
    public PolygonShape shape;
    private Texture texture;
    public TextureRegion enemy;

public Enemy(World world, PlayScreen screen) {
    this.world = world;

    defineEnemy();

    texture = new Texture("enemy.png");
    enemy = new TextureRegion(texture, 0, 0, 165, 150);

    setBounds(0, 0 ,19, 19);
    setRegion(enemy);
    }

    private void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(275, 40);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        shape = new PolygonShape();
        shape.setAsBox(10, 10);

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("enemy");
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }
}
