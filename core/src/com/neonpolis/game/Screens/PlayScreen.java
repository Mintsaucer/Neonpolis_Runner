package com.neonpolis.game.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.neonpolis.game.Neonpolis;
import com.neonpolis.game.Scenes.Hud;
import com.neonpolis.game.Sprites.Vivica;
import com.neonpolis.game.Utils.B2WorldCreator;



/**
 * Created by nikom on 20.3.2017.
 */

public class PlayScreen implements Screen, InputProcessor {

    //private GameStage stage;
    private Neonpolis game;
    private Hud hud;
    private Music music;
    private Vivica player;
    private TextureAtlas atlas;

    private OrthographicCamera gamecam;
    private Viewport gamePort;

    private TiledMap map;
    private TmxMapLoader mapLoader;
    OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Vector2 lastTouch = new Vector2();

    public PlayScreen(Neonpolis game){
        //stage = new GameStage(game);
        this.game = game;

        Gdx.input.setInputProcessor(this);

        gamecam = new OrthographicCamera();
        //gamePort = new FitViewport(100,50, gamecam);
        gamecam.setToOrtho(false,220,100);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("map1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1);

        //gamecam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        world = new World(new Vector2(0,-60), true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world,map);

        player = new Vivica(world, this);
        hud = new Hud(game.batch);
    }

    public TextureAtlas getAtlas() {
        return atlas;
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
        //game.batch.begin();
        //player.draw(game.batch);
        //game.batch.end();

        //update stage
        //stage.draw();
        //stage.act(delta);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        // Player is dead if drop below ground
        if (player.b2body.getPosition().y +4 <= 0) {
            game.setScreen(new GameOverScreen(game));
        }
    }

    public void handleInput(float dt) {
        int posX = Gdx.input.getX();
        int posY = Gdx.input.getY();

        // move left
        if (Gdx.input.isTouched() && posX > 1920 / 2 && posX > 500  )
            player.b2body.applyLinearImpulse(new Vector2(4, 0), player.b2body.getWorldCenter(), true);
        // move right
        if (Gdx.input.isTouched() && posX < 1920 / 2 && posX < 500  )
            player.b2body.applyLinearImpulse(new Vector2(-4, 0), player.b2body.getWorldCenter(), true);
       // jump
        if (Gdx.input.isTouched() && posY < 1080 / 2)
           player.b2body.applyLinearImpulse(new Vector2(0, 10), player.b2body.getWorldCenter(), true);

    }

    public void update (float dt) {
        //handle user input first
        handleInput(dt);

        world.step(1/60f, 6, 2);
        player.update(dt);

        // attach gamecam to player coordinates
        if(player.b2body.getPosition().x >= 220/2)
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
    }

    @Override
    public boolean keyDown(int keycode) {
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
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 newTouch = new Vector2(screenX, screenY);
        // delta will now hold the difference between the last and the current touch positions
        Vector2 delta = newTouch.cpy().sub(lastTouch);
        if (delta.y < 0) {
                player.b2body.applyLinearImpulse(new Vector2(0, 40), player.b2body.getWorldCenter(), true);
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
}
