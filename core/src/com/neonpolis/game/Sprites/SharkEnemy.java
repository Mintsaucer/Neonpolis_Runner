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
 * Created by Lauri on 20.4.2017.
 */

public class SharkEnemy extends Sprite {
    public World world;
    public Body b2body;
    public PolygonShape shape;
    private Texture texture;
    public TextureRegion enemy;

    public SharkEnemy(World world, PlayScreen screen) {
        this.world = world;

        defineEnemy();

        texture = new Texture("enemies.png");
        enemy = new TextureRegion(texture, 1100, 30, 750, 300);

        setRotation(-90);
        setBounds(0, 0 , 52, 35);
        setOriginCenter();
        setRegion(enemy);
    }

    private void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(920, 50);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        shape = new PolygonShape();
        shape.setAsBox(10, 17);

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("enemy");
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

    public void enemyMovement() {
        if (b2body.getLinearVelocity().y == 0)
            b2body.setLinearVelocity(0, 50);
    }
}
