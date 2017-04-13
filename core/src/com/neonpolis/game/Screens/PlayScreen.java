package com.neonpolis.game.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.neonpolis.game.Neonpolis;
import com.neonpolis.game.Scenes.Hud;
import com.neonpolis.game.Sprites.Enemy;
import com.neonpolis.game.Sprites.Vivica;
import com.neonpolis.game.Utils.B2WorldCreator;


/**
 * Created by nikom on 20.3.2017.
 */

public class PlayScreen implements Screen, InputProcessor, ContactListener {

    private final B2WorldCreator worldcreator;
    //private GameStage stage;
    private Neonpolis game;
    private Hud hud;
    private Music music;
    private Vivica player;
    private Enemy enemy;

    private OrthographicCamera gamecam;

    private TiledMap map;
    private TmxMapLoader mapLoader;
    OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Vector2 lastTouch = new Vector2();

    private Sound deathSound = Gdx.audio.newSound(Gdx.files.internal("audio/sounds/die.ogg"));
    private Music mp3Music = Gdx.audio.newMusic(Gdx.files.internal("audio/music/snap_music.mp3"));

    public PlayScreen(Neonpolis game) {
        //stage = new GameStage(game);
        this.game = game;

        mp3Music.setVolume(0.5f);
        mp3Music.setLooping(true);
        mp3Music.play();

        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(this);

        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false, 250, 135);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("alku.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1);

        world = new World(new Vector2(0, -45), true);
        world.setContactListener(this);

        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world, map);

        worldcreator = new B2WorldCreator(world, map);

        player = new Vivica(world, this);
        enemy = new Enemy(world,this);
        hud = new Hud(game.batch);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        update(delta);
        //Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        game.batch.setProjectionMatrix(gamecam.combined);

        // render Box2DDebug lines
        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        enemy.draw(game.batch);
        game.batch.end();

        //update stage
        //stage.draw();
        //stage.act(delta);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        // Player is dead if drop below ground
        if (player.b2body.getPosition().y + 4 <= 0) {
            deathSound.play(2.0f);
            mp3Music.stop();
            game.setScreen(new GameOverScreen(game));
        }

        player.b2body.applyLinearImpulse(new Vector2(2.8f, 0), player.b2body.getWorldCenter(), true);
        player.setBounds(0, 0 ,18, 32);
    }

    public void handleInput(float dt) {
        int posX = Gdx.input.getX();
        int posY = Gdx.input.getY();

        if (!player.jumping && player.b2body.getLinearVelocity().x == 0 && !player.dodging) {
            player.setRegion(player.vivicaStand);
            player.setBounds(0, 0 ,18, 31);
        }

        // move right
        if (Gdx.input.isTouched() && posX > 1920 / 2 && posX > 500) {
            //player.b2body.applyLinearImpulse(new Vector2(2.8f, 0), player.b2body.getWorldCenter(), true);
            if (!player.jumping)
                    player.setRegion(player.vivicaRun);
                    player.setBounds(0, 0, 18, 32);
        }
        /* move left
        if (Gdx.input.isTouched() && posX < 1920 / 2 && posX < 500)
            player.b2body.applyLinearImpulse(new Vector2(-4, 0), player.b2body.getWorldCenter(), true); */
    }

    public void update(float dt) {
        //handle user input first
        handleInput(dt);

        world.step(1 / 60f, 6, 2);
        player.update(dt);
        enemy.update(dt);

        // attach gamecam to player coordinates
        if (player.b2body.getPosition().x >= 250 / 2)
            gamecam.position.x = player.b2body.getPosition().x;
        // update gamecam with correct coordinates
        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        deathSound.dispose();
        mp3Music.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK) {
            game.setScreen(new MenuScreen(game));
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        lastTouch.set(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (player.dodging) {
            player.stopDodge();
            player.setRotation(0);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 newTouch = new Vector2(screenX, screenY);
        // delta will now hold the difference between the last and the current touch positions
        Vector2 delta = newTouch.cpy().sub(lastTouch);

        if (delta.y < -20 && !player.jumping && !player.dodging) {
            player.setRegion(player.vivicaJump);
            player.jump();
            player.setBounds(0, 0, 18, 32);
        }
        else if (delta.y > 35 && !player.jumping) {
            //player.setRegion(player.vivicaSlide);
            //player.dodge();
            //player.setBounds(0, 0, 18, 35);
            //player.setRotation(90);
        }
        lastTouch = newTouch;

        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        // Check if player and ground touch
        if (fixtureA.getUserData() != null
                || fixtureA.getUserData().equals("vivica")
                || fixtureB.getUserData() != null
                && fixtureB.getUserData().equals("ground")) {

            player.landed();
        }

        // Check if player and win-area touch
        if (fixtureA.getUserData() != null
                && fixtureA.getUserData().equals("win")
                && fixtureB.getUserData() != null
                && fixtureB.getUserData().equals("vivica")) {

            game.setScreen(new MenuScreen(game));
        }

        // Check if player and enemy touch
        if (fixtureA.getUserData() != null
                && fixtureA.getUserData().equals("vivica")
                && fixtureB.getUserData() != null
                && fixtureB.getUserData().equals("enemy")) {

            game.setScreen(new GameOverScreen(game));
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

