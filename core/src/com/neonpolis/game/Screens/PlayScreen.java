package com.neonpolis.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.neonpolis.game.Actors.Runner;
import com.neonpolis.game.Neonpolis;
import com.neonpolis.game.Scenes.Hud;
import com.neonpolis.game.Stages.GameStage;
import com.neonpolis.game.Utils.WorldUtils;

import static sun.audio.AudioPlayer.player;

/**
 * Created by nikom on 20.3.2017.
 */

public class PlayScreen implements Screen {

    private GameStage stage;
    public Neonpolis game;
    private Hud hud;

    public TiledMap map;
    OrthogonalTiledMapRenderer renderer;

    public PlayScreen(Neonpolis game){
        stage = new GameStage(game);
        this.game = game;
        hud = new Hud(game.batch);

        map = new TmxMapLoader().load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 5);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView((OrthographicCamera) stage.getCamera());
        renderer.render();

        //update stage
        stage.draw();
        stage.act(delta);

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    public void handleInput(float dt) {
    }

    public void update (float dt) {

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
    }
}
