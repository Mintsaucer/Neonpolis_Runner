package com.neonpolis.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.neonpolis.game.Actors.Runner;
import com.neonpolis.game.Neonpolis;
import com.neonpolis.game.Scenes.Hud;
import com.neonpolis.game.Stages.GameStage;

import static sun.audio.AudioPlayer.player;

/**
 * Created by nikom on 20.3.2017.
 */

public class PlayScreen implements Screen {

    private GameStage stage;
    public Neonpolis game;
    private Hud hud;

    public PlayScreen(Neonpolis game){
        stage = new GameStage(game);
        this.game = game;
        hud = new Hud(game.batch);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        //Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
